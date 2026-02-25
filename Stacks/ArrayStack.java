import java.util.EmptyStackException;

public class ArrayStack<T> implements MyStack<T> {
    private T[] stack;
    private int top;

    public ArrayStack(int cap) {
      //  int initialCapacity = Math.max(1, capacity);
        stack = (T[]) new Object[cap];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public T peek() {
        if (isEmpty()) {
           throw new EmptyStackException();
        }
        return stack[top];
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T x = stack[top];
        stack[top] = null;
        top--;

        return x;
    }
 
    public void push(T x) {
        if (top + 1 == stack.length) {
            T[] newStack = (T[]) new Object[Math.max(1, stack.length * 2)];
            System.arraycopy(stack, 0, newStack, 0, stack.length);
            stack = newStack;
        }

        top++;
        stack[top] = x;
    }
    
}
