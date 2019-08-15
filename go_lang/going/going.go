// little things I do in go

package main

import (
	"fmt"
	"log"
	"net/http"
	"strings"
)

// fizz buzz challenge
func fizzbuzz() {
	for i := 1; i <= 20; i++ {
		if i%3 == 0 && i%5 == 0 {
			fmt.Println("fizz buzz")
		} else if i%3 == 0 {
			fmt.Println("fizz")
		} else if i%5 == 0 {
			fmt.Println("buzz")
		} else {
			fmt.Println(i)
		}
	}
}

// even-ended number challenge
func evenEnded() {
	// count = 0
	count := 0

	// for every pair of four digit numbers
	for a := 1000; a <= 9999; a++ {
		for b := a; b <= 9999; b++ {
			n := a * b

			// if a*b is even ended
			s := fmt.Sprintf("%d", n)
			if s[0] == s[len(s)-1] {
				// count = count + 1
				count++
			}
		}
	}

	// return count
	fmt.Println(count)
}

// maximal value of slice
func maximal(slice []int) {
	max := slice[0]
	for _, s := range slice[1:] {
		if max < s {
			max = s
		}
	}

	fmt.Println(max)
}

// word count
func wordCount(text string) {
	words := strings.Fields(text)
	wordFreqs := map[string]int{} // Empty map

	for _, word := range words {
		wordFreqs[strings.ToLower(word)]++
	}

	fmt.Println(wordFreqs)
}

// return content type
func contentType(url string) (string, error) {
	resp, err := http.Get(url)

	if err != nil {
		return "", err
	}

	defer resp.Body.Close() // close the body

	ctype := resp.Header.Get("Content-Type")
	if ctype == "" { // Return error if Content-Type header not found
		return "", fmt.Errorf("can't find content-type header")
	}

	return ctype, nil
}

// structs practice
// Point is a 2d point
type Point struct {
	X int
	Y int
}

type Square struct {
	Center Point
	Length int
}

// moves the point
func (p *Point) Move(dx, dy int) {
	p.X += dx
	p.Y += dy
}

// moves the square
func (s *Square) Move(dx, dy int) {
	s.Center.Move(dx, dy)
}

// calculates area of a square
func (s *Square) Area() int {
	return s.Length * s.Length
}

func NewSquare(x, y, length int) (*Square, error) {
	if length < 0 {
		return nil, fmt.Errorf("length must be >= 0")
	}

	s := &Square{
		Center: Point{x, y},
		Length: length,
	}

	return s, nil
}

// uncomment respective test cases to test each function
func main() {
	// test fizzbuzz
	// fizzbuzz()

	// test evenEnded
	// evenEnded()

	// test maximal
	// nums := []int{1, 2, 34, 5, 1000}
	// maximal(nums)

	// test wordCount
	// text := `
	// 	Needles and pins
	// 	Needles and pins
	// 	Sew me a sail
	// 	To catch me the wind
	// 	`
	// wordCount(text)

	// test content type
	// ctype, err := contentType("https://linkedin.com")
	// if err != nil {
	// 	fmt.Printf("ERROR: %s\n", err)
	// } else {
	// 	fmt.Println(ctype)
	// }

	// test square struct
	s, err := NewSquare(1, 1, 10)
	if err != nil {
		log.Fatalf("ERROR: can't create square")
	}

	s.Move(2, 3)
	fmt.Printf("%+v\n", s)
	fmt.Println(s.Area())
}