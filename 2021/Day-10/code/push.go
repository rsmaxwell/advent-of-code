package code

type Push struct {
	ch string
}

func (o Push) Perform(c *Compiler) error {
	c.Stack.Push(o.ch)
	return nil
}
