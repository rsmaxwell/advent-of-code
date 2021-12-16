package bits

type Bits uint8

const (
	A Bits = 1 << iota
	B
	C
	D
	E
	F
	G
)

func Set(b, flag Bits) Bits    { return b | flag }
func Clear(b, flag Bits) Bits  { return b &^ flag }
func Toggle(b, flag Bits) Bits { return b ^ flag }
func Has(b, flag Bits) bool    { return b&flag != 0 }

func (b *Bits) NumberOfBits() int {
	total := 0
	for i := 0; i < 7; i++ {
		mask := Bits(1 << i)
		value := mask & *b

		if value != 0 {
			total += 1
		}
	}

	return total
}
