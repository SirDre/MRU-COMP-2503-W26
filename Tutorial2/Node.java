/** 
 * A generic Node class for building linked structures.
 * COMP 2503 Winter 2023
 */

public class Node<T> {
    private T data;
    
    /**
     * Default constructor - creates an empty node
     */
    public Node() {
        this.data = null;
    }

    /**
     * Constructor
     * @param data
     */
    public Node(T data) {
        this.data = data;
    }

    //Getters and setters

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    @Override
    public String toString(){
        return "data="+data;
    }
    
}
