package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"net/url"
	"strings"
	"time"
)

// Only exported fields are marshaled
type Movie struct {
	Title  string
	Year   int  `json:"released"`
	Color  bool `json:"color,omitempty"`
	Actors []string
}

var movies = []Movie{
	{Title: "Casablanca", Year: 1942, Color: false,
		Actors: []string{"Humphrey Bogart", "Ingrid Bergman"}},
	{Title: "Cool Hand Luke", Year: 1967, Color: true,
		Actors: []string{"Paul Newman"}},
	{Title: "Bullitt", Year: 1968, Color: true,
		Actors: []string{"Steve McQueen", "Jacqueline Bisset"}},
}

func main() {
	data, err := json.MarshalIndent(movies, "", "  ") // slice, prefix, indent spacing
	if err != nil {
		log.Fatalf("JSON marshaling failed: %s.", err)
	}
	fmt.Printf("%s\n", data)

	var titles []struct{ Title string }
	if err := json.Unmarshal(data, &titles); err != nil {
		log.Fatalf("JSON un-marshaling failed: %s.", err)
	}
	fmt.Println(titles)

	// testing github
	result, err := SearchIssues([]string{"repo:golang/go", "json", "decoder"})
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("%d issues:\n", result.TotalCount)
	for _, item := range result.Items {
		fmt.Printf("#%-5d %9.9s %.55s\n",
			item.Number, item.User.Login, item.Title)
	}

	// testing poster
	poster, err := getMoviePoster("Good Will Hunting")
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(poster)
	}
}

// github

const IssuesURL = "https://api.github.com/search/issues"

type IssuesSearchResult struct {
	TotalCount int `json:"total_count"`
	Items      []*Issue
}

type Issue struct {
	Number    int
	HTMLURL   string `json:"html_url"`
	Title     string
	State     string
	User      *User
	CreatedAt time.Time `json:"created_at"`
	Body      string    // in Markdown format
}

type User struct {
	Login   string
	HTMLURL string `json:"html_url"`
}

// SearchIssues queries the GitHub issue tracker.
func SearchIssues(terms []string) (*IssuesSearchResult, error) {
	q := url.QueryEscape(strings.Join(terms, " "))
	getRequest := IssuesURL + "?q=" + q
	fmt.Printf("fetching result from: %s\n", getRequest)
	resp, err := http.Get(getRequest)
	if err != nil {
		return nil, err
	}
	// We must close resp.Body on all execution paths.
	if resp.StatusCode != http.StatusOK {
		resp.Body.Close()
		return nil, fmt.Errorf("search query failed: %s", resp.Status)
	}
	var result IssuesSearchResult
	if err := json.NewDecoder(resp.Body).Decode(&result); err != nil {
		resp.Body.Close()
		return nil, err
	}
	resp.Body.Close()
	return &result, nil
}

// poster
const (
	APIURL     = "http://www.omdbapi.com/?"
	FREEAPIKEY = "f9e326e7"
)

type omdb struct {
	Response string
	Poster   string
	Error    string `json:"Error,omitempty"`
}

// getMoviePoster gets poster of the movie with title == searchTerm
func getMoviePoster(searchTerm string) (string, error) {
	var movie omdb
	url_ := fmt.Sprintf("%st=%s&apikey=%s", APIURL, url.QueryEscape(searchTerm), FREEAPIKEY)
	resp, err := http.Get(url_)
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()
	if resp.StatusCode == http.StatusOK {
		if err := json.NewDecoder(resp.Body).Decode(&movie); err != nil {
			return "", err
		}

	} else {
		return "", fmt.Errorf("Server Error: %s", resp.Status)
	}
	if movie.Response == "False" {
		return "", fmt.Errorf("%s", movie.Error)
	} else {
		return movie.Poster, nil
	}
}
