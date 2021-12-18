package cave

import (
	"fmt"
	"sort"
	"strconv"
	"strings"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Cave struct {
	numRows    int
	numCols    int
	caveMap    map[string]*Location
	basinSizes []int
}

type Location struct {
	Height   int
	BeenHere bool
}

var (
	deltaRow []int
	deltaCol []int
)

func init() {
	deltaRow = []int{-1, 0, 1, 0}
	deltaCol = []int{0, 1, 0, -1}
}

func ReadCave(filename string) (*Cave, error) {
	lines, err := tools.ReadLines(filename)
	if err != nil {
		return nil, err
	}

	minimum, maximum := tools.GetLinesWidth(lines)
	if minimum != maximum {
		return nil, fmt.Errorf("width varies between %d and %d", minimum, maximum)
	}
	width := minimum
	length := len(lines)

	cavemap := make(map[string]*Location)
	for row, line := range lines {
		fmt.Println(line)
		words := strings.Split(line, "")
		for col, word := range words {
			height, err := strconv.Atoi(word)
			if err != nil {
				return nil, err
			}
			cavemap[key(row, col)] = &Location{Height: height}
		}
	}

	return &Cave{numRows: length, numCols: width, caveMap: cavemap}, nil
}

func key(row, col int) string {
	return fmt.Sprintf("%d,%d", row, col)
}

func (c *Cave) GetLocation(row, col int) *Location {
	return c.caveMap[key(row, col)]
}

func (c *Cave) Height(row, col int) int {
	return c.GetLocation(row, col).Height
}

func (c *Cave) BeenHere(row, col int) bool {
	return c.GetLocation(row, col).BeenHere
}

func (c *Cave) IsLowPoint(row, col int) (bool, int) {

	height := c.Height(row, col)

	for i := 0; i < 4; i++ {
		rr := row + deltaRow[i]
		cc := col + deltaCol[i]
		if (rr >= 0) && (rr < c.numRows) && (cc >= 0) && (cc < c.numCols) {
			h := c.Height(rr, cc)
			if height >= h {
				return false, height
			}
		}
	}

	return true, height
}

func (c *Cave) FindLowPointRisk() int {

	total := 0

	for row := 0; row < c.numRows; row++ {
		for col := 0; col < c.numCols; col++ {
			isLowPoint, height := c.IsLowPoint(row, col)
			if isLowPoint {

				fmt.Printf("LowPoint at (%d,%d): height: %d\n", row, col, height)

				risk := height + 1
				total += risk

				size := 0
				c.FindBasin(&size, row, col)
				fmt.Printf("Basin size: %d\n\n", size)
				c.basinSizes = append(c.basinSizes, size)
			}
		}
	}

	return total
}

func (c *Cave) FindBasin(size *int, row int, col int) {

	location := c.GetLocation(row, col)
	location.BeenHere = true
	*size += 1

	height := c.Height(row, col)

	for i := 0; i < 4; i++ {
		rr := row + deltaRow[i]
		cc := col + deltaCol[i]
		if (rr >= 0) && (rr < c.numRows) && (cc >= 0) && (cc < c.numCols) {
			if c.BeenHere(rr, cc) {
				continue
			}
			h := c.Height(rr, cc)
			if h >= 9 {
				continue
			}
			if h <= height {
				continue
			}

			c.FindBasin(size, rr, cc)
		}
	}
}

func (c *Cave) ListBasinSizes() {

	sort.Sort(sort.Reverse(sort.IntSlice(c.basinSizes)))

	fmt.Println("")
	fmt.Printf("basinSizes: ")

	result := 1
	for i, size := range c.basinSizes {
		fmt.Printf("%d ", size)
		if i < 3 {
			result *= size
		}
	}
	fmt.Println("")
	fmt.Printf("product of the 3 largest basin sizes: %d\n", result)

}
