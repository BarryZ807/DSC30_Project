/* 
 * NAME: zehui Zhang
 * PID: A16151490
 */
import java.util.*;
/**
 * A hash table class
 * 
 * @author Zehui Zhang
 * @since 13/03/2021
 */

@SuppressWarnings("rawtypes")
public class HashTable<K, D> {
    
    protected class TableEntry<K, D> {
        private K key;
        private D data;

        public TableEntry(K key, D data) {
            this.key = key;
            this.data = data;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            if ((obj == null) || !(obj instanceof TableEntry))
                return false;
            return key.equals(((TableEntry<?, ?>) obj).key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        public D getData() {
            return this.data;
        }

        public K getKey() {
            return this.key;
        }
    }

    /* instance variable */
    private LinkedList<TableEntry<K, D>>[] table;
    private int nElems;
    private int capacity;

    /* static variables */
    private static int MINIMUM_SIZE = 10;
    private static double LOAD_FACTOR_CHECK = 2 / 3.0;
    

    /**
     * Constructor that initializes a hash table
     * @param capacity an integer indicating initial capacity
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity < MINIMUM_SIZE) {
            throw new IllegalArgumentException();
        }
        this.table = (LinkedList<TableEntry<K, D>>[]) new LinkedList[capacity];
        this.nElems = 0;
        this.capacity = capacity;
    }

    /**
     * insert a given pair of data
     * @param key
     * @param data
     * @return true if the insert was successful, false if the key is already present
     */
    @SuppressWarnings("unchecked")
    public boolean insert(K key, D data) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (lookup(key) != null) {
            return false;
        }
        // compute load factor and rehash if needed
        double loadFactor = (double)(this.nElems + 1) / capacity;
        if (loadFactor > LOAD_FACTOR_CHECK) {
            this.rehash();
        }
        // insert the given key-data pair
        int h = this.hashValue(key);
        TableEntry entry = new TableEntry<K,D>(key, data);
        if (this.table[h] == null) {
            this.table[h] = new LinkedList<TableEntry<K, D>>();
        }
        table[h].add(entry);
        this.nElems++;

        return true;
    }

    /**
     * a helper method to insert a new entry in a particular table,
     * used especially when rehash
     * @param key
     * @param data
     * @param myTable a linked list of TableEntry objects
     * @return true if the insert was successful, false if the key is already present
     */
    @SuppressWarnings("unchecked")
    public boolean insertInTable(K key, D data, LinkedList<TableEntry<K, D>>[] myTable) {
        int h = this.hashValue(key);
        TableEntry entry = new TableEntry<K,D>(key, data);
        if (myTable[h] == null) {
            myTable[h] = new LinkedList<TableEntry<K, D>>();
        }
        myTable[h].add(entry);
        this.nElems++;

        return true;
    }

    /**
     * update a given key with new data
     * @param key
     * @param newData
     * @return true if the update was successful, false if the key is not already present
     */
    
    @SuppressWarnings("unchecked")
    public boolean update(K key, D newData) {
        if (key == null || newData == null) {
            throw new NullPointerException();
        }
        if (lookup(key) == null) {
            return false;
        }
        boolean b = false;
        if (delete(key)) {
            b = insert(key, newData);
        }
        return b;
    }

    /**
     * Delete a given key from table
     * @param key
     * @return true if the deletion was successful, false if the key is not already present
     */
    
    @SuppressWarnings("unchecked")
    public boolean delete(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (lookup(key) == null) {
            return false;
        }
        // calculate the hash code of the key and get the according table entry linked list
        D data = lookup(key);
        int h = this.hashValue(key);
        LinkedList<TableEntry<K, D>> myLL = table[h];
        // delete the given key
        TableEntry<K, D> myObj = new TableEntry<>(key, data);
        int i = 0;
        while (i < myLL.size()) {
            TableEntry<K, D> curr = myLL.get(i);
            if (curr.equals(myObj)) {
                myLL.remove(i);
                break;
            }
            i++;
        }
        nElems--;
        return true;
    }

    /**
     * Look up if a given key exists
     * @param key
     * @return true if the key exists, false otherwise
     */
    
    @SuppressWarnings("unchecked")
    public D lookup(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int h = this.hashValue(key);
        LinkedList<TableEntry<K, D>> myLL = table[h];

        TableEntry<K, D> myObj = new TableEntry<>(key, null);
        if (myLL == null) {
            return null;
        }
        int i = 0;
        while (i < myLL.size()) {
            TableEntry<K, D> curr = myLL.get(i);
            if (curr.equals(myObj)) {
                return curr.getData();
            }
            i++;
        }
        return null;
    }
    /**
     * Get the size of table
     * @return the size of the table
     */
    public int size() {
        return this.nElems;
    }

    /**
     * Get the capacity of the table
     * @return capacity of the table
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Calculate the hash code
     * @param key
     * @return a hash code integer
     */
    private int hashValue(K key) {
        return Math.abs(key.hashCode() % this.capacity);
    }

    /**
     * Rehash the table
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        this.capacity = this.capacity * 2;
        this.nElems = 0;
        LinkedList<TableEntry<K, D>>[] newTable = 
        (LinkedList<TableEntry<K, D>>[]) new LinkedList[this.capacity * 2];

        for(int i = 0; i < this.table.length; i++) {
            
            if (this.table[i] != null) {
                LinkedList<TableEntry<K, D>> myLL = this.table[i];
                
                for(int j = 0; j < myLL.size(); j++) {
                    TableEntry<K, D> myObj = myLL.get(j);
                    insertInTable(myObj.getKey(), myObj.getData(), newTable);
                }
            }
        }
        this.table = newTable;
     }
}
