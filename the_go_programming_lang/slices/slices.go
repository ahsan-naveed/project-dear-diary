package main

import (
	"fmt"
	"unicode/utf8"
)

func main() {
	months := [...]string{ // array
		1:  "January",
		2:  "February",
		3:  "March",
		4:  "April",
		5:  "May",
		6:  "June",
		7:  "July",
		8:  "August",
		9:  "September",
		10: "October",
		11: "November",
		12: "December",
	}
	Q2 := months[4:7]     // slice
	summer := months[6:9] // slice
	fmt.Println(Q2)
	fmt.Println(summer)

	for _, s := range summer {
		for _, q := range Q2 {
			if s == q {
				fmt.Printf("%s appears in both\n", s)
			}
		}
	}

	// slicing beyond cap causes panic but
	// slicing beyond len exnteds the slice

	// fmt.Println(summer[:20]) // panic: out of range

	endlessSummer := summer[:5] // extend a slice within cap
	fmt.Println(endlessSummer)

	a := [...]int{0, 1, 2, 3, 4, 5} // array
	reverse(a[:])
	fmt.Printf("a: %x\n", a)

	s := []int{0, 1, 2, 3, 4, 5} // slice
	// Rotate s left by two positions
	reverse(s[:2]) // first to the leading n elements
	reverse(s[2:]) // then to the remaining elements
	reverse(s)     // and finally to the whole slice
	fmt.Printf("s: %x\n", s)

	// The zero value of a slice type is nil
	// nil behaves like a zero-length slice
	var nilSlice []int
	nilSlice = nil
	nilSlice = []int(nil)
	nilSlice = []int{} // not nil tho

	if len(nilSlice) == 0 {
		fmt.Println("nilSlice is empty.")
	}

	var runes []rune
	for _, r := range "Hello, World" {
		runes = append(runes, r)
	}
	fmt.Printf("%q\n", runes)
	fmt.Printf("%q\n", []rune("Hello, World"))

	// testing appendInt
	var x, y []int
	for i := 0; i < 10; i++ {
		y = appendInt(x, i)
		fmt.Printf("%d  cap=%d\t%v\n", i, cap(y), y)
		x = y
	}

	// testing nonempty
	data := []string{"one", "", "three"}
	fmt.Printf("%q\n", data) // `["one" "three"]`
	fmt.Printf("%q\n", data) // `["one" "three" "three"]`

	// testing remove
	r := []int{5, 6, 7, 8, 9}
	fmt.Println(remove3(r, 2)) // "[5 6 8 9]"

	// testing unique
	strings := []string{"a", "a", "b", "c", "c", "c", "d", "d", "e"}
	fmt.Println(unique(strings)) // ["a", "b", "c", "d", "e"]

	// testing revUTF8
	fmt.Println(revUTF8([]byte("xoxo")))

	return
}

// reverse reverses a slice of ints in place
func reverse(s []int) {
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		s[i], s[j] = s[j], s[i]
	}
}

// equal compares two slices - is confusing as the underlying arrary coudl change
func equal(a, b []int) bool {
	if len(a) != len(b) {
		return false
	}
	for i := range a {
		if b[i] != a[i] {
			return false
		}
	}
	return true
}

func appendInt(x []int, y ...int) []int {
	var z []int
	xlen := len(x)
	zlen := xlen + len(y)

	if zlen <= cap(x) {
		// There is room to grow. Extend the slice.
		z = x[:zlen] // input x and the result z share the same underlying array
	} else {
		// There is insufficient space. Allocate new array.
		// Grow by doubling, for amortized linear complexity.
		zcap := zlen
		if zcap < 2*xlen {
			zcap = 2 * xlen
		}
		z = make([]int, zlen, zcap) // result z now refers to a different underlying array than the array that x refers to.
		copy(z, x)
	}

	copy(z[xlen:], y)
	return z
}

// nonempty returns a slice holding only the non-empty strings.
// The underlying array is modified during the call.
func nonempty(strings []string) []string {
	i := 0
	for _, s := range strings {
		if s != "" {
			strings[i] = s
			i++
		}
	}
	return strings[:i]
}

// Whichever variant we use, reusing an array in this way
// requires that at most one output value is produced for
// each input value
func nonempty2(strings []string) []string {
	out := strings[:0] // zero-length slice of original
	for _, s := range strings {
		if s != "" {
			out = append(out, s)
		}
	}
	return out
}

// i < len(slice)
func remove(slice []int, i int) []int {
	out := []int(nil)
	for idx, v := range slice {
		if idx != i {
			out = append(out, v)
		}
	}
	return out
}

// want to preserve original order
func remove2(slice []int, i int) []int {
	copy(slice[i:], slice[i+1:])
	return slice[:len(slice)-1]
}

// don't want to preserve order
func remove3(slice []int, i int) []int {
	last := len(slice) - 1
	slice[i] = slice[last]
	return slice[:last]
}

// Exercise 4.3
func reverse2(a *[5]int) {
	for i, j := 0, len(a); i < j; i, j = i+1, j-1 {
		a[i], a[j] = a[j], a[i]
	}
}

// reference: https://stackoverflow.com/a/10485970
func contains(s []string, e string) bool {
	for _, a := range s {
		if a == e {
			return true
		}
	}
	return false
}

// Exercise 4.5
func unique(strings []string) []string {
	out := []string(nil)
	for _, s := range strings {
		if !contains(out, s) {
			out = append(out, s)
		}
	}
	return out
}

// reference: https://github.com/torbiak/gopl/blob/master/ex4.5/unique.go
func unique2(strings []string) []string {
	idx := 0
	for _, s := range strings {
		if strings[idx] == s {
			continue
		}
		idx++
		strings[idx] = s
	}
	return strings[:idx+1]
}

// exercise 4.7 & 4.6: reference: https://github.com/torbiak/gopl/blob/master/ex4.7/reverse.go
func rev(b []byte) {
	size := len(b)
	for i := 0; i < size/2; i++ {
		b[i], b[size-i-1] = b[size-i-1], b[i]
	}
}

func revUTF8(b []byte) []byte {
	for i := 0; i < len(b); {
		_, size := utf8.DecodeRune(b[i:])
		rev(b[i : i+size])
		i += size
	}
	rev(b)
	return b
}
