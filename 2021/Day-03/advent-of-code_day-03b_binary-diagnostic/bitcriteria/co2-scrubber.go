package bitcriteria

import "fmt"

type Co2Scrubber struct {
}

func NewCo2Scrubber() BitCriteria {
	return Co2Scrubber{}
}

func (cmd Co2Scrubber) GetDigit(numberOfOnes int, total int) int {

	if 2*numberOfOnes > total {
		return 0
	}

	if 2*numberOfOnes == total {
		return 0
	}

	return 1
}

func (cmd Co2Scrubber) String() string {
	return fmt.Sprintln("CO2 Scrubber")
}
