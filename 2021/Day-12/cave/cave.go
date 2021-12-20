package cave

import (
	"bytes"
	"fmt"
	"os"
	"strings"
	"unicode"

	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Caves struct {
	Map            map[string]*Cave
	NumberOfRoutes int
}

type Cave struct {
	Small       bool
	Connections []string
}

func NewCaves() *Caves {
	caves := &Caves{}
	caves.Map = make(map[string]*Cave)
	return caves
}

func ReadCaves(filename string) (*Caves, error) {

	lines, err := tools.ReadLines(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	caves := NewCaves()

	for _, line := range lines {

		words := strings.Split(line, "-")
		if len(words) != 2 {
			return nil, fmt.Errorf("unexpected number of caves")
		}

		for _, name := range words {
			_, ok := caves.Map[name]
			if !ok {
				caves.Map[name] = &Cave{Small: IsLower(name)}
			}
		}

		cave1 := caves.Map[words[0]]
		cave1.Connections = append(cave1.Connections, words[1])

		cave2 := caves.Map[words[1]]
		cave2.Connections = append(cave2.Connections, words[0])
	}

	return caves, nil
}

func (c *Cave) String() string {
	buf := new(bytes.Buffer)
	for _, connection := range c.Connections {
		buf.WriteString(fmt.Sprintf("%s ", connection))
	}
	return buf.String()
}

func IsLower(s string) bool {
	for _, r := range s {
		if !unicode.IsLower(r) && unicode.IsLetter(r) {
			return false
		}
	}
	return true
}

func (c *Cave) Visit(caves *Caves, path []string, indent int, numberOfSmallCavesVisitedTwice int) {

	for _, name := range c.Connections {

		if name == "start" {
			continue
		}

		if name == "end" {
			caves.NumberOfRoutes += 1
			for _, x := range path {
				fmt.Printf("%s ", x)
			}
			fmt.Println("end")
			continue
		}

		n := numberOfSmallCavesVisitedTwice
		next := caves.Map[name]
		if next.Small {
			count := CountSubStrings(path, name)
			if count == 0 {
			} else if count == 1 {
				if n == 0 {
					n += 1
				} else if n > 0 {
					continue
				}
			} else if count > 1 {
				continue
			}
		}

		next.Visit(caves, append(path, name), indent+1, n)
	}
}

func CountSubStrings(s []string, str string) int {
	count := 0
	for _, v := range s {
		if v == str {
			count += 1
		}
	}

	return count
}
