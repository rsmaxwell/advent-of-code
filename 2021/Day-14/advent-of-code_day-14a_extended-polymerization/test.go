package main

import (
	"fmt"
	"os"

	"github.com/rsmaxwell/advent-of-code/2021/lists"
)

func main2() {
	l := lists.NewLinkedList()
	iterator := lists.NewIterator(l)
	iterator.Add("I")

	iterator = lists.NewIterator(l)
	for iterator.HasNext() {
		temp, err := iterator.Next()
		if err != nil {
			fmt.Print(err.Error())
			os.Exit(1)
		}
		fmt.Printf("value = %v, prev = %v, next = %v\n", temp.Data, temp.Prev, temp.Next)
	}

	fmt.Printf("Add Front Node: C\n")
	l.AddFirst("C")
	fmt.Printf("Add Front Node: B\n")
	l.AddFirst("B")
	fmt.Printf("Add Front Node: A\n")
	l.AddFirst("A")
	fmt.Printf("Add End Node: D\n")
	l.AddLast("D")
	fmt.Printf("Add End Node: E\n")
	l.AddLast("E")

	fmt.Printf("Size of list: %d\n", l.Size())

	fmt.Printf("---[ Forwards ]------------------\n")
	temp := l.Head
	for temp != nil {
		fmt.Printf("value = %v, prev = %v, next = %v\n", temp.Data, temp.Prev, temp.Next)
		temp = temp.Next
	}

	fmt.Printf("---[ Backwards ]------------------\n")
	temp = l.Tail
	for temp != nil {
		fmt.Printf("value = %v, prev = %v, next = %v\n", temp.Data, temp.Prev, temp.Next)
		temp = temp.Prev
	}

	fmt.Printf("---[ Iterator - Forwards ]------------------\n")
	iterator = lists.NewIterator(l)
	for iterator.HasNext() {
		temp, err := iterator.Next()
		if err != nil {
			fmt.Print(err.Error())
			os.Exit(1)
		}
		fmt.Printf("value = %v, prev = %v, next = %v\n", temp.Data, temp.Prev, temp.Next)
	}

	fmt.Printf("---[ Iterator - Add ]------------------\n")
	iterator = lists.NewIterator(l)
	iterator.Add("X")
	for i := 0; (i < 2) && iterator.HasNext(); i++ {
		temp, err := iterator.Next()
		if err != nil {
			fmt.Print(err.Error())
			os.Exit(1)
		}
		fmt.Printf("value = %v, prev = %v, next = %v\n", temp.Data, temp.Prev, temp.Next)

	}
	iterator.Add("Y")
	for iterator.HasNext() {
		temp, err := iterator.Next()
		if err != nil {
			fmt.Print(err.Error())
			os.Exit(1)
		}
		fmt.Printf("value = %v, prev = %v, next = %v\n", temp.Data, temp.Prev, temp.Next)
	}
	iterator.Add("Z")

	fmt.Printf("---[ Iterator - Forwards ]------------------\n")
	iterator = lists.NewIterator(l)
	for iterator.HasNext() {
		temp, err := iterator.Next()
		if err != nil {
			fmt.Print(err.Error())
			os.Exit(1)
		}
		fmt.Printf("value = %v, prev = %v, next = %v\n", temp.Data, temp.Prev, temp.Next)
	}
	fmt.Printf("---------------------------------\n")
}
