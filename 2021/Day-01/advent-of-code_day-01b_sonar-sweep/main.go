package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

func main() {
	fmt.Println("Advent Of Code: Day 1 - Sonar Sweep: part 2")
	filename := "data.txt"
	numbers, err := tools.ReadNumbers(filename)
	if err != nil {
		fmt.Printf("Could not read data from '%s'\n", filename)
		os.Exit(1)
	}

	limit := 3
	N := len(numbers) - limit

	if len(numbers) < limit {
		fmt.Printf("Not enough data\n")
		os.Exit(1)
	}

	var windows []int
	for i := 0; i <= N; i++ {
		window := 0
		for j := i; j < i+limit; j++ {
			number := numbers[j]
			window = window + number
		}
		windows = append(windows, window)
	}

	count, err := tools.CountLarger(windows, true)
	if err != nil {
		os.Exit(1)
	}

	fmt.Printf("number larger: %d\n", count)
}
