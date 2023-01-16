/*
 * Name: Zehui Zhang
 * PID:  A16151490
 */

import java.util.*;

/**
 * Binary search tree implementation.
 *
 * @author Zehui Zhang
 * @since  A1615190
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }


        /**
         * Setter for left child of the node
         *
         * @param newLeft New left child
         */
        public void setLeft(BSTNode newLeft) {
            this.left = newLeft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newRight New right child
         */
        public void setRight(BSTNode newRight) {
            this.right = newRight;
        }

    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return this.nelems;
    }

    /**
     * Insert a key into BST
     *
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new BSTNode(null, null, key);
            this.nelems++;
            return true;
        } else {
            return insertHelpFunc(root, key);
        }
    }

    /**
     * Helper method for insert
     */
    private boolean insertHelpFunc(BSTNode root, T key){

        if (key.compareTo(root.getKey()) == 0) {
            return false;
        } else if (key.compareTo(root.getKey()) > 0) {
            if (root.getRight() == null) {
                root.setRight(new BSTNode(null, null, key));
                this.nelems++;
            } else {
                insertHelpFunc(root.right, key);
            }
        } else if (key.compareTo(root.getKey()) < 0) {
            if (root.getLeft() == null) {
                root.setLeft(new BSTNode(null, null, key));
                this.nelems++;
            } else {
                insertHelpFunc(root.left, key);
            }
        }
        return true;
    }

    /**
     * Return true if the tree contains the 'key', false
     * otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean contains(T key) {
        if (key == null){
            throw  new NullPointerException();
        }
        return  findHelperFunc(this.root,key);
    }

    /**
     * Helper method for find
     */
    private boolean findHelperFunc (BSTNode root, T key){

        if (root == null) {
            return false;
        } else if (root.key == null) {
            return false;
        } else if (root.key.compareTo(key) == 0) {
            return true;
        } else if (key.compareTo(root.key) < 0) {
            return findHelperFunc(root.left, key);
        } else if (key.compareTo(root.key) > 0){
            return findHelperFunc(root.right, key);
        }
        return true;
    }

    /**
     * Remove the key from the BST
     *
     * @param key To be removed
     * @return True if the 'key' is removed, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean remove(T key){
        if (!contains(key)) {
            return false;
        }
        root = getRemovalReplacer(root,key);
        if (root ==null){
            nelems = 0;
            return  false;
        }else {
            return  true;
        }
    }

    /**
     * Helper function for remove
     * @param curNode BSTNode
     * @param key givem key
     * @return BSTNode needed
     */
    public BSTNode getRemovalReplacer(BSTNode curNode,T key){
        if (curNode == null) {
            return null;
        }

        if (curNode.key == key) {
            if (curNode.left == null && curNode.right == null) {
                return null;
            } else if (curNode.left == null) {
                nelems--;
                return curNode.right;
            } else if (curNode.right == null) {
                nelems--;
                return curNode.left;
            } else {
                T lmax = max(curNode.left);
                curNode.key = lmax;
                curNode.left = getRemovalReplacer(curNode.left, lmax);
                nelems--;
                return curNode;
            }
        }
        if ( key.compareTo(curNode.getKey())>0) {
            curNode.right = getRemovalReplacer(curNode.right, key);
        } else {
            curNode.left = getRemovalReplacer(curNode.left, key);
        }
        return curNode;
    }

    /**
     * helper function to get the max
     * @param curNode BSTNode
     * @return the max
     */
    private T max(BSTNode curNode) {

        if (curNode.right == null) {
            return curNode.key;
        }
        return max(curNode.right);
    }

    /**
     * Returns the smallest node from a given node
     *
     * @param root Smallest node will be found from this node
     * @return The smallest node from the 'root' node
     */
    private BSTNode findMin(BSTNode root) {
        if (root.left == null) {
            return root;
        }

        return findMin(root);
    }

    /**
     * Print the BST nodes by preorder traversal
     *
     * @return string of keys in preorder, separated by a single space (“ ”).
     */
    public String printPreOrder(){
        return preOrderHelper(root).trim();
    }

    /**
     * Helper function for printPreOrder
     * @param node BSTNode
     * @return the string will be printed
     */
    public String preOrderHelper(BSTNode node){
        String result = "";
        if(node == null){
            return "";
        }
        if (node.key!=null) {
            result = result+" " + String.valueOf(node.getKey());
        }

        if(node.left != null) {
            result = result+" " + preOrderHelper(node.left);
        }

        if(node.right != null) {
            result = result+" " + preOrderHelper(node.right);
        }

        return result;
    }

    /**
     * Print the BST nodes by postorder traversal
     *
     * @return string of keys in postorder, separated by a single space (“ ”).
     */
    public String printPostOrder(){
        return postOrderHelper(root).trim();
    }

    /**
     * Helper function for printPostOder
     * @param node given node
     * @return string need to print
     */
    public String postOrderHelper(BSTNode node){
        String result = "";

        if(node ==null){
            return "";
        }
        if(node.left != null) {
            result = result +" "+ postOrderHelper(node.left);
        }

        if(node.right != null) {
            result = result+" " + postOrderHelper(node.right);
        }

        if (node.key!=null) {
            result = result+" " + String.valueOf(node.getKey());
        }

        return result;
    }

    /**
     * Print the BST nodes by inorder traversal
     *
     * @return string of keys in inorder, separated by a single space (“ ”).
     */
    public String printInOrder(){
        return inOrderHelper(root).trim();
    }

    /**
     * Helper function for printInOrder
     * @param node BSR node
     * @return string needed
     */
    public String inOrderHelper(BSTNode node){
        String result = "";

        if(node ==null){
            return "";
        }
        if(node.left != null) {
            result = result+ " " + inOrderHelper(node.left);
        }

        if (node.key!=null) {
            result = result+" " + String.valueOf(node.getKey());
        }

        if(node.right != null) {
            result = result+" " + inOrderHelper(node.right);
        }

        return result;
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(this.getRoot());
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) {
            return -1;
        }

        int left = findHeightHelper(root.left);
        int right = findHeightHelper(root.right);
        return 1 + Math.max(left, right);
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {

        // private instance
        private Stack<BSTNode> stack;
        private BSTNode nodeCompare;

        /**
         * Constructor that initializes the Stack with the leftPath of the root
         */
        public BSTree_Iterator() {
            stack = new Stack<BSTNode>();
            nodeCompare = root;

            while (nodeCompare != null) { //goes until compare is null
                stack.push(nodeCompare); //pushes into stack
                nodeCompare = nodeCompare.left; //goes to left
            }
        }

        /**
         * Returns false if the Stack is empty
         * @return  false if the Stack is empty
         */
        public boolean hasNext() {
            return stack.size()!=0;
        }

        /**
         * Returns the next item in the BST.
         * @return the next item in the BST.
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            BSTNode pop = stack.pop();
            BSTNode right = pop.right;

            while (right != null) {
                stack.push(right);
                right = right.left;
            }

            return pop.getKey();
        }
    }

    /**
     * The iterator for BST
     * @return the result
     */
    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }
}

