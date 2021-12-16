package bits

import (
	"bytes"
	"strings"
)

const (
	ALL = A | B | C | D | E | F | G
)

var (
	hex = []string{"A", "B", "C", "D", "E", "F", "G"}
	fmt = map[Bits]string{}
)

func init() {
	zero := NewDigit("abcefg")
	one := NewDigit("cf")
	two := NewDigit("acdeg")
	three := NewDigit("acdfg")
	four := NewDigit("bcdf")
	five := NewDigit("abdfg")
	six := NewDigit("abdefg")
	seven := NewDigit("acf")
	eight := NewDigit("abcdefg")
	nine := NewDigit("abcdfg")

	fmt[zero.Flag] = "0"
	fmt[one.Flag] = "1"
	fmt[two.Flag] = "2"
	fmt[three.Flag] = "3"
	fmt[four.Flag] = "4"
	fmt[five.Flag] = "5"
	fmt[six.Flag] = "6"
	fmt[seven.Flag] = "7"
	fmt[eight.Flag] = "8"
	fmt[nine.Flag] = "9"
}

type Digit struct {
	Flag Bits
}

func NewDigit(word string) *Digit {

	value := Bits(0)
	for _, letter := range strings.Split(word, "") {
		switch letter {
		case "a":
			value |= A
		case "b":
			value |= B
		case "c":
			value |= C
		case "d":
			value |= D
		case "e":
			value |= E
		case "f":
			value |= F
		case "g":
			value |= G
		}
	}
	return &Digit{Flag: value}
}

func (d *Digit) String() string {
	buf := new(bytes.Buffer)

	for i := 0; i < 7; i++ {
		mask := Bits(1 << i)
		value := mask & d.Flag

		if value != 0 {
			buf.WriteString(hex[i])
		}
	}

	return buf.String()
}

func (d *Digit) Format() (string, bool) {
	text, ok := fmt[d.Flag]

	if !ok {
		text = "?"
	}

	return text, ok
}

func (d *Bits) LimitMapping(m []Bits) {
	switch d.NumberOfBits() {
	case 2:
		d.Limit(m, C|F) // must be a "1"
	case 3:
		d.Limit(m, A|C|F) // must be a "7"
	case 4:
		d.Limit(m, B|C|D|F) // must be a "4"
	case 7:
		d.Limit(m, A|B|C|D|E|F|G) // must be an "8"
	}
}

func (digit *Bits) Limit(mappingArray []Bits, flag Bits) {
	for col := 0; col < 7; col++ {
		mask := Bits(1 << col)
		value := *digit & mask

		if value != 0 {
			mappingArray[col] &= flag
		} else {
			mappingArray[col] = Clear(mappingArray[col], flag)
		}
	}
}
