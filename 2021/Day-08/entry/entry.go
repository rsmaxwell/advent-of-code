package entry

import (
	"bytes"
	"fmt"
	"os"
	"strconv"
	"strings"

	"github.com/rsmaxwell/advent-of-code/2021/Day-08/bits"
	"github.com/rsmaxwell/advent-of-code/2021/tools"
)

type Entry struct {
	SignalDigits []*bits.Digit
	OutputDigits []*bits.Digit
}

var (
	verbose = true
)

func ReadEntries(filename string) ([]*Entry, error) {
	lines, err := tools.ReadLines(filename)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	entries := []*Entry{}
	for _, line := range lines {

		words := strings.Fields(line)

		if len(words) != 15 {
			return nil, fmt.Errorf("unexpected number of words in line: n: %d, %s", len(words), line)
		}

		signalDigits := []*bits.Digit{}
		for i := 0; i < 10; i++ {
			word := words[i]
			digit := bits.NewDigit(word)
			signalDigits = append(signalDigits, digit)
		}

		divider := words[10]
		if divider != "|" {
			return nil, fmt.Errorf("unexpected divider: [%s] at %d in line: %s", divider, 10, line)
		}

		outputDigits := []*bits.Digit{}
		for i := 11; i < 15; i++ {
			word := words[i]
			digit := bits.NewDigit(word)
			outputDigits = append(outputDigits, digit)
		}

		e := &Entry{SignalDigits: signalDigits, OutputDigits: outputDigits}
		entries = append(entries, e)
	}
	return entries, nil
}

func (e *Entry) String() string {
	buf := new(bytes.Buffer)

	for _, digit := range e.SignalDigits {
		buf.WriteString(digit.String() + " ")
	}

	buf.WriteString("| ")

	for _, digit := range e.OutputDigits {
		buf.WriteString(digit.String() + " ")
	}

	buf.WriteString("| ")

	for _, digit := range e.OutputDigits {
		text, ok := digit.Format()
		if ok {
			buf.WriteString(text)
		} else {
			buf.WriteString(" ?")
		}
	}

	return buf.String()
}

func (e *Entry) Solve() (int, int, error) {

	if verbose {
		fmt.Println(e.String())
	}

	// Find a mapping which generates good output digits.
	// Start with all bits set, then clear those bits obviously wrong, then iterate through the remaining possibilities.
	// As soon as we find a solution, drop out
	mapping := []bits.Bits{bits.ALL, bits.ALL, bits.ALL, bits.ALL, bits.ALL, bits.ALL, bits.ALL}
	e.LimitMapping(mapping)
	// fmt.Println(bits.MappingToString(mapping))

	for i0 := 0; i0 < 7; i0++ {
		mask := bits.Bits(1 << i0)
		if (mapping[0] & mask) == 0 {
			continue
		}

		for i1 := 0; i1 < 7; i1++ {
			switch i1 {
			case i0:
				continue
			}
			mask = bits.Bits(1 << i1)
			if (mapping[1] & mask) == 0 {
				continue
			}

			for i2 := 0; i2 < 7; i2++ {
				switch i2 {
				case i0:
					continue
				case i1:
					continue
				}
				mask = bits.Bits(1 << i2)
				if (mapping[2] & mask) == 0 {
					continue
				}

				for i3 := 0; i3 < 7; i3++ {
					switch i3 {
					case i0:
						continue
					case i1:
						continue
					case i2:
						continue
					}
					mask = bits.Bits(1 << i3)
					if (mapping[3] & mask) == 0 {
						continue
					}

					for i4 := 0; i4 < 7; i4++ {
						switch i4 {
						case i0:
							continue
						case i1:
							continue
						case i2:
							continue
						case i3:
							continue
						}
						mask = bits.Bits(1 << i4)
						if (mapping[4] & mask) == 0 {
							continue
						}

						for i5 := 0; i5 < 7; i5++ {
							switch i5 {
							case i0:
								continue
							case i1:
								continue
							case i2:
								continue
							case i3:
								continue
							case i4:
								continue
							}
							mask = bits.Bits(1 << i5)
							if (mapping[5] & mask) == 0 {
								continue
							}

							for i6 := 0; i6 < 7; i6++ {
								switch i6 {
								case i0:
									continue
								case i1:
									continue
								case i2:
									continue
								case i3:
									continue
								case i4:
									continue
								case i5:
									continue
								}
								mask = bits.Bits(1 << i6)
								if (mapping[6] & mask) == 0 {
									continue
								}

								total1478, number, ok, err := TryPrint(e, i0, i1, i2, i3, i4, i5, i6)
								if err != nil {
									return 0, 0, err
								}
								if ok {
									return total1478, number, err
								}
							}
						}
					}
				}
			}
		}
	}

	return 0, 0, fmt.Errorf("no solution found")
}

func TryPrint(e *Entry, i0, i1, i2, i3, i4, i5, i6 int) (int, int, bool, error) {

	if verbose {
		fmt.Printf("%d %d %d %d %d %d %d\n", i0, i1, i2, i3, i4, i5, i6)
	}

	mapping := []bits.Bits{
		1 << i0,
		1 << i1,
		1 << i2,
		1 << i3,
		1 << i4,
		1 << i5,
		1 << i6,
	}

	if verbose {
		fmt.Println(bits.MappingToString(mapping))
	}

	return e.PrintOutput(mapping)
}

func (e *Entry) LimitMapping(mapping []bits.Bits) {
	for _, digit := range e.SignalDigits {
		digit.Flag.LimitMapping(mapping)
	}
}

func (e *Entry) PrintOutput(xform []bits.Bits) (int, int, bool, error) {

	buf := new(bytes.Buffer)

	if verbose {
		fmt.Println("-------------------------------------------------")
	}

	var err error
	number := 0
	total1478 := 0
	success := true
	for _, d := range e.OutputDigits {
		x := d.Transform(xform)

		if verbose {
			fmt.Print(x.String() + "    ")
		}

		text, ok := x.Format()
		if ok {
			buf.WriteString(text)

			x, err := strconv.Atoi(text)
			if err != nil {
				return 0, 0, false, err
			}

			doit := true
			switch x {
			case 1:
			case 4:
			case 7:
			case 8:
			default:
				doit = false
			}

			if doit {
				total1478 += 1
			}

		} else {
			buf.WriteString("?")
			success = false
		}
	}
	if success {

		text := buf.String()
		number, err = strconv.Atoi(text)
		if err != nil {
			return 0, 0, false, err
		}

		fmt.Printf("%s       %s\n", buf.String(), "Success")
		fmt.Printf("total1478: %d, number: %d\n", total1478, number)

		if verbose {
			fmt.Print(bits.MappingToString(xform))
		}
	}

	if verbose {
		fmt.Println("")
	}
	return total1478, number, success, nil
}
