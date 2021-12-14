package coordinate

import (
	"fmt"
	"strconv"
	"strings"
)

type Coordinate struct {
	X int
	Y int
}

func New(x int, y int) Coordinate {
	return Coordinate{X: x, Y: y}
}

func (coord *Coordinate) String() string {
	return fmt.Sprintf("%d,%d", coord.X, coord.Y)
}

func Parse(text string) (*Coordinate, error) {
	words := strings.Split(text, ",")

	if len(words) != 2 {
		return nil, fmt.Errorf("unexpected data: %s", text)
	}

	x, err := strconv.Atoi(words[0])
	if err != nil {
		return nil, err
	}

	y, err := strconv.Atoi(words[1])
	if err != nil {
		return nil, err
	}

	c := Coordinate{X: x, Y: y}
	return &c, nil
}
