package main

import (
	"fmt"
	"os"
	"strconv"
	"strings"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

const (
	N = 10
)

type Octapus struct {
	energy  int
	flashed bool
}

var (
	array [N][N]*Octapus

	deltaRow []int
	deltaCol []int

	lookup map[string]int
)

func init() {
	deltaRow = []int{-1, -1, -1, 0, 0, 1, 1, 1}
	deltaCol = []int{-1, 0, 1, -1, 1, -1, 0, 1}

	lookup = make(map[string]int)
	lookup[")"] = 3
	lookup["]"] = 57
	lookup["}"] = 1197
	lookup[">"] = 25137
}

func main() {
	fmt.Println("Advent Of Code: Day 11 - Dumbo Octapus: part 1")
	filename := "data.txt"
	// filename := "example.txt"

	lines, err := tools.ReadLines(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	for row, line := range lines {
		words := strings.Split(line, "")
		for col, word := range words {
			number, err := strconv.Atoi(word)
			if err != nil {
				fmt.Println(err.Error())
				os.Exit(1)
			}
			array[row][col] = &Octapus{energy: number, flashed: false}
		}
	}

	PrintArray("Before any steps")

	total := 0
	var i int
	for i = 1; i <= 1000000; i++ {

		// PrintArray(fmt.Sprintf("Before Step %d", i))

		// the energy level of each octopus increases by 1.
		for row := 0; row < N; row++ {
			for col := 0; col < N; col++ {
				Increment(row, col)
			}
		}

		// PrintArray(fmt.Sprintf("Step %d (after increment)", i))

		// any octopus with an energy level greater than 9 flashes
		for row := 0; row < N; row++ {
			for col := 0; col < N; col++ {
				MaybeFlash(row, col)
			}
		}

		// PrintArray(fmt.Sprintf("Step %d (after MaybeFlash)", i))

		// any octopus that flashed during this step has its energy level set to 0
		for row := 0; row < N; row++ {
			for col := 0; col < N; col++ {
				Reset(row, col)
			}
		}

		// count the number of flashes
		count, allFlashed := CountFlashes()
		total += count

		doit := false
		if i < 10 {
			doit = true
		} else if i > 190 {
			doit = true
		} else if (i % 10) == 0 {
			doit = true
		}

		if doit {
			PrintArray(fmt.Sprintf("After step %d", i))
		}

		if allFlashed {
			break
		}
	}

	// PrintArray(fmt.Sprintf("After step %d", i))

	fmt.Printf("Total number of flashes: %d\n", total)
}

func Increment(row, col int) {
	octapus := array[row][col]
	octapus.energy += 1
	octapus.flashed = false
}

func MaybeFlash(row, col int) {
	octapus := array[row][col]
	if (octapus.energy > 9) && !octapus.flashed {
		// PrintArray(fmt.Sprintf("--- --- before Flash(%d,%d) --- ", row, col))
		octapus.flashed = true
		for d := 0; d < len(deltaRow); d++ {
			rr := row + deltaRow[d]
			cc := col + deltaCol[d]
			if (rr >= 0) && (rr < N) && (cc >= 0) && (cc < N) {
				array[rr][cc].energy += 1
				MaybeFlash(rr, cc)
			}
		}
		// PrintArray(fmt.Sprintf("--- --- after Flash(%d,%d) --- ", row, col))
	}
}

func Reset(row, col int) {
	octapus := array[row][col]
	if octapus.flashed {
		octapus.energy = 0
		// octapus.flashed = false
	}
}

func PrintArray(title string) {
	fmt.Printf("%s:\n", title)
	for row := 0; row < N; row++ {
		for col := 0; col < N; col++ {
			octapus := array[row][col]
			text := fmt.Sprintf("%3d", octapus.energy)
			tools.Print(octapus.flashed, text)
		}
		fmt.Println()
	}
	fmt.Println()
}

func CountFlashes() (int, bool) {
	total := 0
	allFlashed := true
	for row := 0; row < N; row++ {
		for col := 0; col < N; col++ {
			if array[row][col].flashed {
				total += 1
			} else {
				allFlashed = false
			}
		}
	}
	return total, allFlashed
}
