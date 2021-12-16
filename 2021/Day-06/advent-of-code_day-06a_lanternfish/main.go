package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/Day-06/fish"
)

func main() {
	fmt.Println("Advent Of Code: Day 6 - Lanternfish: part 1")
	filename := "data.txt"
	// filename := "example.txt"

	fishes, err := fish.ReadFishes(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	fmt.Printf("Initial state: total: %4d,    %s\n", fishes.Total(), fishes.String())
	for day := 1; day <= 256; day++ {
		fishes.Step()
		fmt.Printf("After %2d days: total: %4d,    %s\n", day, fishes.Total(), fishes.String())
	}
}
