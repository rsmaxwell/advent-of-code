package code

import (
	"fmt"
	"strings"

	"github.com/rsmaxwell/advent-of-code/2021/Day-10/myerrors"
	"github.com/rsmaxwell/advent-of-code/2021/stack"
)

type Operation interface {
	Perform(c *Compiler) error
}

type Compiler struct {
	Stack    stack.Stack
	Handlers map[string]Operation
}

func New() *Compiler {

	compiler := Compiler{}
	compiler.Handlers = map[string]Operation{
		"(": Push{")"},
		"[": Push{"]"},
		"{": Push{"}"},
		"<": Push{">"},
		")": Pop{")"},
		"]": Pop{"]"},
		"}": Pop{"}"},
		">": Pop{">"},
	}
	return &compiler
}

func (c *Compiler) Compile(line string) error {

	c.Stack.Clear()

	for _, ch := range strings.Split(line, "") {

		handler := c.Handlers[ch]
		if handler == nil {
			return fmt.Errorf("unexpected character: %s", ch)
		}

		err := handler.Perform(c)
		if err != nil {
			return err
		}
	}

	if !c.Stack.IsEmpty() {

		ch := ""
		total := 0
		missing := ""
		for ok := true; ok; {
			ch, ok = c.Stack.Pop()
			if ok {
				missing += ch
				total *= 5
				total += lookup[ch]
			}
		}

		message := fmt.Sprintf("incomplete. missing: %s, score: %d", missing, total)
		return &myerrors.IncompleteError{Message: message, Score: total}
	}

	return nil
}

var (
	lookup map[string]int
)

func init() {
	lookup = make(map[string]int)
	lookup[")"] = 1
	lookup["]"] = 2
	lookup["}"] = 3
	lookup[">"] = 4
}
