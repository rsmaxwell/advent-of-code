package board

import (
	"bytes"
	"fmt"

	"github.com/rsmaxwell/advent-of-code/2021/Day-04/advent-of-code_day-04b_giant-squid/square"
)

type Board struct {
	Index   int
	Squares map[string]*square.Square
}

func New(index int) Board {
	b := Board{Index: index}
	b.Squares = make(map[string]*square.Square)
	return b
}

func key(row int, col int) string {
	return fmt.Sprintf("%d,%d", row, col)
}

func (b *Board) Set(row int, col int, value int) {
	b.Squares[key(row, col)] = &square.Square{Value: value}
}

func (b *Board) Mark(row int, col int, mark bool) error {
	s, ok := b.Squares[key(row, col)]
	if !ok {
		return fmt.Errorf("missing square (%d,%d", row, col)
	}
	s.Mark = mark
	return nil
}

func (b *Board) Get(row int, col int) *square.Square {
	s, ok := b.Squares[key(row, col)]
	if !ok {
		return nil
	}
	return s
}

func (b *Board) String() string {

	buf1 := new(bytes.Buffer)
	buf2 := new(bytes.Buffer)

	for row := 0; row < 5; row++ {

		buf2.Reset()
		for col := 0; col < 5; col++ {
			s := b.Get(row, col)
			// -----------------------------------------------------
			text := "   "
			if s != nil {
				text = fmt.Sprintf("%2d ", s.Value)
			}
			buf1.WriteString(text)
			// -----------------------------------------------------
			text = "   "
			if s != nil {
				if s.Mark {
					text = " X "
				} else {
					text = " . "
				}
			}
			buf2.WriteString(text)
		}
		buf1.WriteString("          ")
		buf1.WriteString(buf2.String())
		buf1.WriteString("\n")
	}

	return buf1.String()
}

func (b *Board) CalcHasWon() (bool, error) {

	for row := 0; row < 5; row++ {
		ok, err := b.winningRow(row)
		if err != nil {
			return false, err
		}
		if ok {
			return true, nil
		}
	}

	for col := 0; col < 5; col++ {
		ok, err := b.winningCol(col)
		if err != nil {
			return false, err
		}
		if ok {
			return true, nil
		}
	}

	return false, nil
}

func (b *Board) winningRow(row int) (bool, error) {
	// fmt.Fprintf(os.Stderr, "board.winningRow(row: %d): board.Index: %d -------------------------------------------\n", row, b.Index)
	for col := 0; col < 5; col++ {
		s := b.Get(row, col)
		if s == nil {
			return false, fmt.Errorf("missing square (%d,%d", row, col)
		}
		if !s.Mark {
			return false, nil
		}
		// fmt.Fprintf(os.Stderr, "board.winningRow(row: %d): square:{Value: %d, Mark: %t}\n", row, s.Value, s.Mark)
	}
	return true, nil
}

func (b *Board) winningCol(col int) (bool, error) {
	for row := 0; row < 5; row++ {
		s := b.Get(row, col)
		if s == nil {
			return false, fmt.Errorf("missing square (%d,%d", row, col)
		}
		if !s.Mark {
			return false, nil
		}
	}
	return true, nil
}

func (b *Board) NextTurn(number int) error {

	for row := 0; row < 5; row++ {
		for col := 0; col < 5; col++ {
			s := b.Get(row, col)
			if s == nil {
				return fmt.Errorf("missing square (%d,%d", row, col)
			}
			if s.Value == number {
				s.Mark = true
			}
		}
	}

	return nil
}

func (b *Board) CalcScore(number int) (int, error) {

	total := 0
	for row := 0; row < 5; row++ {
		for col := 0; col < 5; col++ {
			s := b.Get(row, col)
			if s == nil {
				return 0, fmt.Errorf("missing square (%d,%d", row, col)
			}
			if !s.Mark {
				total = total + s.Value
			}
		}
	}

	return total * number, nil
}
