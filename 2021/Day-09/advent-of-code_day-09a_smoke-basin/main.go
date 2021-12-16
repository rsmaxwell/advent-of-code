package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/Day-09/cave"
)

func main() {
	fmt.Println("Advent Of Code: Day 9 - Smoke Basin: part 1")
	filename := "data.txt"
	// filename := "example.txt"

	c, err := cave.ReadCave(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	risk := c.FindLowPointRisk()
	fmt.Printf("risk: %d\n", risk)

	c.ListBasinSizes()
}
