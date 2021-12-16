package bits

import "bytes"

func (d *Digit) Transform(mapping []Bits) *Digit {

	output := Bits(0)

	for i := 0; i < 7; i++ {
		mask := Bits(1 << i)
		value := Bits(mask & d.Flag)

		if value != 0 {
			output |= mapping[i]
		}
	}

	return &Digit{Flag: output}
}

func MappingToString(mapping []Bits) string {

	buf := new(bytes.Buffer)

	for _, value := range mapping {

		for i := 0; i < 7; i++ {
			mask := Bits(1 << i)
			value := mask & value

			ch := "."
			if value != 0 {
				ch = "X"
			}
			buf.WriteString(ch)
		}
		buf.WriteString("\n")
	}

	return buf.String()
}
