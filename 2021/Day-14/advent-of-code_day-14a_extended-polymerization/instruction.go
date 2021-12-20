package main

import (
	"fmt"
	"strings"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Instructions struct {
	List []*Instruction
}

type Instruction struct {
	Element1 string
	Element2 string
	Element3 string
}

func ReadInstructions(block tools.Block) (*Instructions, error) {

	list := make([]*Instruction, 0)

	for _, line := range block.Lines {

		words := strings.Split(line, " -> ")
		if len(words) != 2 {
			return nil, fmt.Errorf("unexpected instruction(1): [%s]", line)
		}

		if len(words[1]) != 1 {
			return nil, fmt.Errorf("unexpected instruction(2): [%s]", line)
		}

		words2 := strings.Split(words[0], "")
		if len(words2) != 2 {
			return nil, fmt.Errorf("unexpected instruction(3): [%s]", line)
		}

		instruction := Instruction{Element1: words2[0], Element2: words2[1], Element3: words[1]}

		list = append(list, &instruction)
	}

	return &Instructions{List: list}, nil
}

func (list *Instructions) Print() {

	for _, i := range list.List {
		fmt.Printf("%s\n", i.String())
	}
}

func (i *Instruction) String() string {
	return fmt.Sprintf("%s%s -> %s", i.Element1, i.Element2, i.Element3)
}

func (i *Instruction) Apply(t *Template) {
}
