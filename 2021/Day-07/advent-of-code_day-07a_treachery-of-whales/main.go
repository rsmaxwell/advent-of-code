package main

import (
	"fmt"
	"math"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

func main() {
	fmt.Println("Advent Of Code: Day 7 - The Treachery of Whales: part 1")
	filename := "data.txt"
	// filename := "example.txt"

	numbers, err := tools.ReadCommaSeparatedNumbers(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	for i, number := range numbers {
		fmt.Printf("%2d: %4d\n", i, number)
	}

	minimum := tools.MinArray(numbers)
	maximum := tools.MaxArray(numbers)
	fmt.Printf("minimum: %2d: maximum: %2d\n", minimum, maximum)

	minFuel := math.MaxInt
	minPos := 0
	for i := minimum; i <= maximum; i++ {
		fuel := CalcFuel(i, numbers)
		if fuel < minFuel {
			minFuel = fuel
			minPos = i
		}
	}

	fmt.Printf("minPos: %d: minFuel: %d\n", minPos, minFuel)
}

func CalcFuel(pos int, numbers []int) int {

	// fmt.Printf("Position: %d\n", i)
	total := 0
	for _, number := range numbers {
		distance := tools.Abs(number - pos)
		fuel := tools.SumOfLinearSequence(distance+1, 0, distance)

		// fmt.Printf("    Move from %d to %d: %d fuel\n", number, pos, fuel)
		total = total + fuel
	}

	// fmt.Printf("Total: %d\n", total)
	return total
}
