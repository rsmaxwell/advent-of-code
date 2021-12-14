package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/Day-02/advent-of-code_day-02a_dive/commands"
	"github.com/rsmaxwell/advent-of-code/2021/Day-02/advent-of-code_day-02a_dive/location"
)

func main() {
	fmt.Println("Advent Of Code: Day 2 - Dive: part 1")
	filename := "data.txt"
	// filename := "example.txt"
	cmds, err := commands.ReadCommands(filename)
	if err != nil {
		os.Exit(1)
	}

	loc := location.New(0, 0)
	fmt.Printf("%10s --> %10s", "<INITIAL>", loc)
	for _, cmd := range cmds {
		loc = cmd.Move(loc)
		fmt.Printf("%10s --> %10s", cmd, loc)
	}

	answer := loc.Position * loc.Depth
	fmt.Printf("answer: %d\n", answer)
}
