package main

import (
	"fmt"
	"os"
	"sort"

	"github.com/rsmaxwell/advent-of-code/2021/Day-10/code"
	"github.com/rsmaxwell/advent-of-code/2021/Day-10/myerrors"
	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

func main() {
	fmt.Println("Advent Of Code: Day 10 - Syntax Scoring: part 1")
	filename := "data.txt"
	// filename := "example.txt"

	lines, err := tools.ReadLines(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}
	_, maxWidth := tools.GetLinesWidth(lines)

	scores := []int{}
	total := 0
	machine := code.New()
	for _, line := range lines {

		err := machine.Compile(line)

		if err != nil {
			serr, ok := err.(*myerrors.SyntaxError)
			if ok {
				score := lookup[serr.Char]
				fmt.Printf("%-*s - %s - score: %d\n", maxWidth, line, serr.Error(), score)
				total += score
			} else {
				ierr, ok := err.(*myerrors.IncompleteError)
				if ok {
					fmt.Printf("%-*s - %s\n", maxWidth, line, ierr.Error())
					scores = append(scores, ierr.Score)
				} else {
					fmt.Printf("%-*s - %s\n", maxWidth, line, err.Error())
				}
			}
		} else {
			fmt.Printf("%*s - %s\n", maxWidth, line, "ok")
		}
	}
	fmt.Printf("Total Syntax Error Score: %d\n", total)

	sort.Ints(scores)
	for _, score := range scores {
		fmt.Printf("%d\n", score)
	}

	m := (len(scores) - 1) / 2
	fmt.Printf("middle score: [%d]: %d\n", m, scores[m])
}

var (
	lookup map[string]int
)

func init() {
	lookup = make(map[string]int)
	lookup[")"] = 3
	lookup["]"] = 57
	lookup["}"] = 1197
	lookup[">"] = 25137
}
