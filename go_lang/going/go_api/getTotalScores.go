package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"net/url"
	"strconv"
)

type Match struct {
	Competition string `json:"competition"`
	Year        int    `json:"year"`
	Round       string `json:"round"`
	Team1       string `json:"team1"`
	Team1Goals  string `json:"team1goals"`
	Team2       string `json:"team2"`
	Team2Goals  string `json:"team2goals"`
}

type Matches struct {
	Page       string  `json:"page"`
	PerPage    int     `json:"per_page"`
	Total      int     `json:"total"`
	TotalPages int     `json:"total_pages"`
	Data       []Match `json:"data"`
}

type WinnerResponse struct {
	Page       int      `json:"page"`
	PerPage    int      `json:"per_page"`
	Total      int      `json:"total"`
	TotalPages int      `json:"total_pages"`
	Data       []Winner `json:"data"`
}

type Winner struct {
	Name     string `json:"name"`
	Country  string `json:"country"`
	Year     int    `json:"year"`
	Winner   string `json:"winner"`
	Runnerup string `json:"runnerup"`
}

// hasNextPage returns true if there is next page available
func (r *Matches) hasNextPage() bool {
	if r.Page == "1" {
		return true
	}
	p, _ := strconv.Atoi(r.Page)
	return p < r.TotalPages
}

// getWinner returns the winner given the competition and year
func getWinner(competitionName string, year int) string {
	winnerRespo := &WinnerResponse{}
	baseUrl, err := url.Parse("https://jsonmock.hackerrank.com/api/football_competitions")
	if err != nil {
		log.Fatalf("Malformed URL: %s", err.Error())
	}
	params := url.Values{}
	params.Add("name", competitionName)
	params.Add("year", fmt.Sprintf("%d", year))
	baseUrl.RawQuery = params.Encode()
	resp, err := http.Get(baseUrl.String())
	if err != nil {
		log.Fatal(err)
	}
	defer resp.Body.Close()
	dec := json.NewDecoder(resp.Body)
	if err := dec.Decode(winnerRespo); err != nil {
		log.Fatal(err)
	}
	return winnerRespo.Data[0].Winner // may be guard for zero length data
}

// sumGoals goes through matches and sums up the
// goals scored by winning team
func sumGoals(data []Match, winner string, sum *int) {
	for i := 0; i < len(data); i++ {
		if data[i].Team1 == winner {
			g, _ := strconv.Atoi(data[i].Team1Goals)
			*sum += g
		} else {
			g, _ := strconv.Atoi(data[i].Team2Goals)
			*sum += g
		}
	}
}

// getMatchesWon passes list of matches won by team1/team2
// in a givne competition and year
func getMatchesWon(goals *int, winner, competitionName, teamNumber string, year int) {
	currentPage := 1
	matchesRespo := &Matches{Page: "1"}
	for matchesRespo.hasNextPage() {
		baseUrl, err := url.Parse("https://jsonmock.hackerrank.com/api/football_matches")
		if err != nil {
			log.Fatalf("Malformed URL: %s", err.Error())
		}
		params := url.Values{}
		params.Add("competition", competitionName)
		params.Add("year", fmt.Sprintf("%d", year))
		params.Add("page", fmt.Sprintf("%d", currentPage))
		params.Add(teamNumber, winner)
		baseUrl.RawQuery = params.Encode()
		resp, err := http.Get(baseUrl.String())
		if err != nil {
			log.Fatal(err)
		}
		defer resp.Body.Close()
		dec := json.NewDecoder(resp.Body)
		if err := dec.Decode(matchesRespo); err != nil {
			log.Fatal(err)
		}
		sumGoals(matchesRespo.Data, winner, goals)
		currentPage += 1
	}

}

func main() {
	competition := "UEFA Champions League"
	year := 2011
	winner := getWinner(competition, year)
	totalGoals := 0
	getMatchesWon(&totalGoals, winner, competition, "team1", year)
	getMatchesWon(&totalGoals, winner, competition, "team2", year)
	winningStatement := fmt.Sprintf(
		"During %s %d, %d goals were scored by winning team %s!",
		competition, year, totalGoals, winner,
	)
	fmt.Println(winningStatement)
}

// input <- "UEFA Champions League", 2011
// ouput -> During UEFA Champions League 2011, 28 goals were scored by winning team Chelsea!
