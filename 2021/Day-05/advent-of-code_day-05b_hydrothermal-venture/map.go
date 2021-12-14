package main

import (
	"bytes"
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/Day-05/path"
)

func TestPrintMap(area *path.Path, paths []*path.Path, verbose bool) error {

	fmt.Println("TestPrintMap()")

	row := 0
	col := 2
	count, err := processSquare(row, col, paths, true)
	if err != nil {
		return err
	}

	fmt.Printf("count: %d\n", count)

	return nil
}

func PrintMap(area *path.Path, paths []*path.Path, verbose bool) error {

	total := 0

	// if verbose {
	// 	fmt.Println("PrintMap()")
	// }
	buf := new(bytes.Buffer)

	for row := area.From.Y; row <= area.To.Y; row++ {

		fmt.Fprintf(os.Stderr, "row: %d\n", row)

		buf.Reset()

		for col := area.From.X; col <= area.To.X; col++ {
			count, err := processSquare(row, col, paths, false)
			if err != nil {
				return err
			}

			if count > 1 {
				total = total + 1
			}

			if verbose {
				char := "."
				if count > 9 {
					char = "*"
				} else if count >= 1 {
					char = fmt.Sprintf("%d", count)
				}

				buf.WriteString(char)
			}
		}
		if verbose {
			fmt.Println(buf.String())
		}
	}

	fmt.Printf("total: %d\n", total)

	return nil
}

func processSquare(row int, col int, paths []*path.Path, verbose bool) (int, error) {

	if verbose {
		fmt.Fprintf(os.Stderr, "square: %d,%d\n", col, row)
	}

	total := 0
	for _, p := range paths {

		ok, err := p.ContainsPoint(col, row)
		if err != nil {
			return 0, err
		}

		if ok {
			if verbose {
				fmt.Fprintln(os.Stderr, "    "+p.String()+" --> yes")
			}
			total = total + 1
		} else {
			if verbose {
				fmt.Fprintln(os.Stderr, "    "+p.String()+" --> no")
			}
		}
	}

	return total, nil
}
