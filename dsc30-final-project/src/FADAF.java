/* 
 * NAME: zehui Zhang
 * PID: A16151490
 */
import java.util.*;
/**
 * A FADAF class
 * 
 * @author Zehui Zhang
 * @since 13/03/2021
 */

@SuppressWarnings("rawtypes")
public class FADAF<K extends Comparable<? super K>, D> {

    /* instance variable */
    private HashTable<K, D> table;
    private DAFTree<K, D> tree;

    /**
     * Constructor of an FADAF instance with given capacity
     * @param capacity
     */
    public FADAF(int capacity) {
        this.table = new HashTable<K, D>(capacity);
        this.tree = new DAFTree<K, D>();
    }

    /**
     * Get the size of the FADAF
     * @return the size of the FADAF
     */
    public int size() {
        return this.tree.size();
    }

    /**
     * Get the unique size of the FADAF
     * @return the unique size of the FADAF
     */
    public int nUniqueKeys() {
        return this.tree.nUniqueKeys();
    }

    /**
     * Insert a given key-data pair to the FADAF
     * @param key
     * @param data
     * @param nCopy
     * @return true if successful, false if the data is ignored
     */
    public boolean insert(K key, D data, int nCopy) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }

        D myData = table.lookup(key);
        if (myData == null) {
            this.table.insert(key, data);
            this.tree.insert(key, data, nCopy);
            return true;
        } else if (myData.equals(data)) {
            tree.insert(key, data, nCopy);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Lookup if a certain key exists
     * @param key
     * @return the counter of key in FADAF, 0 if the key does not exists
     */
    public int lookup(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (this.table.lookup(key) == null) {
            return 0;
        } else {
            return this.tree.lookup(key).getCount();
        }
    }

    /**
     * Remove a certain key from FADAF for nCopy times
     * @param key
     * @param nCopy
     * @return true if successful, false if the key is not present
     */
    public boolean remove(K key, int nCopy) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFTree<K, D>.DAFNode<K, D> n = this.tree.remove(key, nCopy);
        if (n == null) {
            return false;
        } 
        if (this.tree.lookup(key) == null) {
            this.table.delete(key);
        }
        return true;
    }

    /**
     * Remove all occurence of given key
     * @param key
     * @return true if successful, false if the key is not present
     */
    public boolean removeAll(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.table.lookup(key) == null) {
            return false;
        }
        int count = this.tree.lookup(key).getCount();
        this.tree.remove(key, count);
        this.table.delete(key);
        return true;
    }

    /**
     * Upate a new data to given key
     * @param key
     * @param newData
     * @return true if successful, false if the key is not present
     */
    public boolean update(K key, D newData) {
        if (key == null || newData == null) {
            throw new NullPointerException();
        }
        if (table.lookup(key) == null) {
            return false;
        }
        boolean b = table.update(key, newData);
        if (b) {
            this.tree.updateData(key, newData);
        }
        return b;
    }

    /**
     * Get all keys in a LinkedList<K>
     * @param allowDuplicate indicating if we allow duplictate 
     * @return a linkedList of keys
     */
    public List<K> getAllKeys(boolean allowDuplicate) {
        LinkedList<K> l = new LinkedList<K>();
        Iterator<K> it = tree.iterator();
        
        if (allowDuplicate) {
            while (it.hasNext()) {
                l.add(it.next());
            }
        } else {
            LinkedList<K> history = new LinkedList<K>();
            while(it.hasNext()) {
                K key = it.next();
                if (!history.contains(key)) {
                    l.add(key);
                    history.add(key);
                }
            }
        }
        return l;
    }

    /**
     * Get unique keys in a certain range
     * @param lower
     * @param upper
     * @return a linkedList of keys in a certain range
     */
    public List<K> getUniqueKeysInRange(K lower, K upper) {
        LinkedList<K> l = new LinkedList<K>();
        Iterator<K> it = tree.iterator();
        LinkedList<K> history = new LinkedList<K>();
        while(it.hasNext()) {
            K key = it.next();
            if (!history.contains(key) && lower.compareTo(key) < 0 && upper.compareTo(key) > 0) {
                l.add(key);
                history.add(key);
            }
        }
        return l;
    }

    /**
     * Get the minimum key 
     * @return the minimum key
     */
    public K getMinKey() {
        if (tree.size() == 0) {
            return null;
        }
        return tree.findExtreme(false).getKey();
        
    }

    /**
     * Get the maximum key
     * @return the maximum key
     */
    public K getMaxKey() {
        if (this.tree.size() == 0) {
            return null;
        }
        return tree.findExtreme(true).getKey();
    }

}
