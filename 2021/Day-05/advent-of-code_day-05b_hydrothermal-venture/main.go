package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/Day-05/path"
	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

func main() {
	fmt.Println("Advent Of Code: Day 5 - Hydrothermal Venture: part 2")
	// filename := "data.txt"
	filename := "example.txt"

	paths, err := ReadData(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	if len(paths) < 1 {
		fmt.Println("No data!")
		os.Exit(1)
	}

	area := paths[0]
	for _, p := range paths {
		// fmt.Println(p.String())
		area = p.AddArea(area)
	}

	verbose := false
	if isSmall(area) {
		verbose = true
	}

	err = PrintMap(area, paths, verbose)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}
}

func isSmall(area *path.Path) bool {
	width := area.From.X - area.To.X
	height := area.From.Y - area.To.Y
	size := tools.Abs(width * height)
	return size < 1000
}
