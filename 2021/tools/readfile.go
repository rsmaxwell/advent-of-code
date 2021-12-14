package tools

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func ReadLines(filename string) ([]string, error) {

	file, err := os.Open(filename)
	if err != nil {
		fmt.Printf("failed to open %s", filename)
		return nil, err
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanLines)
	var lines []string

	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}

	return lines, nil
}

func ReadNumbers(filename string) ([]int, error) {

	lines, err := ReadLines(filename)
	if err != nil {
		return nil, err
	}

	var numbers []int
	for _, line := range lines {
		number, err := strconv.Atoi(line)
		if err != nil {
			return nil, err
		}
		numbers = append(numbers, number)
	}

	return numbers, nil
}

func GetReportWidth(filename string) (int, error) {

	lines, err := ReadLines(filename)
	if err != nil {
		return 0, err
	}

	minimum := math.MaxInt
	maximum := math.MinInt

	for _, line := range lines {
		width := len(line)
		minimum = Min(minimum, width)
		maximum = Max(maximum, width)
	}

	if minimum != maximum {
		return 0, fmt.Errorf("report width varies between %d and %d", minimum, maximum)
	}

	return minimum, nil
}

func ReadBinaryNumbers(filename string) ([]int, error) {

	lines, err := ReadLines(filename)
	if err != nil {
		return nil, err
	}

	var numbers []int
	for _, line := range lines {
		number, err := strconv.ParseInt(line, 2, 64)
		if err != nil {
			return nil, err
		}
		numbers = append(numbers, int(number))
	}

	return numbers, nil
}