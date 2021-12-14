package bitcriteria

import "fmt"

type OxygenGenerator struct {
}

func NewOxygenGenerator() BitCriteria {
	return OxygenGenerator{}
}

func (cmd OxygenGenerator) GetDigit(numberOfOnes int, total int) int {

	if 2*numberOfOnes > total {
		return 1
	}

	if 2*numberOfOnes == total {
		return 1
	}

	return 0
}

func (cmd OxygenGenerator) String() string {
	return fmt.Sprintln("OxygenGenerator")
}
