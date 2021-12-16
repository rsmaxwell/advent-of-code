package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/Day-08/entry"
)

func main() {
	fmt.Println("Advent Of Code: Day 8 - Seven Segment Search: part 1")
	filename := "data.txt"
	// filename := "example1.txt"

	entries, err := entry.ReadEntries(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	total := 0
	total1478 := 0
	for _, e := range entries {
		count, number, err := e.Solve()
		if err != nil {
			fmt.Println(err.Error())
			os.Exit(1)
		}
		total1478 += count
		total += number
	}
	fmt.Printf("Total1478: %d\n", total1478)
	fmt.Printf("Total:     %d\n", total)
}
