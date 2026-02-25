import java.util.*;


class StackNode<T> {
    T data;
    StackNode<T> next;

    StackNode(T data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListStack<T> implements MyStack<T> {
    private StackNode<T> top;
    private int size;

    public LinkedListStack() {
        top = null;
        size = 0;

    }
    
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public void push(T item) {
        StackNode<T> newNode = new StackNode<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = top.data;
        top = top.next;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}   