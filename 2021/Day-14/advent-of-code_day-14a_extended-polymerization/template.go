package main

import (
	"bytes"
	"fmt"
	"sort"
	"strings"

	"github.com/rsmaxwell/advent-of-code/2021/lists"
	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Template struct {
	List *lists.LinkedList
}

func ReadTemplate(block tools.Block) (*Template, error) {

	N := len(block.Lines)
	if N != 1 {
		return nil, fmt.Errorf("unexpected number of lines: %d", N)
	}
	line := block.Lines[0]

	list := lists.NewLinkedList()
	words := strings.Split(line, "")
	for _, word := range words {
		list.AddLast(word)
	}
	return &Template{List: list}, nil
}

func (t *Template) String() string {
	buf := &bytes.Buffer{}

	iterator := lists.NewIterator(t.List)
	for iterator.HasNext() {
		temp, _ := iterator.Next()
		buf.WriteString(temp.Data)
	}

	return buf.String()
}

func (t *Template) Size() int {
	count := 0
	iterator := lists.NewIterator(t.List)
	for iterator.HasNext() {
		iterator.Next()
		count += 1
	}
	return count
}

func (t *Template) Analyse() {

	counts := map[string]int{}

	iterator := lists.NewIterator(t.List)
	for iterator.HasNext() {
		temp, _ := iterator.Next()
		count := counts[temp.Data]
		counts[temp.Data] = count + 1
	}

	for key, value := range counts {
		fmt.Printf("%s : %d\n", key, value)
	}
	fmt.Println("")

	numbers := []int64{}
	for _, count := range counts {
		numbers = append(numbers, int64(count))
	}

	// sort.Ints(array)
	sort.Slice(numbers, func(i, j int) bool {
		return numbers[i] < numbers[j]
	})

	for _, number := range numbers {
		fmt.Printf("%d ", number)
	}
	fmt.Println("")

	largest := numbers[len(numbers)-1]
	smallest := numbers[0]

	answer := largest - smallest
	fmt.Printf("answer: %d\n", answer)
}
