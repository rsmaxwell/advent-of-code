package tools

import (
	"strconv"
	"strings"
)

func Min(v1 int, vn ...int) int {
	m := v1
	for i := 0; i < len(vn); i++ {
		if vn[i] < m {
			m = vn[i]
		}
	}
	return m
}

func Max(v1 int, vn ...int) int {
	m := v1
	for i := 0; i < len(vn); i++ {
		if vn[i] > m {
			m = vn[i]
		}
	}
	return m
}

func CommaSeparatedValuesToNumbers(line string) ([]int, error) {

	numbers := []int{}
	words := strings.Split(line, ",")
	for _, word := range words {
		n, err := strconv.Atoi(word)
		if err != nil {
			return nil, err
		}
		numbers = append(numbers, n)
	}

	return numbers, nil
}

func SpaceSeparatedValuesToNumbers(line string) ([]int, error) {

	numbers := []int{}
	words := strings.Fields(line)
	for _, word := range words {
		n, err := strconv.Atoi(word)
		if err != nil {
			return nil, err
		}
		numbers = append(numbers, n)
	}

	return numbers, nil
}

// Abs returns the absolute value of x.
func Abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
