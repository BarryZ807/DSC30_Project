/*
 * Name: Zehui Zhang
 * PID:  A16151490
 */

import java.util.*;

/**
 *faster insertion with the cost of possibly slower deletion.
 *
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min
    private int DEFAULT_SIZE = 6;
    private int doublingVal = 2;
    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        heap = (T[]) new Comparable[DEFAULT_SIZE];
        d = doublingVal;
        nelems = 0;
        isMaxHeap = true;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        heap = (T[]) new Comparable[heapSize];
        d = doublingVal;
        nelems = 0;
        isMaxHeap = true;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        heap = (T[]) new Comparable[heapSize];
        this.d = d;
        nelems = 0;
        this.isMaxHeap = isMaxHeap;
    }

    @Override
    /**
     * Returns the number of elements stored in the heap.
     */
    public int size() {
        return this.nelems;
    }

    @Override
    /**
     * Adds the given data to the heap.
     */
    public void add(T data) throws NullPointerException {
        addhelp(data);
    }

    /**
     * Helper function for add
     * @param data data added
     */
    private void addhelp(T data){
        if (data == null) {
            throw new NullPointerException();
        }

        if(this.heap.length == this.size()) {
            this.resize();
        }

        this.heap[nelems] = data;
        bubbleUp(nelems);
        nelems++;
    }

    @Override
    /**
     * Returns and removes the root element from the heap.
     */
    public T remove() throws NoSuchElementException {
        return removehelp();
    }

    /**
     * helper function of remove
     * @return the root element from the heap.
     */
    private T removehelp(){
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }

        swap(0, nelems-1);
        T toReturn = this.heap[nelems-1];
        nelems--;
        trickleDown(0);
        return toReturn;
    }

    @SuppressWarnings("unchecked")
    @Override
    /**
     * Clear all elements in the heap.
     */
    public void clear() {
        T[] array = (T[]) new Comparable[this.heap.length];
        this.heap = array;
        this.nelems = 0;
    }

    /**
     * Returns the root element of the heap.
     * @return root element of the heap.
     * @throws NoSuchElementException
     */
    public T element() throws NoSuchElementException {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        else {
            return this.heap[0];
        }
    }

    /**
     * helper function
     * @param index index
     */
    private void trickleDown(int index) {
        int child = this.d * index + 1;
        T pValue = this.heap[index];

        while (child < this.size()) {
            T maxVal = pValue;
            int maxIndex = -1;

            for (int x = 0; x < d && x + child < this.size(); x++){
                if(compareHelper(x + child, maxVal)) {
                    maxVal = this.heap[x + child];
                    maxIndex = x + child;
                }

            }
            if (maxVal.compareTo(pValue) == 0) {
                return;
            } else {
                swap(index, maxIndex);
                index = maxIndex;
                child = this.d * index + 1;
            }
        }
    }


    /**
     * check the child node is bigger the the max element.
     *
     * @param child  child node's index
     * @param max the element to be compared
     */
    private boolean compareHelper(int child, T max) {

        if (isMaxHeap) {
            if (this.heap[child].compareTo(max) > 0 ) {
                return true;
            } else {
                return false;
            }
        } else {
            if (this.heap[child].compareTo(max) < 0 ) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * helper function
     * @param index index
     */
    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = parent(index);
            if (compareFuncBubble(index, parentIndex)) {
                return;
            } else {
                swap(index, parentIndex);
                index = parentIndex;
            }
        }
    }

    /**
     * helper function
     * @param child the child
     * @param parentIndex parent index
     * @return result
     */
    private boolean compareFuncBubble(int child, int parentIndex){
        if (this.isMaxHeap) {
            return this.heap[child].compareTo(this.heap[parentIndex]) <= 0;
        } else {
            return this.heap[child].compareTo(this.heap[parentIndex]) >= 0;
        }
    }

    @SuppressWarnings("unchecked")
    /**
     *  doubles the size of array before adding if is full
     */
    private void resize() {
        T[] arr = (T[]) new Comparable[this.heap.length * doublingVal];
        System.arraycopy(this.heap, 0, arr, 0, this.heap.length);
        this.heap = arr;
    }

    /**
     *  finds the index of parent
     * @param index index
     * @return the parent index
     */
    private int parent(int index) {
        if(index - d <= 0) {
            return 0;
        } else {
            return (index-1)/d;
        }
    }

    /**
     * helper
     * @param index1 first index
     * @param index2 second index
     */
    private void swap(int index1, int index2){
        T swap = this.heap[index1];  //stores index
        this.heap[index1] = this.heap[index2];
        this.heap[index2] = swap;
    }

}
