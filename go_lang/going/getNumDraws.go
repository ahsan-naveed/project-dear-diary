package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
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

type HttpResponse struct {
	Page       string  `json:"page"`
	PerPage    int     `json:"per_page"`
	Total      int     `json:"total"`
	TotalPages int     `json:"total_pages"`
	Data       []Match `json:"data"`
}

type FirstResponse struct {
	Page       int     `json:"page"`
	PerPage    int     `json:"per_page"`
	Total      int     `json:"total"`
	TotalPages int     `json:"total_pages"`
	Data       []Match `json:"data"`
}

func (r *HttpResponse) hasNextPage() bool {
	p, _ := strconv.Atoi(r.Page)
	return p < r.TotalPages
}

// getNumDraws gets number of draw matches in a given year
// Forvinstance, there 516 draw matches between team1 and
// team2 in 2011.
func getNumDraws(year int) int32 {
	httpResp := &HttpResponse{Page: "1"}
	firstResp := &FirstResponse{}

	url := fmt.Sprintf("https://jsonmock.hackerrank.com/api/football_matches?year=%d", year)

	resp, err := http.Get(url)

	if err != nil {
		log.Fatal(err)
	}

	defer resp.Body.Close()

	// Decod JSON
	dec := json.NewDecoder(resp.Body)

	if err := dec.Decode(firstResp); err != nil {
		log.Fatal(err)
	}

	httpResp.TotalPages = firstResp.TotalPages

	var draws int32
	var nextPage int

	for httpResp.hasNextPage() {
		if httpResp.Page == "1" {
			httpResp.Data = firstResp.Data
		}

		for i := 0; i < len(httpResp.Data); i++ {
			if httpResp.Data[i].Team1Goals == httpResp.Data[i].Team2Goals {
				draws += 1
			}
		}

		currentPage, _ := strconv.Atoi(httpResp.Page)
		nextPage = currentPage + 1

		url = fmt.Sprintf("https://jsonmock.hackerrank.com/api/football_matches?year=%d&page=%d", year, nextPage)

		resp, err := http.Get(url)

		if err != nil {
			log.Fatal(err)
		}

		defer resp.Body.Close()

		dec := json.NewDecoder(resp.Body)

		if err := dec.Decode(httpResp); err != nil {
			log.Fatal(err)
		}
	}

	return draws
}

func main() {
	fmt.Println(getNumDraws(2011))
}
