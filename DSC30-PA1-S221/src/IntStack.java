/*
    Name: Zehui Zhang
    PID:  A16151490
 */

import java.util.EmptyStackException;

/**
 * Container with ints with stack data type
 * @author Zehui Zhang
 * @since  08/03/2021
 */
public class IntStack {

    //Magic Numbers
    private static final int    MIN_VALID_CAPACITY = 5;
    private static final int    RESIZE_FACTOR     = 2;
    private static final double DEFAULT_LOAD_FACTOR   = 0.75;
    private static final double MIN_LOAD_FACTOR   = 0.67;
    private static final double DEFAULT_SHRINK_FACTOR = 0.25;
    private static final double MAX_SHRINK_FACTOR = 0.33;

    private int[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;
    private int maxCapacity;
    private int initCap;
    private int top;

    /**
     * Constructor that initializes a stack with the given capacity, load factor, and shrink factor
     * The valid range of capacity is (CAP >= 5), that of load factor is (0.67 <= LF <= 1),
     * and that of shrink factor is (0 < SF <= 0.33).
     * @param capacity The amount of value can be stored in the IntStack
     * @param loadF Setup a custom load factor
     * @param shrinkF Setup a custom shrink factor
     */
    public IntStack(int capacity, double loadF, double shrinkF) {
        try{
            // Check if capacity out of valid range
            if(capacity < MIN_VALID_CAPACITY) {
                throw new IllegalArgumentException("Capacity is out of valid range");
            } else if (loadF < MIN_LOAD_FACTOR || loadF > 1) {
                // Check if loadF out of valid range
                throw new IllegalArgumentException("Load factor is out of valid range");
            } else if (shrinkF <= 0 || shrinkF > MAX_SHRINK_FACTOR) {
                // Check if shrinkF out of valid range
                throw new IllegalArgumentException("Shrink factor is out of valid range");
            } else {
                maxCapacity = capacity;
                initCap = capacity;
                data = new int[capacity];
                loadFactor = loadF;
                shrinkFactor = shrinkF;
                nElems = 0;
                top = -1; //Initializes values
            }

        } catch (IllegalArgumentException e) {
            throw  e;//Check for exceptions
        }
    }

    /**
     * Constructor that initializes a stack with the given initial capacity,
     * the given load factor, and the default shrink factor (0.25).
     * @param capacity The amount of value can be stored in the IntStack
     * @param loadF Setup a custom load factor
     */
    public IntStack(int capacity, double loadF) {
        this(capacity, loadF, DEFAULT_SHRINK_FACTOR); //Use this() to call the general constructor
    }

    /**
     *Constructor that initializes a stack with the given initial capacity,
     * the default load factor (0.75), and the default shrink factor (0.25).
     * @param capacity The amount of value can be stored in the IntStack
     */
    public IntStack(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR, DEFAULT_SHRINK_FACTOR);//Use this() to call the general constructor
    }

    /**
     * This method checks if the Stack is empty
     * @return  Boolean value; true if there is nothing in the stack, false otherwise
     */
    public boolean isEmpty() {
        if (nElems == 0) { //Checking for size 0
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method clears all values stored in Stack and resize to default
     */
    public void clear() {
        nElems = 0;
        data = new int[initCap];
        top = -1;

    }

    /**
     * Return the number of elements stored in the Stack
     * @return the size of element
     */
    public int size() {
        return nElems;
    }

    /**
     * Return the max capacity at the current state
     * @return the length of the backed data array
     */
    public int capacity() {
        return maxCapacity;
    }

    /**
     * Returns the top element of the stack.
     * @return the top element
     */
    public int peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException(); // check exceptions
        } else {
            return data[top];
        }

    }

    /**
     * Add to the top of the Stack
     *Double the capacity of the stack before pushing the element if the condition meets.
     * @param element The value need to be added
     */
    public void push(int element) {
        double curLoadFactor = nElems /maxCapacity; //Get the ratio
        if (curLoadFactor >= loadFactor) { //Check condition to double
            maxCapacity = maxCapacity * RESIZE_FACTOR; // double the capacity
            int[] temp = new int[maxCapacity]; //Create temporary holder array
            for(int x = 0; x < data.length; x++){
                temp[x] = data[x];
            }
            data = temp; //Reassign back to the Stack array
        }
        top++;
        data[top] = element;
        nElems++;

    }

    /**
     * Returns and removes the top element of the stack.
     * @return the top element of the stack
     */
    public int pop() {
        // check for exception
        if (this.isEmpty()) {
            throw new EmptyStackException();
        } else {
            int popVal = data[top];
            data[top] = 0;// remove the top data
            nElems--; // decrease the size
            top--;
            double curShrinkFactor = top /maxCapacity;// find the ratio
            if(curShrinkFactor <= shrinkFactor) {
                maxCapacity = maxCapacity / RESIZE_FACTOR; // find the new capacity
                if(maxCapacity < initCap) {//if condition meets, assign the new capacity
                    maxCapacity = initCap;
                }
                int[] temp = new int[maxCapacity];
                for(int x = 0; x < temp.length; x++) {
                    temp[x] = data[x];
                }
                data = temp; //  reassign back to the stack array
            }
            return popVal;
        }

    }

    /**
     * Pushes all numbers in the array elements to the stack.
     * @param elements needed to push
     */
    public void multiPush(int[] elements) {
        // check for exceptions
        if (elements == null) {
            throw new IllegalArgumentException();
        } else {
            // for loop to push one by one
            for(int x = 0; x < elements.length; x++) {
                this.push(elements[x]);
            }
        }
    }

    /**
     * Pops the given amount of elements from the stack.
     * @param amount number of elements need to pop
     * @return elements popped
     */
    public int[] multiPop(int amount) {
        // check for exceptions
        if(amount <=0) {
            throw new IllegalArgumentException();
        } else {
            // check if the amount of elements larger than the size of stack
            if (amount > nElems) {
                amount = nElems - 1;
            }
            int[] returnPops = new int[amount];
            // pop elements one by one
            for(int x = 0; x < amount; x++) {
                returnPops[x] = this.pop();
            }
            return returnPops;
        }

    }

}