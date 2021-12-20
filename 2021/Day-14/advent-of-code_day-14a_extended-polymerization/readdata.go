package main

import (
	"fmt"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

func ReadData(filename string) (*Template, *Instructions, error) {

	blocks, err := tools.ReadBlocks(filename)
	if err != nil {
		return nil, nil, err
	}
	if len(blocks) != 2 {
		return nil, nil, fmt.Errorf("unexpected number of blocks: %d", len(blocks))
	}

	template, err := ReadTemplate(blocks[0])
	if err != nil {
		return nil, nil, err
	}

	instructions, err := ReadInstructions(blocks[1])
	if err != nil {
		return nil, nil, err
	}

	return template, instructions, nil
}
