package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/Day-04/advent-of-code_day-04a_giant-squid/game"
)

func main() {
	fmt.Println("Advent Of Code: Day 4 - Giant Squid: part 1")
	filename := "data.txt"
	// filename := "example.txt"

	g, err := game.ReadGame(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	err = g.FirstTurn()
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	fmt.Printf("---[ Turn: %d, Number: %d ]-------------------------------------------------\n", g.Turn, g.Numbers[g.Turn-1])
	fmt.Println(g.String())

	err = g.CalcHasWon()
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	for ok := g.HasWon; !ok; ok = g.HasWon {

		err = g.NextTurn()
		if err != nil {
			fmt.Println(err.Error())
			os.Exit(1)
		}

		fmt.Printf("---[ Turn: %d, Number: %d ]-------------------------------------------------\n", g.Turn, g.Numbers[g.Turn-1])
		fmt.Println(g.String())

		err = g.CalcHasWon()
		if err != nil {
			fmt.Println(err.Error())
			os.Exit(1)
		}
	}
}
