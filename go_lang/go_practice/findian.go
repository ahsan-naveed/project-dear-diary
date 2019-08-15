package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	fmt.Printf("Enter string: \n")
	in := bufio.NewReader(os.Stdin)
	str, err := in.ReadString('\n')

	if err == nil {
		str = strings.ToLower(str)
		str = str[1 : len(str)-2]

		if strings.HasPrefix(str, "i") && strings.Contains(str, "a") && strings.HasSuffix(str, "n") {
			fmt.Printf("Found! \n")
		} else {
			fmt.Printf("Not Found! \n")
		}
	}
}
