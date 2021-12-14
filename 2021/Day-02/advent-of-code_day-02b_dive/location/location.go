package location

import "fmt"

type Location struct {
	Position int
	Depth    int
	Aim      int
}

func New(position int, depth int, aim int) Location {
	return Location{Position: position, Depth: depth, Aim: aim}
}

func (loc Location) String() string {
	return fmt.Sprintf("position: %d, depth: %d, aim: %d\n", loc.Position, loc.Depth, loc.Aim)
}
