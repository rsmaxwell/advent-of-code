package myerrors

import "fmt"

type SyntaxError struct {
	Message string
	Char    string
}

func (e *SyntaxError) Error() string {
	return fmt.Sprintf("%s (ch: %s)", e.Message, e.Char)
}
