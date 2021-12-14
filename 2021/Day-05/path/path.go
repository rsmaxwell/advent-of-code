package path

import (
	"fmt"
	"strings"

	"github.com/rsmaxwell/advent-of-code/2021/Day-05/coordinate"
	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Path struct {
	From *coordinate.Coordinate
	To   *coordinate.Coordinate
}

func New(from *coordinate.Coordinate, to *coordinate.Coordinate) *Path {
	return &Path{From: from, To: to}
}

func (p *Path) String() string {
	return fmt.Sprintf("%s -> %s", p.From, p.To)
}

func (p *Path) AddArea(other *Path) *Path {

	x1 := tools.Min(p.From.X, p.To.X, other.From.X, other.To.X)
	x2 := tools.Max(p.From.X, p.To.X, other.From.X, other.To.X)

	y1 := tools.Min(p.From.Y, p.To.Y, other.From.Y, other.To.Y)
	y2 := tools.Max(p.From.Y, p.To.Y, other.From.Y, other.To.Y)

	from := &coordinate.Coordinate{X: x1, Y: y1}
	to := &coordinate.Coordinate{X: x2, Y: y2}

	return &Path{From: from, To: to}
}

func (p *Path) IsHorizontalOrVertical() bool {

	ok := false

	if p.From.X == p.To.X {
		ok = true
	}

	if p.From.Y == p.To.Y {
		ok = true
	}

	return ok
}

func Parse(text string) (*Path, error) {
	words := strings.Split(text, " -> ")

	if len(words) != 2 {
		return nil, fmt.Errorf("unexpected data: %s", text)
	}

	from, err := coordinate.Parse(words[0])
	if err != nil {
		return nil, err
	}

	to, err := coordinate.Parse(words[1])
	if err != nil {
		return nil, err
	}

	return &Path{From: from, To: to}, nil
}

func (p *Path) ContainsPoint(x int, y int) (bool, error) {

	numSteps := 1
	xStep := 0
	if p.From.X < p.To.X {
		numSteps = p.To.X - p.From.X + 1
		xStep = 1
	} else if p.From.X > p.To.X {
		numSteps = p.From.X - p.To.X + 1
		xStep = -1
	}

	yStep := 0
	if p.From.Y < p.To.Y {
		numSteps = p.To.Y - p.From.Y + 1
		yStep = 1
	} else if p.From.Y > p.To.Y {
		numSteps = p.From.Y - p.To.Y + 1
		yStep = -1
	}

	for i := 0; i < numSteps; i++ {

		xx := p.From.X + i*xStep
		yy := p.From.Y + i*yStep

		if x == xx {
			if y == yy {
				return true, nil
			}
		}
	}

	return false, nil
}
