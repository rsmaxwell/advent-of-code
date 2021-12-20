package main

import "fmt"

type FoldHorizontal struct {
	Value int
}

func (f FoldHorizontal) Fold(p *Paper) {
	for x := 0; x < p.Width; x++ {
		for k := 0; k < p.Height/2; k++ {
			y1 := p.Height/2 - k - 1
			y2 := p.Height/2 + k + 1
			if p.Get(x, y2) {
				p.Set(x, y1, true)
				p.Clear(x, y2)
			}
		}
	}
	p.Height = p.Height / 2
}

func (i FoldHorizontal) String() string {
	return fmt.Sprintf("fold along y=%d", i.Value)
}
