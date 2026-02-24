import java.util.EmptyStackException;

public class ArrayStack<T> implements MyStack<T> {
    private T[] stack;
    private int top;

    public ArrayStack(int capacity) {
      //  stack = (T[]) new Object[capacity];
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
        top--;
        if (isEmpty()) {
          throw new EmptyStackException();
        }
        T x = stack[top];
      //  stack[top] = null; // Clear reference for garbage collection
   
        return x;
    }

    @SuppressWarnings("unchecked")
    public void push(T x) {
        if (top == stack.length) {
            //throw new IllegalStateException("Stack is full");
            T[] newStack = (T[]) new Object[stack.length * 2];
            System.arraycopy(stack, 0, newStack, 0, stack.length);
            stack = newStack;
        }
        
        top++;
        stack[top] = x;
    }
    
}
