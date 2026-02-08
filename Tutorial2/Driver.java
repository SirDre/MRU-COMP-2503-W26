/**
 * Driver class to demonstrate the use of Node class and linked structures. 
 * COMP 2503 Winter 2023
 */

public class Driver {

    public static void main(String[] args) {
        // Step 2: Create three instances of Node with String as the data type
        Node<String> a = new Node<String>();
        a.setData("Athabasca");

        Node<String> b = new Node<String>();
        b.setData("Andromeda");
        
        Node<String> c = new Node<String>();
        c.setData("Snow Dome");

        // Step 4: Link the nodes together to create a chain

        // Step 5: Create a starting point for the linked structure
         Node<String> nodes = a;
        // Step 6: Print the linked list
        printList(nodes);
        System.out.println();

    }

    /**
     * Adds a new node at the START of the linked list
     * 
     * @param head the first node in the list
     * @param data the data to add
     * @return the new head of the list
     */
    public static Node<String> addAtStart(Node<String> head, String data) {
        Node<String> newNode = new Node<String>();
        newNode.setData(data);
        
        // New node becomes the head
        return newNode;
    }

   /**
     * Adds a new node at the END of the linked list
     * 
     * @param head the first node in the list
     * @param data the data to add
     * @return the head of the list
     */
    public static Node<String> addAtEnd(Node<String> head, String data) {
        Node<String> newNode = new Node<String>();
        newNode.setData(data);
        
        // If list is empty, new node becomes the head
        if (head == null) {
            return newNode;
        }
      
        
        return head;
    }

    /**
     * Deletes a node from the linked list
     * 
     * @param head the first node in the list
     * @param data the data of the node to delete
     * @return the head of the list (may be different if first node was deleted)
     */
    public static Node<String> delete(Node<String> head, String data) {
        // Empty list
        if (head == null) {
            System.out.println("empty list");
            return null;
        }
       
        // Find the node BEFORE the one to delete
       // Node<String> current = head;
   
        
        return head;
    }
    
    /**
     * Helper method to print the list in a simple format
     * @param head the first node in the list
     */
    public static void printList(Node<String> head) {
        Node<String> current = head;
        while (current != null) {
            System.out.println(current.getData());
        }
    }

}
