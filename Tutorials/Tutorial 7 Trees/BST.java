public class BST<T extends Comparable<T>> {
/* A Binary search tree */
 
   class BSTNode implements Comparable<BSTNode> {
   /* Each node in the tree is one of these */
      private T data;
      private BSTNode left;
      private BSTNode right;

      public BSTNode(T d) {
         setLeft( null);
         setRight( null);
         setData( d);
      }

      public T getData() { return data; }
      public void setData(T d) { data = d; }

      public void setLeft( BSTNode l) { left = l; }
      public BSTNode getLeft() { return left; }

      public void setRight( BSTNode r) { right = r; }
      public BSTNode getRight() { return right; }

      public boolean isLeaf() { 
         return ( getLeft() == null) && ( getRight() == null); 
      }

      public int compareTo(BSTNode o) {
      /* Make use of the compareTo() implemented on 
         the generic class */
         return this.getData().compareTo( o.getData());
      }
   }


   private BSTNode root;
   private int size;

   public BST() {
      root = null;
      size = 0;
   }

   public int size() {
   /* Return the number of nodes in the tree.
    */
      return size;
   }
	
   public T find(T d) {
   /* Return true if element d is present in the tree.  
    * This is the public find, it calls the private 
    * recursive find. */
      return find( d, root);
   }

   public void add( T d) {
   /* Add element d to the tree.  */
      BSTNode n = new BSTNode( d);
      if ( root == null) {
         root = n;
      } else {
         add( root, n);
      }
      size++;
   }

   public int height() {
   /*  Return the height of the tree.  */
      return height( root);
   }

   public void printInOrder() {
      inOrderTraversal( root);
   }
	
   public void printPreOrder() {
      preOrderTraversal( root);
   }
	
   public void printPostOrder() {
      postOrderTraversal( root);
   }
	
   public void printLevelOrder() {
      levelOrderTraversal( root);
   }
	
   // Private methods.

   private T find(T d, BSTNode r) {
   /* Find element T in the tree. Return the data if it is 
      found, null otherwise. 
   */

   // if the root is null, clearly d is not here so return null
   if ( r == null)
      return null;
   // the root is not null so compare it's data to d. 
   int c = d.compareTo( r.getData());
   // if it is equal, we have found it! 
   if ( c == 0)
      return r.getData();
   // if d is < 0, look down the left subtree
   else if (c < 0)
      return find( d, r.getLeft());
   // otherwise it is bigger so look down the right subtree
   else
      return find( d, r.getRight());
   }


   private void add(BSTNode r, BSTNode n) {
   /* Add node r to the tree */
      int c = n.compareTo( r);
      if ( c < 0) {
         // TODO An exercise for the student!
      }
   }

   private int height(BSTNode r) {
   /* Return the height of the tree */
      int h = -1;

      // TODO: An exercise for the student!
      return h;
   }

   // Traversal Methods 
   private void visit(BSTNode r) {
   /* Visit a node by printing its data */

      if ( r != null)
         System.out.println( r.getData());
   }
	
   private void inOrderTraversal(BSTNode r) {
   /* Perform an InOrder traversal of the tree */
      if (r == null)
         return;
      else {
         inOrderTraversal( r.getLeft());
         visit( r);
         inOrderTraversal( r.getRight());
      }
   }
	
   private void preOrderTraversal(BSTNode r) {
   /* Perform an InOrder traversal of the tree */
      // TODO: An exercise for the student!
   }
	
   private void postOrderTraversal(BSTNode r) {
   /* Perform a Post order traversal of the tree */
      // TODO: An exercise for the student!
   }
	
   private void levelOrderTraversal(BSTNode r) {
   /* Perform a level Order traversal of the tree */
      // TODO: An exercise for the student!
   }
}
