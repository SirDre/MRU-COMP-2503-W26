import java.util.ArrayList;

/**
 * BST (Binary Search Tree) from Tutorial 7, adapted for Assignment 3.
 *
 * The core BST structure — inner BSTNode class, recursive helpers, and all
 * four traversals — is taken directly from the course reference implementation.
 *
 * Tables - BST to Index a Column:
 *  - add(String key, Row row) O(log n)
 *  - find(String key) O(log n)
 *  - size() O(n)
 *
 * @author Andre Gardiner
 * @version 3.0
 * @see Row
 * @see Table
 * @see A3
 */

public class BST {

   // Construct an empty BST
   public BST() {
      root = null;
   }

   // Node
   private class Node {
      String key;
      ArrayList<Row> data;
      Node left, right;

      Node(String key, Row row) {
         this.key = key;
         this.data = new ArrayList<>();
         this.data.add(row);
      }
   }

   // BST root node
   private Node root;

   // Insert a (key, row) pair.

   public void add(String key, Row row) {
      if (root == null) {
         root = new Node(key, row);
         return;
      }

      Node current = root;
      while (current != null) {
         int compare = key.compareTo(current.key);

         if (compare < 0) {
            if (current.left == null) {
               current.left = new Node(key, row);
               return;
            }
            current = current.left;
         } else if (compare > 0) {
            if (current.right == null) {
               current.right = new Node(key, row);
               return;
            }
            current = current.right;
         } else {
            current.data.add(row);
            return;
         }
      }
   }

   // Find the rows linked with a key, or null if not found.
   public ArrayList<Row> find(String key) {
      Node node = root;

      while (node != null) {
         int compare = key.compareTo(node.key);

         if (compare < 0) {
            node = node.left;
         } else if (compare > 0) {
            node = node.right;
         } else {
            return node.data;
         }
      }

      return null;
   }


   // Return the number of distinct keys stored in this tree.
   public int size() {
      ArrayList<Node> stack = new ArrayList<>();
      
      int count = 0;

      if (root != null) {
         stack.add(root);
      }
      
      while (stack != null) {
         Node node = stack.remove(stack.size() - 1);
         count++;
         
         if (node.left != null) {
            stack.add(node.left);
         }
         if (node.right != null) {
            stack.add(node.right);
         }
      }
      
      return count;
   }

   @Override
   public String toString() {
      return "BST[key=" + size() + "]";
   }
}