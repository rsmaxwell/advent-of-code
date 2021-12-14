package tools

import (
	"bufio"
	"fmt"
	"os"
)

type Block struct {
	Lines []string
}

func ReadBlocks(filename string) ([]Block, error) {

	file, err := os.Open(filename)
	if err != nil {
		fmt.Printf("failed to open %s", filename)
		return nil, err
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanLines)

	var blocks []Block

	block := Block{}
	for scanner.Scan() {
		line := scanner.Text()
		if len(line) == 0 {
			blocks = append(blocks, block)
			block = Block{}
			continue
		}
		block.Append(line)
	}
	blocks = append(blocks, block)

	return blocks, nil
}

func (b *Block) Append(line string) {
	b.Lines = append(b.Lines, line)
}

func (b *Block) ToNumbers() ([]int, error) {

	if len(b.Lines) != 1 {
		err := fmt.Errorf("unexpected data")
		return nil, err
	}

	line := b.Lines[0]
	return CommaSeparatedValuesToNumbers(line)
}
