package lists

import "fmt"

type Iterator struct {
	List    *LinkedList
	Current *Node
}

func NewIterator(list *LinkedList) *Iterator {
	return &Iterator{List: list, Current: list.Head}
}

func (i *Iterator) HasNext() bool {
	return i.Current != nil
}

func (i *Iterator) Next() (*Node, error) {
	if i.Current == nil {
		return nil, fmt.Errorf("there is no next element")
	}

	position := i.Current
	i.Current = i.Current.Next
	return position, nil
}

func (i *Iterator) Add(data string) error {
	newNode := &Node{Data: data}

	var next **Node
	var prev **Node

	if i.Current == nil {
		next = &i.List.Tail

		if i.List.Tail == nil {
			prev = &i.List.Head
		} else {
			prev = &i.List.Tail.Next
			newNode.Prev = i.List.Tail
		}

	} else {
		next = &i.Current.Prev
		newNode.Next = i.Current

		if i.Current.Prev == nil {
			prev = &i.List.Head
		} else {
			prev = &i.Current.Prev.Next
			newNode.Prev = i.Current.Prev
		}
	}

	*next = newNode
	*prev = newNode

	return nil
}
