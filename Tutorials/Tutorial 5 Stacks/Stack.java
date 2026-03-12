public class Stack<T extends Comparable<T>> extends SLL<T> {
	// add an element data to the top of the stack.
	public void push(T data) {
		// Todo: add an element data to the top of the stack.
		// use addHead
		addHead(data);
		
	}


	public T pop() {
		// remove an element from the top of the stack.
		// use deleteHead
		return deleteHead();
	}


	public T peek() {
		// Todo: return a pointer to the top element on the stack,
		// without removing it.
		// use getHead;
		return getHead();
	}

	public boolean isEmpty() {
		// return true if the stack is empty, false otherwise
		// which method from SSL should you use?
		return size() == 0;
	}
}
