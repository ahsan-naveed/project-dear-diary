// Note: slice can be used as stack
// Note: map can be used as set

package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"unicode"
	"unicode/utf8"
)

func main() {
	ages := make(map[string]int) // mapping from strings to ints
	ages["alice"] = 31
	ages["charlie"] = 34

	ages2 := map[string]int{
		"alice":   31,
		"charlie": 34,
	}

	// a map element is not a variable, and we cannot take its address
	fmt.Println(ages2)

	delete(ages, "alice")
	fmt.Println(ages)

	if age, ok := ages["bob"]; !ok {
		fmt.Println("Bob is not a key in this map.")
	} else {
		fmt.Printf("Bob's age: %d\n", age)
	}

	// As with slices, maps cannot be compared to
	// each other; the only legal comparison is with nil

	// testing dedup
	// dedup()

	// testing Charcount
	// Charcount()

	// testing hasEdge and addEdge
	var graph = make(map[string]map[string]bool)
	addEdge(graph, "a", "b")
	addEdge(graph, "b", "c")
	addEdge(graph, "c", "a")
	addEdge(graph, "a", "d")

	fmt.Printf("a -> b ? %t\n", hasEdge(graph, "a", "b"))
	fmt.Printf("a -> c ? %t\n", hasEdge(graph, "a", "c"))
	fmt.Printf("d -> a ? %t\n", hasEdge(graph, "d", "a"))
	fmt.Printf("a -> d ? %t\n", hasEdge(graph, "a", "d"))

	// testing wordFreq
	wordFreq()

	return
}

func equal(a, b map[string]int) bool {
	if len(a) != len(b) {
		return false
	}
	for k, av := range a {
		if bv, ok := b[k]; !ok || bv != av {
			return false
		}
	}
	return true
}

func dedup() {
	seen := make(map[string]bool) // a set of strings
	input := bufio.NewScanner(os.Stdin)
	for input.Scan() {
		line := input.Text()
		if !seen[line] {
			seen[line] = true
			fmt.Println(line)
		}
	}
	if err := input.Err(); err != nil {
		fmt.Fprintf(os.Stderr, "dedup: %v\n", err)
		os.Exit(1)
	}
}

// using slices as keys
var m = make(map[string]int)

func k(list []string) string  { return fmt.Sprintf("%q", list) }
func Add(list []string)       { m[k(list)]++ }
func Count(list []string) int { return m[k(list)] }

// exercise 4.8:
// Charcount computers count of unicode characters
func Charcount() {
	counts := make(map[rune]int)       // counts of Unicode characters
	letterCounts := make(map[rune]int) // counts of Unicode letters
	var utflen [utf8.UTFMax + 1]int    // count of lengths of UTF-8 encodings
	invalid := 0                       // count of invalid UTF-8 characters

	in := bufio.NewReader(os.Stdin)
	for {
		r, n, err := in.ReadRune() // returns rune, n bytes, error
		if err == io.EOF {
			break
		}
		if err != nil {
			fmt.Fprintf(os.Stderr, "Charcount: %v\n", err)
		}
		if r == unicode.ReplacementChar && n == 1 {
			invalid++
			continue
		}
		if unicode.IsLetter(r) {
			letterCounts[r]++
		}
		counts[r]++
		utflen[n]++
	}
	fmt.Printf("rune\tcount\n")
	for c, n := range counts {
		fmt.Printf("%q\t%d\n", c, n)
	}
	fmt.Printf("letter\tcount\n")
	for c, n := range letterCounts {
		fmt.Printf("%q\t%d\n", c, n)
	}
	fmt.Print("\nlen\tcount\n")
	for i, n := range utflen {
		if i > 0 {
			fmt.Printf("%d\t%d\n", i, n)
		}
	}
	if invalid > 0 {
		fmt.Printf("\n%d invalid UTF-8 characters\n", invalid)
	}
}

func addEdge(G map[string]map[string]bool, from string, to string) {
	edges := G[from]
	if edges == nil {
		edges = make(map[string]bool)
		G[from] = edges
	}
	edges[to] = true
}

func hasEdge(G map[string]map[string]bool, from string, to string) bool {
	return G[from][to]
}

// exercise 4.9:
// wordFreq
func wordFreq() {
	freq := make(map[string]int)
	input := bufio.NewScanner(os.Stdin)
	input.Split(bufio.ScanWords)
	for input.Scan() {
		word := input.Text()
		freq[word]++
	}
	if err := input.Err(); err != nil {
		fmt.Fprintf(os.Stderr, "wordFreq: %v\n", err)
		os.Exit(1)
	}
	for w, f := range freq {
		fmt.Printf("%s\t%d", w, f)
	}
}
