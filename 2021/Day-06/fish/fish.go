package fish

import (
	"bytes"
	"fmt"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Fishes struct {
	Array []*Fish
}

type Fish struct {
	Count int64
}

func (f *Fish) String() string {
	return fmt.Sprintf("Fish: Count:%d", f.Count)
}

func ReadFishes(filename string) (*Fishes, error) {

	fishes := make([]*Fish, 9)
	for i := 0; i < 9; i++ {
		fishes[i] = &Fish{Count: 0}
	}

	numbers, err := tools.CommaSeparatedValuesToNumbers(filename)
	if err != nil {
		return nil, err
	}

	for _, number := range numbers {
		fish := fishes[number]
		fish.Count = fish.Count + 1
	}

	return &Fishes{Array: fishes}, nil
}

func (f *Fishes) Step() {

	zero := f.Array[0]

	for i := 1; i < 9; i++ {
		f.Array[i-1] = f.Array[i]
	}

	f.Array[6].Count = f.Array[6].Count + zero.Count
	f.Array[8] = &Fish{Count: +zero.Count}
}

func (f *Fishes) Total() int64 {

	total := *new(int64)
	for i := 0; i < 9; i++ {
		total = total + f.Array[i].Count
	}

	return total
}

func (f *Fishes) String() string {

	buf := new(bytes.Buffer)
	for i := 0; i < 9; i++ {
		text := fmt.Sprintf("(%d,%d) ", i, f.Array[i].Count)
		buf.WriteString(text)
	}
	return buf.String()
}
