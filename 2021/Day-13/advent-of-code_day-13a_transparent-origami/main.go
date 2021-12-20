package main

import (
	"fmt"
	"os"
)

func main() {
	fmt.Println("Advent Of Code: Day 13 - Transparent Origami: part 1")
	filename := "data.txt"
	// filename := "example.txt"

	paper, instructions, err := ReadData(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	paper.Print()
	fmt.Println("")
	instructions.Print()

	for i, instruction := range instructions.List {
		fmt.Printf("--[ %d: %s ]------------------\n", i, instruction.String())
		instruction.Fold(paper)
		paper.Print()
	}
}
