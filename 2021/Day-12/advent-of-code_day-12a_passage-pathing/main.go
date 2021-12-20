package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/Day-12/cave"
)

func main() {
	fmt.Println("Advent Of Code: Day 12 - Passage Pathing: part 1")
	filename := "data.txt"
	// filename := "example3.txt"

	caves, err := cave.ReadCaves(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	fmt.Println("---[ Caves ]---------------------")
	for name, cave := range caves.Map {
		fmt.Printf("%s --> %s\n", name, cave.String())
	}

	name := "start"
	next := caves.Map[name]
	path := []string{name}

	fmt.Println("---[ Routes ]---------------------")
	next.Visit(caves, path, 0, 0)

	fmt.Println("----------------------------------")
	fmt.Printf("NumberOfRoutes: %d\n", caves.NumberOfRoutes)
}
