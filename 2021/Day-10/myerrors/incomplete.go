package myerrors

type IncompleteError struct {
	Message string
	Score   int
}

func (e *IncompleteError) Error() string {
	return e.Message
}
