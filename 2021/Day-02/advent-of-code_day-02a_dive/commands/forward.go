package commands

import (
	"fmt"

	"github.com/rsmaxwell/advent-of-code/2021/Day-02/advent-of-code_day-02a_dive/location"
)

// ---[ factory ]---------------------------------------------------------------------------

type ForwardFactory struct {
}

func (f ForwardFactory) Make(value int) Command {
	return NewForward(value)
}

// ---[ instance ]---------------------------------------------------------------------------

type Forward struct {
	Value int
}

func NewForward(value int) Forward {
	return Forward{Value: value}
}

func (cmd Forward) Move(loc location.Location) location.Location {
	return location.New(loc.Position+cmd.Value, loc.Depth)
}

func (cmd Forward) String() string {
	return fmt.Sprintf("Forward %d", cmd.Value)
}
