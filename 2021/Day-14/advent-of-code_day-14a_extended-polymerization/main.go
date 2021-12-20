package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/lists"
)

func main() {
	fmt.Println("Advent Of Code: Day 14 - Extended Polymerization: part 1")
	// filename := "data.txt"
	filename := "example.txt"

	template, instructions, err := ReadData(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	fmt.Println(template.String())
	fmt.Println("")
	instructions.Print()

	for i := 0; i < 40; i++ {
		err := Step(template, instructions)
		if err != nil {
			fmt.Println(err.Error())
			os.Exit(1)
		}

		if i < 5 {
			text := template.String()
			fmt.Printf("After step %d: len: %-15d: %s\n", i+1, template.Size(), text)
		} else {
			fmt.Printf("After step %d: len: %-15d: ...\n", i+1, template.Size())
		}
	}

	template.Analyse()
}

func Step(template *Template, instructions *Instructions) error {
	iterator := lists.NewIterator(template.List)

	for iterator.HasNext() {
		thisNode, err := iterator.Next()
		if err != nil {
			return err
		}

		nextNode := thisNode.Next
		if nextNode == nil {
			continue
		}

		for _, instruction := range instructions.List {
			if (instruction.Element1 == thisNode.Data) && (instruction.Element2 == nextNode.Data) {
				iterator.Add(instruction.Element3)
			}
		}
	}

	return nil
}
