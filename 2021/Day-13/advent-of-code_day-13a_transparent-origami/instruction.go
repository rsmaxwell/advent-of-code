package main

import (
	"fmt"
	"strconv"
	"strings"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Instructions struct {
	List []Instruction
}

type Instruction interface {
	Fold(p *Paper)
	String() string
}

func ReadInstructions(block tools.Block) (*Instructions, error) {

	list := make([]Instruction, 0)

	for _, line := range block.Lines {

		words := strings.Split(line, " ")
		if len(words) != 3 {
			return nil, fmt.Errorf("unexpected instruction(1): [%s]", line)
		}

		words2 := strings.Split(words[2], "=")
		if len(words2) != 2 {
			return nil, fmt.Errorf("unexpected instruction(2): [%s]", line)
		}

		var instruction Instruction
		text := words2[1]
		value, err := strconv.Atoi(text)
		if err != nil {
			return nil, err
		}
		if words2[0] == "x" {
			instruction = FoldVertical{Value: value}
		} else if words2[0] == "y" {
			instruction = FoldHorizontal{Value: value}
		} else {
			return nil, fmt.Errorf("unexpected instruction(3): [%s]", line)
		}

		list = append(list, instruction)
	}

	return &Instructions{List: list}, nil
}

func (list *Instructions) Print() {

	for _, i := range list.List {
		fmt.Printf("%s\n", i.String())
	}
}
