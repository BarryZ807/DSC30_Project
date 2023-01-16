/* 
 * NAME: zehui Zhang
 * PID: A16151490
 */
import java.util.*;
/**
 * A DAFtree class
 * 
 * @author Zehui Zhang
 * @since 13/03/2021
 */


@SuppressWarnings("rawtypes")
public class DAFTree<K extends Comparable<? super K>, D> implements Iterable {

    /* instance variable */
    private DAFNode<K, D> root;
    private int size;
    private int unique_size;

    protected class DAFNode<K extends Comparable<? super K>, D> {
        K key;
        D data;
        int count; // duplicate counter
        DAFNode<K, D> left, right;

        /**
         * Constructor which initializes a DAFNode<K, D> instance with 
         * the given key and data
         * @param key the key
         * @param the data
         */
        public DAFNode(K key, D data) {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            this.key = key;
            this.data = data;
            this.count = 1;
            this.left = null;
            this.right = null;
        }
        /**
         * Constructor which initializes a DAFNode<K, D> instance with 
         * the given key and data and nCopy
         * @param key the key
         * @param the data
         * @param nCopy 
         */
        public DAFNode(K key, D data, int nCopy) {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            if (nCopy < 1) {
                throw new IllegalArgumentException();
            }
            this.key = key;
            this.data = data;
            this.count = nCopy;
            this.left = null;
            this.right = null;
        }

        /**
         * Get the key of the node
         * @return the key of DAFnode
         */
        public K getKey() {
            return this.key;
        } 

        /**
         * Get the data of the node
         * @return the data of the node
         */
        public D getData() {
            return this.data;
        }

        /**
         * Get the count of the node
         * @return the count of the node
         */
        public int getCount() {
            return this.count;
        }

        /**
         * increment the counter of the node by certain number
         * @param n 
         * @return the new counter
         */
        public int incrementCounter(int n) {
            this.count += n;
            return this.count;
        }

        /**
         * Get the left of the node
         * @return the left of the node
         */
        public DAFNode<K,D> getLeft() {
            return this.left;
        }

        /** 
         * Get the right of the node
         * @return the right of the node
         */
        public DAFNode<K,D> getRight() {
            return this.right;
        }

        /**
         * Set the left of the node
         * @param newLeft
         */
        public void setLeft(DAFNode<K,D> newLeft) {
            this.left = newLeft;
        }

        /**
         * Set the right of the node
         * @param newRight
         */
        public void setRight(DAFNode<K,D> newRight) {
            this.right = newRight;
        }

        /**
         * Set the data of the node
         * @param newData
         */
        public void setData(D newData) {
            this.data = newData;
        }
    }

    /**
     * Constructor which initializes a DAFTree instance
     */
    public DAFTree() {
        this.root = null;
        this.size = 0;
        this.unique_size = 0;
    }

    /**
     * Get the size of the tree
     * @return the size of the tree
     */
    public int size() {
        return this.size;
    }

    /**
     * Get the unique size of the tree
     * @return the unique size of the tree
     */
    public int nUniqueKeys() {
        return this.unique_size;
    }

    /**
     * A helper function to insert
     * @param root
     * @param key
     * @param data
     * @param nCopy
     * @return the inserted node
     */
    private DAFNode<K,D> insertHelper(DAFNode<K, D> root, K key, D data, int nCopy) {
        if (root == null) {
            DAFNode<K, D> r = new DAFNode<K, D>(key, data, nCopy);
            this.root = r;
            return this.root;
        }
        if (key.compareTo(root.getKey()) < 0) {
            root.setLeft(insertHelper(root.getLeft(), key, data, nCopy));
        } else if (key.compareTo(root.getKey()) > 0) {
            root.setRight(insertHelper(root.getRight(), key, data, nCopy));
        } else {
            root.incrementCounter(nCopy);
            return root;
        }
        return root;
    }

