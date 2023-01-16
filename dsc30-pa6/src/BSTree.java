/*
 * Name: Zehui Zhang
 * PID:  A16151490
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Zehui Zhang
 * @since  2021-02-14
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.dataList = new LinkedList<T>();
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
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return this.dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.nelems = 0;
        this.root = null;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        if (root == null) {
            return null;
        }
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
     * @param root 
     * @param key
     * @return the insert node
     */
    private BSTNode insertHelper(BSTNode root, T key) {
        if (root == null) {
            LinkedList<T> data = new LinkedList<T>();
            root = new BSTNode(null, null, data, key);
            this.root = root;
            return this.root;
        }
        if (key.compareTo(root.getKey()) < 0) {
            root.setleft(insertHelper(root.getLeft(), key));
        } else if (key.compareTo(root.getKey()) > 0) {
            root.setright(insertHelper(root.getRight(), key));
        } else {
            return root;
        }
        return root;
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
        if (this.findKey(key)) {
            return false;
        } else {
            this.root = insertHelper(this.root, key);
            if (this.root != null) {
                this.nelems += 1;
                return true;
            }
        }
        return false;
    }


    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param n current BST node
     * @param key To be searched
     * @return true if the key is found
     */
    private boolean findKeyHelper(BSTNode n, T key) {
        if (n == null) {
            return false;
        } 
        if (key.compareTo(n.getKey()) == 0) {
            return true;
        }
        if (key.compareTo(n.getKey()) < 0) {
            return findKeyHelper(n.getLeft(), key);
        } else {
            return findKeyHelper(n.getRight(), key);
        }
    }
    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param n current BST node
     * @param key To be searched
     * @return the 'key''s node 
     */
    private BSTNode findNodeHelper(BSTNode n, T key) {
        if (n == null || key.compareTo(n.getKey()) == 0) {
            return n;
        } 
        if (key.compareTo(n.getKey()) < 0) {
            return findNodeHelper(n.getLeft(), key);
        } else {
            return findNodeHelper(n.getRight(), key);
        }
    }
    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKeyHelper(this.root, key)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null) {
            throw new NullPointerException();
        } 
        if (!this.findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode mynode = findNodeHelper(this.root, key);
        mynode.addNewInfo(data);
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException();
        } 
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode mynode = findNodeHelper(this.root, key);
        return mynode.getDataList();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        if (this.nelems == 0) {
            return -1;
        }
        if (this.nelems == 1) {
            return 0;
        }
        return findHeightHelper(this.root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode n) {
        if (n == null) {
            return -1;
        } 
        int l = findHeightHelper(n.getLeft());
        int r = findHeightHelper(n.getRight());
        if (l > r){
            return l + 1;
        } else {
            return r + 1;
        }
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {

        private Stack<BSTNode> stack;
        private BSTNode root = BSTree.this.getRoot();

        public BSTree_Iterator() {
            stack = new Stack<BSTNode>();
            BSTNode curr = root;
            while (curr!= null) {
                stack.push(curr);
                curr = curr.getLeft();
            }
        }

        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            return true;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTNode curr = stack.pop();
            T result = curr.getKey();

            if (curr.getRight() !=  null) {
                stack.push(curr.getRight());
                curr = curr.getRight();

                while (curr.getLeft() != null) {
                    stack.push(curr.getLeft());
                    curr = curr.getLeft();
                    
                }
            }
            return result;
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        ArrayList<T> a1 = new ArrayList<T>();
        ArrayList<T> a2 = new ArrayList<T>();
        ArrayList<T> result = new ArrayList<T>();
        while(iter1.hasNext()) {
            a1.add(iter1.next());
        }
        while(iter2.hasNext()) {
            a2.add(iter2.next());
        }
        for (T item : a1) {
            if (a2.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public int levelCount(int level) {
        if (level > this.findHeight()) {
            return -1;
        }
        return levelCountHelper(level, this.root);
    }

    private int levelCountHelper(int level, BSTNode root) {
        if ((level == 0 && root == null) || root == null) {
            return 0;
        }
        if (level == 0 && root != null) {
            return 1;
        }
        
        return levelCountHelper(level-1, root.getRight()) + levelCountHelper(level-1, root.getLeft());
    }
}
