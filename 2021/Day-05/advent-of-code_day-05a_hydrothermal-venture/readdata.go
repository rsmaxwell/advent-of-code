package main

import (
	"fmt"

	"github.com/rsmaxwell/advent-of-code/2021/Day-05/path"
	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

func ReadData(filename string) ([]*path.Path, error) {
	lines, err := tools.ReadLines(filename)
	if err != nil {
		return nil, err
	}

	paths := []*path.Path{}
	for _, line := range lines {
		p, err := path.Parse(line)
		if err != nil {
			return nil, err
		}
		if p.IsHorizontalOrVertical() {
			paths = append(paths, p)
		} else {
			fmt.Printf("skipping path: %s\n", p.String())
		}
	}

	return paths, nil
}
