package commands

import (
	"fmt"

	"github.com/rsmaxwell/advent-of-code/2021/Day-02/advent-of-code_day-02b_dive/location"
)

// ---[ factory ]---------------------------------------------------------------------------

type UpFactory struct {
}

func (f UpFactory) Make(value int) Command {
	return NewUp(value)
}

// ---[ instance ]---------------------------------------------------------------------------

type Up struct {
	Value int
}

func NewUp(value int) Up {
	return Up{Value: value}
}

func (cmd Up) Move(loc location.Location) location.Location {
	return location.New(loc.Position, loc.Depth, loc.Aim-cmd.Value)
}

func (cmd Up) String() string {
	return fmt.Sprintf("Up %d", cmd.Value)
}
