package lists

import "fmt"

type Node struct {
	Data string
	Prev *Node
	Next *Node
}

type LinkedList struct {
	Len  int
	Tail *Node
	Head *Node
}

func NewLinkedList() *LinkedList {
	return &LinkedList{}
}

func (l *LinkedList) Add(index int, data string) error {

	temp := l.Head
	for i := 0; i < index; i++ {
		temp = temp.Next
		if temp == nil {
			return fmt.Errorf("index is out of range")
		}
	}

	return nil
}

func (l *LinkedList) AddFirst(data string) {
	newNode := &Node{
		Data: data,
	}
	if l.Head == nil {
		l.Head = newNode
		l.Tail = newNode
	} else {
		newNode.Next = l.Head
		l.Head.Prev = newNode
		l.Head = newNode
	}
	l.Len++
}

func (l *LinkedList) AddLast(data string) {
	newNode := &Node{
		Data: data,
	}
	if l.Head == nil {
		l.Head = newNode
		l.Tail = newNode
	} else {
		currentNode := l.Head
		for currentNode.Next != nil {
			currentNode = currentNode.Next
		}
		newNode.Prev = currentNode
		currentNode.Next = newNode
		l.Tail = newNode
	}
	l.Len++
}

func (l *LinkedList) Size() int {
	return l.Len
}
