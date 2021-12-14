package commands

import (
	"fmt"

	"github.com/rsmaxwell/advent-of-code/2021/Day-02/advent-of-code_day-02b_dive/location"
)

// ---[ factory ]---------------------------------------------------------------------------

type DownFactory struct {
}

func (f DownFactory) Make(value int) Command {
	return NewDown(value)
}

// ---[ instance ]---------------------------------------------------------------------------

type Down struct {
	Value int
}

func NewDown(value int) Down {
	return Down{Value: value}
}

func (cmd Down) Move(loc location.Location) location.Location {
	return location.New(loc.Position, loc.Depth, loc.Aim+cmd.Value)
}

func (cmd Down) String() string {
	return fmt.Sprintf("Down %d", cmd.Value)
}
