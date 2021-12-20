package main

import "fmt"

type FoldVertical struct {
	Value int
}

func (f FoldVertical) Fold(p *Paper) {
	for k := 0; k < p.Width/2; k++ {
		x1 := p.Width/2 - k - 1
		x2 := p.Width/2 + k + 1
		for y := 0; y < p.Height; y++ {
			if p.Get(x2, y) {
				p.Set(x1, y, true)
			}
		}
	}
	p.Width = p.Width / 2
}

func (i FoldVertical) String() string {
	return fmt.Sprintf("fold along x=%d", i.Value)
}
