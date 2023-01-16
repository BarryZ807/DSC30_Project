/*
 * Name: Zehui Zhang
 * PID: A16151490
 */

import java.util.Arrays;

/**
 * A hash table implementation
 *   
 * @author Zehui Zhang
 * @since 2021-02-25
 */
public class HashTable implements IHashTable {

    /* the bridge for lazy deletion */
    private static final String bridge = new String("[BRIDGE]".toCharArray());

    /* static variables */
    private static final int DEFAULT_SIZE = 15;
    private static final int MINIMUM_SIZE = 5;
    private static final int LEFT_SHIFT = 5;
    private static final int RIGHT_SHIFT = 27;

    /* instance variables */
    private int size; // number of elements stored
    private int capacity;
    private String[] table; // data table
    private int rehashNum = 0;
    private int collision = 0;
    private String stat;
    


    public HashTable() {
        this(DEFAULT_SIZE);
    }

    public HashTable(int capacity) {
        if (capacity < MINIMUM_SIZE) {
            throw new IllegalArgumentException();
        }
        this.size = 0;
        this.table = new String[capacity];
        this.capacity = capacity;
        this.stat = "";
    }

    @Override
    public boolean insert(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (lookup(value)) {
            return false;
        }
        // compute load factor and rehash if needed
        double loadFactor = (double)(this.size + 1) / capacity;
        if (loadFactor > 0.55) {
            // update rehash number, stats string and collision number
            this.rehashNum++;
            this.stat += "Before rehash #" + rehashNum;
            this.stat += ": load factor " + String.format("%.2f", loadFactor);
            this.stat += ", " +collision + " collisions.\n";
            this.collision = 0;
            rehash();
        }
        //compute hash code
        int hashCode = hashString(value);
        if (hashCode < 0) {
            hashCode = this.capacity + hashCode;
        }
        while (this.table[hashCode] != null && !this.table[hashCode].equals(bridge)) {
            collision += 1;
            hashCode = (hashCode + 1) % capacity;
        }
        // populate the index computed with given value
        this.table[hashCode] = value;
        this.size += 1;
        return true;
    }

    @Override
    public boolean delete(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (!lookup(value)) {
            return false;
        }
        // compute hash code
        int hashCode = hashString(value);
        if (hashCode < 0) {
            hashCode = this.capacity + hashCode;
        }
        String curr = this.table[hashCode];
        while (curr != null && !curr.equals(bridge) && !curr.equals(value)) {
            hashCode = (hashCode + 1) % capacity;
            curr = this.table[hashCode];
        }

        // delete the value
        if (curr.equals(value)) {
            this.table[hashCode] = bridge;
            this.size -= 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean lookup(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (this.size == 0) {
            return false;
        }
        // compute hash value
        int hashCode = hashString(value);
        if (hashCode < 0) {
            hashCode = this.capacity + hashCode;
        }
        String curr = this.table[hashCode];
        while (curr != null && !curr.equals(bridge) && !curr.equals(value)) {
            hashCode = (hashCode + 1) % capacity;
            curr = this.table[hashCode];
        }
        // find the value or not
        if (curr != null && curr.equals(value)) {
            return true;
        } 
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    public String getStatsLog() {
        return this.stat;
    }

    private void rehash() {
        String[] originalTable = this.table;
        int originalCapacity = this.capacity;
        this.table = new String[capacity * 2];
        this.capacity = this.capacity * 2;
        this.size = 0;
        // re-probe
        for (int i = 0; i < originalCapacity; i++) {
            if (originalTable[i] != null && !originalTable[i].equals(bridge)) {
                insert(originalTable[i]);
            }
        }
    }

    private int hashString(String value) {
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            int left = hashValue << LEFT_SHIFT;
            int right = hashValue >>> RIGHT_SHIFT;
            hashValue = (left | right) ^ value.charAt(i);
        }
        return hashValue % capacity;
    }


    /**
     * Returns the string representation of the hash table.
     * This method internally uses the string representation of the table array.
     * DO NOT MODIFY. You can use it to test your code.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
