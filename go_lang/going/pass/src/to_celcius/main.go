package main

import (
	"fmt"
	"keyboard"
	"log"
)

func main() {
	fmt.Println("Enter a temperature in Fahrenheit: ")
	fahrenheit, err := keyboard.GetFloat()
	if err != nil {
		log.Fatal(err)
	}
	celcius := (fahrenheit - 32) * 5 / 9
	fmt.Printf("%0.2f degrees Celcius\n", celcius)
}
