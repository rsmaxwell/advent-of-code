package tools

import (
	"fmt"
)

func CountLarger(array []int, verbose bool) (int, error) {
	count := 0
	for i, number := range array {
		if i == 0 {
			fmt.Printf("%d (N/A - no previous measurement)\n", i)
			continue
		}

		previous := array[i-1]
		if number < previous {
			Debugf(verbose, "%d (decreased)\n", number)
		} else if number > previous {
			Debugf(verbose, "%d (increased)\n", number)
			count = count + 1
		} else {
			Debugf(verbose, "%d (no change)\n", number)
		}
	}

	return count, nil
}
