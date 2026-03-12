import java.util.ArrayDeque;
import java.util.Queue;

public class BST2<T extends Comparable<? super T>> {
    private static class Node<E> {
        E data;
        Node<E> left, right;

        Node(E data) {
            this.data = data;
        }
    }

    private Node<T> root;
    private int size;

    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values are not supported.");
        }
        root = add(root, value);
    }

    private Node<T> add(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }

        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = add(node.left, value);
        } else if (cmp > 0) {
            node.right = add(node.right, value);
        }
        // duplicate values are ignored
        return node;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) return -1; // empty tree height
        return 1 + Math.max(height(node.left), height(node.right));
    }

   // Traversal Methods 
   private void visit(Node<T> r) {
   /* Visit a node by printing its data */

      if ( r != null)
         System.out.println( r.data);
      
   }    

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node<T> node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.println(node.data);
        printInOrder(node.right);
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node<T> node) {
        if (node == null) return;
        System.out.println(node.data);
        printPreOrder(node.left);
        printPreOrder(node.right);
        visit(node);
    }

    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(Node<T> node) {
        if (node == null) return;
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.println(node.data);
        visit(node);
    }

    public void printLevelOrder() {
        if (root == null) return;

        Queue<Node<T>> q = new ArrayDeque<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node<T> cur = q.remove();
            System.out.println(cur.data);
            visit(cur);

            if (cur.left != null) q.add(cur.left);
            if (cur.right != null) q.add(cur.right);
        }
    }
}