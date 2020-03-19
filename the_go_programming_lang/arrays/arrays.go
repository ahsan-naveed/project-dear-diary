package main

import (
	"crypto/sha256"
	"crypto/sha512"
	"fmt"
)

func main() {
	var a [3]int             // array of 3 integers
	fmt.Println(a[0])        // print first element
	fmt.Println(a[len(a)-1]) // print last element

	// print the indicies and elements
	for i, v := range a {
		fmt.Printf("%d => %d\n", i, v)
	}

	// print elements only
	for _, v := range a {
		fmt.Printf("%d\n", v)
	}

	q := [...]int{1, 2, 3}
	var r [3]int = [3]int{1, 2}
	fmt.Println(r[2])
	fmt.Printf("%T\n", q)

	type Currency int
	const (
		USD Currency = iota
		EUR
		GBP
		RMB
	)
	symbol := [...]string{USD: "$", EUR: "€", GBP: "£", RMB: "¥"}
	for i, v := range symbol {
		fmt.Printf("%d %s\n", i, v)
	}

	c1 := sha256.Sum256([]byte("x"))
	c2 := sha256.Sum256([]byte("X"))
	var c3 *[32]byte = &c1
	fmt.Printf("%x\n%x\n%t\n%T\n", c1, c2, c1 == c2, c1)

	// zero(c3)
	fmt.Printf("c1: %x\n", c1)
	fmt.Printf("c3: %x\n", *c3)

	fmt.Printf("c1 & c2 are equal? %t\n", areSameShas(&c1, &c2))

	fmt.Println(toSHA256([]byte("x"), "sha256"))
	fmt.Println(toSHA256([]byte("x"), "sha384"))
	fmt.Println(toSHA256([]byte("x"), "sha512"))

	return
}

// zero zeroes the contents of a [32]byte array
func zero(ptr *[32]byte) {
	for i, _ := range ptr {
		ptr[i] = 0
	}

	// or

	*ptr = [32]byte{}
}

// Exercise 4.1: areSameShas checks to shas256 values for equality
func areSameShas(a, b *[32]byte) bool {
	ret := true
	for i, v := range *a {
		if b[i] != v {
			ret = false
			break
		}
	}
	return ret
}

// Exercise 4.2: toSHA256 returns sha256
func toSHA256(in []byte, opt string) string {
	sha := fmt.Sprintf("sha256: %x\n", sha256.Sum256(in))
	switch opt {
	case "sha512":
		sha = fmt.Sprintf("sha512: %x\n", sha512.Sum512(in))
		break
	case "sha384":
		sha = fmt.Sprintf("sha384: %x\n", sha512.Sum384(in))
		break
	default:
		break
	}

	return sha
}
