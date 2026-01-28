/**
 * A generic Node class for building linked structures. 
 * Uses a generic type parameter T instead of Object. 
 * COMP 2503 Winter 2023
 */
public class Node<T> {
    private T data;
    private Node<T> next;
    
    /**
     * Default constructor - creates an empty node
     */
    public Node() {
        this.data = null;
        this.next = null;
    }
    
    /**
     * Constructor with data
     * @param data the data to store in this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
    
    /**
     * Constructor with data and next node
     * @param data the data to store in this node
     * @param next reference to the next node
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    
    // Getters and setters
    
    /**
     * Gets the data stored in this node
     * @return the data
     */
    public T getData() {
        return data;
    }
    
    /**
     * Sets the data for this node
     * @param data the data to store
     */
    public void setData(T data) {
        this.data = data;
    }
    
    /**
     * Gets the reference to the next node
     * @return the next node
     */
    public Node<T> getNext() {
        return next;
    }
    
    /**
     * Sets the reference to the next node
     * @param next the next node in the chain
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
    
    /**
     * Returns a string representation of this node
     * @return string representation
     */
    @Override
    public String toString() {
        return "Node[data=" + data + "]";
    }
}