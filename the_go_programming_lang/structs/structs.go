package main

import "fmt"

// A named struct type S can’t declare a field of the same type S:
// an aggregate value cannot contain itself. (An analogous restriction
// applies to arrays.) But S may declare a field of the pointer
// type *S, which lets us create recursive data structures like
// linked lists and trees.

type tree struct {
	value       int
	left, right *tree
}

func main() {
	// test treesort
	arr := []int{3, 2, 1}
	Sort(arr) // inplace sort
	fmt.Printf("%x\n", arr)

	// embedding
}

// treesort
func Sort(values []int) {
	var root *tree
	for _, v := range values {
		root = add(root, v)
	}
	appendValues(values[:0], root)
}

// appendValues appends the elements of t to values in order
// and returns the resulting slice.
func appendValues(values []int, t *tree) []int {
	if t != nil {
		values = appendValues(values, t.left)
		values = append(values, t.value)
		values = appendValues(values, t.right)
	}
	return values
}

func add(t *tree, value int) *tree {
	if t == nil {
		// Equivalent to return &tree{value: value}.
		return &tree{value: value}
	}
	if value < t.value {
		t.left = add(t.left, value)
	} else {
		t.right = add(t.right, value)
	}
	return t
}
