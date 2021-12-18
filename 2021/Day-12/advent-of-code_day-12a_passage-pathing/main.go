package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

func main() {
	fmt.Println("Advent Of Code: Day 12 - Passaage Pathing: part 1")
	// filename := "data.txt"
	filename := "example.txt"

	lines, err := tools.ReadLines(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	for _, line := range lines {
		fmt.Println(line)
	}
}
