package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

func main() {
	fmt.Println("Advent Of Code: Day 1 - Sonar Sweep: part 1")
	filename := "data.txt"
	numbers, err := tools.ReadNumbers(filename)
	if err != nil {
		os.Exit(1)
	}

	count, err := tools.CountLarger(numbers, true)
	if err != nil {
		os.Exit(1)
	}

	fmt.Printf("number larger: %d\n", count)
}
