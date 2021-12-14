package game

import (
	"bytes"
	"fmt"
	"strings"

	"github.com/rsmaxwell/advent-of-code/2021/Day-04/advent-of-code_day-04b_giant-squid/board"
	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Game struct {
	Numbers []int
	Boards  []board.Board
	HasWon  bool
	Turn    int
}

func ReadGame(filename string) (*Game, error) {

	blocks, err := tools.ReadBlocks(filename)
	if err != nil {
		fmt.Println(err.Error())
		return nil, err
	}

	//	for i, block := range blocks {
	//		fmt.Printf("[%d]\n", i)
	//		for j, line := range block.Lines {
	//			fmt.Printf("    [%d]: %s\n", j, line)
	//		}
	//	}

	if len(blocks) < 2 {
		err = fmt.Errorf("not enough data")
		return nil, err
	}

	numbers, err := blocks[0].ToNumbers()
	if err != nil {
		return nil, err
	}

	g := Game{Numbers: numbers}
	for i := 1; i < len(blocks); i++ {
		block := blocks[i]
		b := board.New(i)
		for row, line := range block.Lines {
			array, err := tools.SpaceSeparatedValuesToNumbers(line)
			if err != nil {
				return nil, err
			}

			for col, value := range array {
				b.Set(row, col, value)
			}
		}
		g.Boards = append(g.Boards, b)
	}

	return &g, nil
}

func (g *Game) String() string {
	buf := new(bytes.Buffer)

	buf.WriteString(strings.Trim(strings.Join(strings.Fields(fmt.Sprint(g.Numbers)), ","), "[]"))
	buf.WriteString("\n")

	for _, b := range g.Boards {
		buf.WriteString("\n")
		buf.WriteString(b.String())
	}

	return buf.String()
}

func (g *Game) FirstTurn() error {
	g.Turn = 0
	return g.NextTurn()
}

func (g *Game) NextTurn() error {

	number := g.Numbers[g.Turn]
	g.Turn = g.Turn + 1

	for _, b := range g.Boards {
		err := b.NextTurn(number)
		if err != nil {
			return err
		}
	}

	return nil
}

func (g *Game) CalcHasWon() error {

	for index, b := range g.Boards {

		ok, err := b.CalcHasWon()
		if err != nil {
			return err
		}
		if ok {
			g.HasWon = true
			number := g.Numbers[g.Turn-1]

			score, err := b.CalcScore(number)
			if err != nil {
				return err
			}

			fmt.Printf("Board %d has won with a score of %d\n", index+1, score)
		}
	}

	return nil
}
