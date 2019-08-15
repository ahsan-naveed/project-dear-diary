package main

import (
	"fmt"
)

func main() {
	// fmt.Printf("Enter float: \n")
	// var flt float64 // precision of upto 15 decimal places
	// fmt.Scan(&flt)
	// fmt.Printf("Integeral part: %d\n", int64(flt))

	var xtemp int
	x1 := 0
	x2 := 1
	for x := 0; x < 5; x++ {
		xtemp = x2
		x2 = x2 + x1
		x1 = xtemp
	}
	fmt.Println(x2)

}
