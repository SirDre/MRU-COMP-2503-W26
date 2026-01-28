package DataStructure;

public class Node<T> {
    T data; // The data stored in the node
    Node<T> next; // Reference to the next node in the list

    // Constructor to create a node with data and null next reference
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    // Optional: Constructor to create a node with data and a specific next node reference
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}
