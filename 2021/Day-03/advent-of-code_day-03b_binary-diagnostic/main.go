package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/Day-03/advent-of-code_day-03b_binary-diagnostic/bitcriteria"
	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

func main() {
	fmt.Println("Advent Of Code: Day 3 - Binary Diagnostic: part 2")
	filename := "data.txt"
	// filename := "example.txt"

	width, err := tools.GetReportWidth(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}
	widthStr := fmt.Sprintf("%02d", width)
	format := "(%2d): %" + widthStr + "b\n"
	fmt.Printf("width: %d\n", width)

	report, err := tools.ReadBinaryNumbers(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	// ---[ OxygenGenerator ]------------------------------------------------------------------

	criteria := bitcriteria.NewOxygenGenerator()
	oxygenGeneratorRating, err := findRating(report, criteria, width, format)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}
	fmt.Printf("Oxygen Generator Rating:= %d\n", oxygenGeneratorRating)

	// ---[ CO2 Scrubber ]------------------------------------------------------------------

	criteria = bitcriteria.NewCo2Scrubber()
	co2ScrubberRating, err := findRating(report, criteria, width, format)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}
	fmt.Printf("CO2 Scrubber Rating:= %d\n", co2ScrubberRating)

	// ----------------------------------------------------------------------------------------

	lifeSupportRating := oxygenGeneratorRating * co2ScrubberRating
	fmt.Printf("life support rating:= %d\n", lifeSupportRating)
}

func findRating(report []int, criteria bitcriteria.BitCriteria, width int, format string) (int, error) {

	myMap := map[int]int{}
	for _, number := range report {
		myMap[number] = 0
	}

	for j := 0; j < width; j++ {
		k := (width - j - 1)
		mask := 1 << k
		numberOfOnes := 0

		for number := range myMap {
			if (number & mask) > 0 {
				numberOfOnes = numberOfOnes + 1
			}
		}

		requiredDigit := criteria.GetDigit(numberOfOnes, len(myMap))

		for number := range myMap {
			x := number & mask
			actualDigit := x >> k

			if requiredDigit != actualDigit {
				delete(myMap, number)
			}
		}

		if len(myMap) == 0 {
			return 0, fmt.Errorf("problem Finding Rating")
		}

		if len(myMap) == 1 {
			for result := range myMap {
				return result, nil
			}

			return 0, fmt.Errorf("problem Finding Rating")
		}
	}

	return 0, fmt.Errorf("problem Finding Rating")
}
