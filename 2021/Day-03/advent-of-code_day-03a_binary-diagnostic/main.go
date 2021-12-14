package main

import (
	"fmt"
	"os"
	"strconv"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

func main() {
	fmt.Println("Advent Of Code: Day 3 - Binary Diagnostic: part 1")
	filename := "data.txt"
	// filename := "example.txt"

	width, err := tools.GetReportWidth(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}
	widthStr := fmt.Sprintf("%02d", width)
	format := "(%2d): %" + widthStr + "b\n"

	report, err := tools.ReadBinaryNumbers(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	frequencyArray := GetFrequencyArray(report, width, format)

	gammaString := ""
	epsilonString := ""
	for j := 0; j < width; j++ {
		freq := frequencyArray[j]

		if 2*freq > len(report) {
			gammaString = "1" + gammaString
		} else {
			gammaString = "0" + gammaString
		}

		if 2*freq < len(report) {
			epsilonString = "1" + epsilonString
		} else {
			epsilonString = "0" + epsilonString
		}
	}

	number, err := strconv.ParseInt(gammaString, 2, 64)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}
	gamma := int(number)

	number, err = strconv.ParseInt(epsilonString, 2, 64)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}
	epsilon := int(number)

	fmt.Printf("gamma:   %d\n", gamma)
	fmt.Printf("epsilon: %d\n", epsilon)

	power := gamma * epsilon
	fmt.Printf("power:   %d\n", power)
}

func GetFrequencyArray(report []int, width int, format string) []int {
	frequencyArray := make([]int, width)
	for i, number := range report {
		fmt.Printf(format, i, number)
		for j := 0; j < width; j++ {
			mask := 1 << j
			result := number & mask
			if result > 0 {
				frequencyArray[j] = frequencyArray[j] + 1
			}
		}
	}
	return frequencyArray
}
