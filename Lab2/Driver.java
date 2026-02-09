/**
 * Driver class to demonstrate the use of generic Node class
 * and linked structures. 
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
        // Link 'a' to 'b'
        a.setNext(b);
        
        // Link 'b' to 'c'
        b.setNext(c);
        
        // Step 5: Create a starting point for the linked structure
        Node<String> nodes = a;
        
        // Step 6: Print the linked list
        System.out.println("Initial linked list:");
        System.out.println("--------------------");
        printListWithArrows(nodes);
        System.out.println();
        
        // ===== PART 1: ADDING ITEMS =====
        
        // 1a. Add at the END
        System.out.println("\n1.  Adding 'Everest' at the END:");
        System.out.println("--------------------------------");
        nodes = addAtEnd(nodes, "Everest");
        printListWithArrows(nodes);
        
        // 1b. Add at the START
        System.out.println("\n2. Adding 'Aconcagua' at the START:");
        System.out.println("-----------------------------------");
        nodes = addAtStart(nodes, "Aconcagua");
        printListWithArrows(nodes);
        
        // 1c. Add in the MIDDLE (after "Andromeda")
        System.out.println("\n3. Adding 'Denali' after 'Andromeda':");
        System.out.println("--------------------------------------");
        nodes = addAfter(nodes, "Andromeda", "Denali");
        printListWithArrows(nodes);
        
        // ===== PART 2: DELETING ITEMS =====
        
        // 2a. Delete from the MIDDLE
        System.out.println("\n4. Deleting 'Denali' from the list:");
        System.out.println("------------------------------------");
        nodes = delete(nodes, "Denali");
        printListWithArrows(nodes);
        
        // 2b. Delete from the START
        System.out.println("\n5. Deleting 'Aconcagua' (first item):");
        System.out.println("--------------------------------------");
        nodes = delete(nodes, "Aconcagua");
        printListWithArrows(nodes);
        
        // 2c. Delete from the END
        System.out.println("\n6. Deleting 'Everest' (last item):");
        System.out.println("-----------------------------------");
        nodes = delete(nodes, "Everest");
        printListWithArrows(nodes);
        
        // Final list
        System.out.println("\n\nFinal linked list:");
        System.out.println("------------------");
        printListWithArrows(nodes);
    }
    
    /**
     * Adds a new node at the END of the linked list
     * Algorithm: 
     * 1. Create a new node with the data
     * 2. If list is empty, return the new node as the head
     * 3. Otherwise, traverse to the last node
     * 4. Set the last node's next to the new node
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
        
        // Traverse to the last node
        Node<String> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        
        // Link the last node to the new node
        current.setNext(newNode);
        
        return head;
    }
    
    /**
     * Adds a new node at the START of the linked list
     * Algorithm: 
     * 1. Create a new node with the data
     * 2. Set the new node's next to the current head
     * 3. Return the new node as the new head
     * 
     * @param head the first node in the list
     * @param data the data to add
     * @return the new head of the list
     */
    public static Node<String> addAtStart(Node<String> head, String data) {
        Node<String> newNode = new Node<String>();
        newNode.setData(data);
        
        // Point new node to the current head
        newNode.setNext(head);
        
        // New node becomes the head
        return newNode;
    }
    
    /**
     * Adds a new node AFTER a specific node in the linked list
     * Algorithm:
     * 1. Create a new node with the new data
     * 2. Find the node containing the target data
     * 3. Insert the new node after the target node
     * 
     * @param head the first node in the list
     * @param afterData the data of the node to insert after
     * @param newData the data to add
     * @return the head of the list
     */
    public static Node<String> addAfter(Node<String> head, String afterData, String newData) {
        Node<String> newNode = new Node<String>();
        newNode.setData(newData);
        
        // Find the node with afterData
        Node<String> current = head;
        while (current != null && ! current.getData().equals(afterData)) {
            current = current.getNext();
        }
        
        // If we found the node
        if (current != null) {
            // Insert new node between current and current.next
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        } else {
            System.out.println("   (Node '" + afterData + "' not found)");
        }
        
        return head;
    }
    
    /**
     * Deletes a node from the linked list
     * Algorithm:
     * 1. Special case: if deleting the first node, return head. next
     * 2. Find the node BEFORE the one to delete
     * 3. Update its next reference to skip over the node to delete
     * 
     * @param head the first node in the list
     * @param data the data of the node to delete
     * @return the head of the list (may be different if first node was deleted)
     */
    public static Node<String> delete(Node<String> head, String data) {
        // Empty list
        if (head == null) {
            System.out.println("   (List is empty)");
            return null;
        }
        
        // Special case: deleting the first node
        if (head.getData().equals(data)) {
            return head.getNext();  // Return the second node as new head
        }
        
        // Find the node BEFORE the one to delete
        Node<String> current = head;
        while (current.getNext() != null && !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }
        
        // If we found the node to delete
        if (current.getNext() != null) {
            // Skip over the node to delete
            current.setNext(current.getNext().getNext());
        } else {
            System.out.println("   (Node '" + data + "' not found)");
        }
        
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
            current = current.getNext();
        }
    }
    
    /**
     * Helper method to print the list with visual arrows
     * @param head the first node in the list
     */
    public static void printListWithArrows(Node<String> head) {
        if (head == null) {
            System.out.println("null (empty list)");
            return;
        }
        
        Node<String> current = head;
        while (current != null) {
            System.out.print(current. getData());
            if (current. getNext() != null) {
                System.out.print(" -> ");
            }
            current = current.getNext();
        }
        System.out.println(" -> null");
    }
}