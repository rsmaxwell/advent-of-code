package code

import (
	"fmt"

	"github.com/rsmaxwell/advent-of-code/2021/Day-10/myerrors"
)

type Pop struct {
	ch string
}

func (o Pop) Perform(c *Compiler) error {
	ch, ok := c.Stack.Pop()
	if !ok {
		return fmt.Errorf("could not pop from empty stack")
	}
	if ch != o.ch {
		message := fmt.Sprintf("expected %s, but found %s instead", ch, o.ch)
		return &myerrors.SyntaxError{Char: o.ch, Message: message}
	}

	return nil
}