    /**
     * Insert a given key-data pair, if the key exists, ignore the new data
     * @param key
     * @param data
     * @param nCopy
     * @return the inserted node
     */
    public DAFNode<K, D> insert(K key, D data, int nCopy) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFNode<K, D> n = lookup(key);
        if (n != null) {
            this.root = insertHelper(this.root, key, data, nCopy);
            if (this.root != null) {
                this.size += nCopy;
                return lookup(key);
            }
        } else {
            this.root = insertHelper(this.root, key, data, nCopy);
            if (this.root != null) {
                this.size += nCopy;
                this.unique_size += 1;
                return lookup(key);
            }
        }
        return null;
    }

    /**
     * Insert a given key for nCopy numbers
     * @param key
     * @param nCopy
     * @return the inserted node
     */
    public DAFNode<K, D> insertDuplicate(K key, int nCopy) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFNode<K, D> n = lookup(key);
        if (n == null) {
            return null;
        }
        n.incrementCounter(nCopy);
        this.size += nCopy;
        return n;
    }

    /**
     * A helper function to lookup
     * @param n
     * @param key
     * @return the lookup node, null if not found
     */
    private DAFNode<K,D> lookupHelper(DAFNode<K, D> n, K key) {
        if (n == null) {
            return null;
        } 
        if (key.compareTo(n.getKey()) == 0) {
            return n;
        }
        if (key.compareTo(n.getKey()) < 0) {
            return lookupHelper(n.getLeft(), key);
        } else {
            return lookupHelper(n.getRight(), key);
        }
    }

    /**
     * Look up a given key
     * @param key
     * @return the node with the given key, or null if not found
     */
    public DAFNode<K, D> lookup(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        DAFNode<K, D> n = lookupHelper(this.root, key);
        if (n == null) {
            return null;
        } else {
            return n;
        }
    }

    /**
     * Update the newData for the given key
     * @param key
     * @param newData
     * @return the updated node
     */
    public DAFNode<K, D> updateData(K key, D newData) {
        if (key == null || newData == null) {
            throw new NullPointerException();
        }
        DAFNode<K, D> n = lookup(key);
        if (n == null) {
            return null;
        }
        n.setData(newData);
        return n;
    }

    /**
     * Remove a key from the tree
     * @param n a given node
     * @param key a key
     * @param nCopy
     * @return the removed node
     */
    private DAFNode<K,D> removeHelper(DAFNode<K, D> n, K key, int nCopy) {
        if (n == null) {
            return null;
        }
        if (key.compareTo(n.getKey()) == 0) {
            
            int c = n.getCount();
            DAFNode<K, D> myNode = n;
            // indicate if we are deleting the root
            boolean nIsKey = false;
            if (n.getKey().compareTo(this.root.getKey()) == 0) {
                nIsKey = true;
            }
            // not removing the node but decrease its counter
            if (c - nCopy > 0) {
                n.incrementCounter(0 - nCopy);
                this.size -= nCopy;
                return n;
            } else {
                
                // remove leaf node
                if (n.getLeft() == null && n.getRight() == null) {
                    this.size -= c;
                    this.unique_size -= 1;
                    if (nIsKey) {
                        this.root = null;
                    } 
                    n = null;
                    return myNode;
                }
                // remove node with one child
                if (n.getLeft() == null && n.getRight() != null) {
                    this.size -= c;
                    this.unique_size -= 1;
                    DAFNode<K, D> right = n.getRight();
                    n  = n.getRight();
                    n.setLeft(null);
                    n.setRight(right.getRight());
                    // if now n is the root
                    if (nIsKey) {
                        this.root = n;
                    } 
                    return myNode;
                }
                if (n.getRight() == null && n.getLeft() != null) {
                    this.size -= c;
                    this.unique_size -= 1;
                    DAFNode<K, D> left = n.getLeft();
                    n  = n.getLeft();
                    n.setRight(null);
                    n.setLeft(left.getLeft());
                    // if now n is the root
                    if (nIsKey) {
                        this.root = n;
                    } 
                    return myNode;
                }
                // remove node with two children  
                if (n.getRight() != null && n.getLeft() != null) {
                    this.size -= c;
                    this.unique_size -= 1;
                    // traverse to find the succesor of current node
                    DAFNode<K, D> curr = n.getRight();
                    while (curr.getLeft() != null) {
                        curr = curr.getLeft();
                    }
                    DAFNode<K, D> returnNode = curr;
                    returnNode.setLeft(n.getLeft());
                    returnNode.setRight(n.getRight());
                    curr = null;
                    if (nIsKey) {
                        this.root = returnNode;
                    }
                    return myNode;
                }    
            }
        }
        if (key.compareTo(n.getKey()) < 0) {
            return removeHelper(n.getLeft(), key, nCopy);
        } else {
            return removeHelper(n.getRight(), key, nCopy);
        }
    }

    
    /**
     * Remove a given key from the tree
     * @param key
     * @param nCopy
     * @return the removed node
     */
    public DAFNode<K, D> remove(K key, int nCopy) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        if (lookup(key) == null) {
            return null;
        }
        return removeHelper(this.root, key, nCopy);
    }

    /**
     * Find node with max or min key
     * @param isMax true of false indicating if we are finding max or min
     * @return the node with max or min key
     */
    public DAFNode<K, D> findExtreme(boolean isMax) {
        return findExtremeHelper(isMax, this.root);
    }

    /**
     * A helper function to find extreme
     * @param isMax
     * @param node
     * @return the node with max or min key
     */
    public DAFNode<K, D> findExtremeHelper(boolean isMax, DAFNode<K, D> node) {
        DAFNode<K, D> curr = node;
        if (curr == null) {
            return null;
        }
        if (isMax) {
            while(curr.getRight() != null) {
                curr = curr.getRight();
            }
        } else {
            while(curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return curr;
    }

    /**
     * Get the root of the tree
     * @return the root of the tree
     */
    public DAFNode<K, D> getRoot() {
        if (root == null) {
            return null;
        }
        return this.root;
    }
    /* ------------------ Iterator class --------------------- */
    public class DAFTreeIterator implements Iterator<K> {

        private Stack<DAFNode<K, D>> stack;
        private DAFNode<K, D> root = DAFTree.this.getRoot();
        
        /**
         * Constructor of an iterator
         */
        public DAFTreeIterator() {
            this(DAFTree.this.getRoot());
        }

        /**
         * Helper function to construct an iterator
         * @param root
         */
        public DAFTreeIterator(DAFNode<K, D> root) {
            this.stack = new Stack<DAFNode<K, D>>();
            DAFNode<K, D> curr = root;
            while (curr != null) {
                int counter = curr.getCount();
                int i = 0;
                while (i < counter) {
                    this.stack.push(curr);
                    i++;
                }
                curr = curr.getLeft();
            }
        }

        /**
         * If the iterator hasNext
         * @return true if the iterator hasNext, false otherwise
         */
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            return true;
        }

        /**
         * Get the next of the iterator
         * @return the next key of the iterator
         */
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            DAFNode<K, D> curr = stack.pop();
            
            if (stack.size() >= 1 && stack.peek().equals(curr)) {
                return curr.getKey();
            }
            
            K k = curr.getKey();
            if (curr.getRight() !=  null) {
                int counter = curr.getRight().getCount();
                int i = 0;
                while (i < counter) {
                    this.stack.push(curr.getRight());
                    i++;
                }
                curr = curr.getRight();

                while (curr.getLeft() != null) {
                    int counter1 = curr.getLeft().getCount();
                    int j = 0;
                    while (j < counter1) {
                        this.stack.push(curr.getLeft());
                        j++;
                    }
                    curr = curr.getLeft();
                    
                }
            }
            return k;
        }
    }

    public Iterator<K> iterator() {
        return new DAFTreeIterator();
    }

}
