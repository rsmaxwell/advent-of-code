package main

import (
	"fmt"
	"math"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Paper struct {
	Sheet  map[string]bool
	Width  int
	Height int
}

func key(x, y int) string {
	return fmt.Sprintf("%d,%d", x, y)
}

func ReadPaper(block tools.Block) (*Paper, error) {

	sheet := make(map[string]bool)
	paper := &Paper{Sheet: sheet}

	maxX := math.MinInt
	maxY := math.MinInt

	for _, line := range block.Lines {
		numbers, err := tools.CommaSeparatedValuesToNumbers(line)
		if err != nil {
			return nil, err
		}
		if len(numbers) != 2 {
			return nil, fmt.Errorf("unexpected number of numbers")
		}

		x := numbers[0]
		y := numbers[1]

		maxX = tools.Max(maxX, x)
		maxY = tools.Max(maxY, y)

		paper.Set(x, y, true)
	}

	paper.Width = maxX + 1
	paper.Height = maxY + 1
	return paper, nil
}

func (p *Paper) Get(x int, y int) bool {
	return p.Sheet[key(x, y)]
}

func (p *Paper) Set(x, y int, value bool) {
	p.Sheet[key(x, y)] = value
}

func (p *Paper) Clear(x, y int) {
	delete(p.Sheet, key(x, y))
}

func (p *Paper) FoldHorizontal(y int) {
}

func (p *Paper) FoldVertical(x int) {
}

func (p *Paper) Print() {

	count := 0
	for y := 0; y < p.Height; y++ {
		for x := 0; x < p.Width; x++ {
			ch := "."
			if p.Get(x, y) {
				ch = "#"
				count += 1
			}
			fmt.Printf("%s", ch)
		}
		fmt.Println("")
	}
	fmt.Printf("Number of visible dots: %d\n", count)
}
