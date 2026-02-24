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

    public static void main(String[] args) {
        MyStack<Integer> stack = new ArrayStack<>(10);

        
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
        
        // Uncommenting the following line will throw an exception since the stack is empty
        // System.out.println(stack.pop());
    }
}
