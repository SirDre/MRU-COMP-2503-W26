package DataStructure;

public class LinkedList { 
    public static void main(String[] args) {
        // 1. Create the first node, which becomes the 'head' of the list
        Node<Integer> head = new Node<>(10); // List: 10 -> null

        // 2. Create the second node
        Node<Integer> second = new Node<>(20); // List: 20 -> null

        // 3. Link the first node to the second node
        head.next = second; // List: 10 -> 20 -> null

        // 4. Create the third node
        Node<Integer> third = new Node<>(30); // List: 30 -> null

        // 5. Link the second node to the third node
        second.next = third; // List: 10 -> 20 -> 30 -> null

        // The complete list is 10 -> 20 -> 30 -> null.
        // You can traverse it starting from the 'head' node.
        System.out.println("Linked List structure:");
        Node<Integer> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
} 
