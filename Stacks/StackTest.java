import java.util.*;

public class StackTest {

    // A palindrome is a word, phrase, number, or other sequence of characters that reads the same forward and backward (ignoring spaces, punctuation, and capitalization
    // For example, "A man, a plan, a canal, Panama!" is a palindrome.  
    public static boolean isPalindrome(String str) { // Check if a string is a palindrome using a stack 
        MyStack<Character> stack = new ArrayStack<>(10);
        
        // Push each character of the string onto the stack
        for ( int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        
        // Check if the string is a palindrome
        for ( int i = 0; i < str.length(); i++) {
             if (stack.pop() != str.charAt(i)) {
                return false; // Not a palindrome
            }
        }
 
        
        return true; // Is a palindrome
    }

    public void reWithStack(int[] arr) {
        MyStack<Integer> stack = new ArrayStack<>(10);
        
        // Push each element of the array onto the stack
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
        }

        // Pop each element from the stack and store it back in the array
        for(int i = 0; i < arr.length; i++) { 
            arr[i] = stack.pop();
        }
        
        // Pop each element from the stack and print it
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(10); 
        // You can also test with LinkedListStack by changing the type to MyStack<Integer> and initializing it with new LinkedListStack<>()

        
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        System.out.println("Top element: " + stack.peek()); // Should print 30
        
        System.out.println("Popped element: " + stack.pop()); // Should print 30
        System.out.println("Top element after pop: " + stack.peek()); // Should print 20
        
        stack.push(40);
        System.out.println("Top element after pushing 40: " + stack.peek()); // Should print 40
        
        while (!stack.isEmpty()) {
            System.out.println("Popped element: " + stack.pop());
        }
        
        StackTest test = new StackTest();
        System.out.println("PALINDROME TESTS:");
        System.out.println("CAR - " + test.isPalindrome("car"));
        System.out.println("RACECAR - " + test.isPalindrome("racecar"));   


        StackTest test2 = new StackTest();
        int[] arr = {1, 2, 3, 4, 5, 6, 19, 20};
        test2.reWithStack(arr);
        System.out.println("Reversed array: " + Arrays.toString(arr)); // Should print [20, 19, 6, 5, 4, 3, 2, 1]
 
    }
}
