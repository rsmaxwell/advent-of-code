package location

import "fmt"

type Location struct {
	Position int
	Depth    int
}

func New(position int, depth int) Location {
	return Location{Position: position, Depth: depth}
}

func (loc Location) String() string {
	return fmt.Sprintf("position: %d, depth: %d\n", loc.Position, loc.Depth)
}
