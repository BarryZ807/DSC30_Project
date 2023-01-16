/*
    Name: Zehui Zhang
    PID:  A16151490
 */

import java.util.EmptyStackException;

/**
 * In addition to the basic operations that Stack ADT has (e.g. push, pop, peek),
 * the IntStack will have several special features which will be explained below.
 * @author Zehui Zhang
 * @since  13/01/2021
 */
public class IntStack {

    /* instance variables, feel free to add more if you need */
    private int[] data;
    private int nElems;
    private static int initialCapacity;
    private int capacity;
    private double loadFactor;
    private double shrinkFactor;
    private int cap = 5;
    private double lf = 0.67;
    private double sf = 0.33;
    private int even = 2;
    private double quarter = 0.25;
    private double threequarters = 0.75;


    /**
     * Constructor that initializes a stack with the given capacity,
     * load factor and shrink factor.
     * @param capacity an integer indicating initial capacity of stack
     * @param loadF the load factor
     * @param shrinkF the shrink factor
     */
    public IntStack(int capacity, double loadF, double shrinkF) {
        if (capacity >= cap && lf <= loadF && loadF <= 1 && 0 < shrinkF && shrinkF <= sf) {

            this.data = new int[capacity];
            this.nElems = 0;
            this.loadFactor = loadF;
            this.shrinkFactor = shrinkF;
            initialCapacity = capacity;
            this.capacity = capacity;


        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructor that initializes a stack with the given capacity,
     * load factor.
     * @param capacity an integer indicating initial capacity of stack
     * @param loadF the load factor
     */
    public IntStack(int capacity, double loadF) {
        if (capacity >= cap && lf <= loadF && loadF <= 1) {
            this.data = new int[capacity];
            this.nElems = 0;
            this.loadFactor = loadF;
            this.shrinkFactor = quarter;
            initialCapacity = capacity;
            this.capacity = capacity;

        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructor that initializes a stack with the given capacity,
     * load factor.
     * @param capacity an integer indicating initial capacity of stack
     */
    public IntStack(int capacity) {
        if (capacity >= cap) {
            this.data = new int[capacity];
            this.nElems = 0;
            this.loadFactor = threequarters;
            this.shrinkFactor = quarter;
            initialCapacity = capacity;
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if the stack is empty. Returns true if it is empty, false otherwise.
     * @return a boolean indicating whether stack is empty
     */
    public boolean isEmpty() {
        if (nElems == 0) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Clears all elements in the stack.
     */
    public void clear() {
        nElems = 0;
    }

    /**
     * Returns the number of elements currently stored in the stack.
     * @return stack size
     */
    public int size() {
        return nElems;
    }

    /**
     * Returns the maximum number of elements the stack currently can store.
     * In other words, the length of the backed data array.
     * @return capacity of stack
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Returns the top element of the stack.
     * @return first integer on stack
     */
    public int peek() {
        if (nElems == 0){
            throw new EmptyStackException();
        }
        return data[nElems-1];
    }

    /**
     * Returns the top element of the stack.
     */
    public void push(int element) {
        if ((double)nElems / capacity >= loadFactor){
            capacity = capacity * even;
            int[] mystack = new int[capacity];

            for(int i = 0; i < nElems; i++){
                mystack[i] = data[i];
            }
            this.data = mystack;
        }
        data[nElems] = element;
        nElems ++;
    }

    /**
     * Returns and removes the top element of the stack.
     * @return top element of stack
     */
    public int pop() {
        int element = 0;
        if (nElems > 0){
            element = data[nElems - 1];
        } else {
            throw new EmptyStackException();
        }
        nElems--;
        if ((double) nElems / capacity <= shrinkFactor){
            capacity = capacity / even;
            if (capacity < initialCapacity){
                capacity = initialCapacity;
            }
        }
        return element;
    }

    /**
     * Pushes all numbers in the array elems to the stack.
     */
    public void multiPush(int[] elements) {
        if (elements.length == 0){
            throw new IllegalArgumentException();
        }
        int i = 0;
        while (i < elements.length) {
            if ((double) nElems / capacity >= loadFactor){
                capacity = capacity * even;
                int[] mystack = new int[capacity];
                for(int j = 0; j < nElems; j++){
                    mystack[j] = data[j];
                }
                this.data = mystack;
            }
            data[nElems] = elements[i];
            nElems++;
            i++;
        }
    }

    /**
     * Pops the given amount of elements from the stack
     * @return elems top amount element of stack
     */
    public int[] multiPop(int amount) {
        if (amount < 0){
            throw new IllegalArgumentException();
        }
        int number = Math.min(amount, nElems);
        int[] elems = new int[number];

        for(int i = 0; i < number; i++){
            elems[i] = data[nElems-1];
            nElems --;
            if ((double)nElems / capacity <= shrinkFactor){
                capacity = capacity / even;
                if (capacity < initialCapacity){
                    capacity = initialCapacity;
                }
            }
        }
        return elems;
    }
}
