/*
 * Name: Zehui Zhang
 * PID:  A16151490
 */

import java.util.*;

/**
 * A d-ary heap implementation
 * 
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min
    private static int DEFAULT_SIZE = 6; //default saize of heap
    private static int BINARY = 2;

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this.heap = (T[]) new Comparable[DEFAULT_SIZE];
        this.nelems = 0;
        this.isMaxHeap = true;
        this.d = BINARY;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
        this.isMaxHeap = true;
        this.d = BINARY;
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
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;
        this.d = d;
    }

    @Override
    public int size() {
        return this.nelems;
    }

    @Override
    public void add(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }
        if (this.heap.length == nelems) {
            resize();
        }
        this.heap[nelems] = data;
        nelems++;
        bubbleUp(nelems - 1);

    }

    @Override
    public T remove() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        T curr = this.heap[0];
        this.heap[0] = this.heap[nelems - 1];
        nelems--;
        trickleDown(0);
        return curr;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        for (int i = 0; i < nelems; i++) {
            this.heap[i] = null;
        }
        this.nelems = 0;
    }

    public T element() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        return this.heap[0];
    }

    private void trickleDown(int index) {
        int c = findMaxChild(index);
        T temp = heap[index];
        if (!this.isMaxHeap){
            while (ithChild(index, 1) < nelems) {
                c = findMinChild(index);
                if (this.heap[c].compareTo(temp) < 0) {
                    this.heap[index] = this.heap[c];
                } else {
                    break;
                }
                index = c;
            }
        } else if (this.isMaxHeap) {
            while (ithChild(index, 1) < nelems) {
                c = findMaxChild(index);
                if (this.heap[c].compareTo(temp) > 0) {
                    this.heap[index] = this.heap[c];
                } else {
                    break;
                }
                index = c;
            }
        }
        this.heap[index] = temp;
    }

    private void bubbleUp(int index) {
        T temp = this.heap[index];
        while (!this.isMaxHeap && index > 0 && temp.compareTo(this.heap[parent(index)]) < 0) {
            this.heap[index] = heap[parent(index)];
            index = parent(index);
        }
        while (this.isMaxHeap && index > 0 && temp.compareTo(this.heap[parent(index)]) > 0) {
            this.heap[index] = heap[parent(index)];
            index = parent(index);
        }
        this.heap[index] = temp;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        if (this.heap.length == nelems) {
            T[] array = (T[]) new Comparable[nelems * BINARY];
            for (int i = 0; i < nelems; i++) {
                array[i] = this.heap[i];
            }
            this.heap = array;
        }
    }

    private int parent(int index) {
        return (index - 1) / d;
    }

    private int ithChild(int index, int i) {
        return d * index + i;
    }

    private int findMinChild(int index) {
        int min = ithChild(index, 1);
        int i = BINARY;
        int t = ithChild(index, i);
        
        while (i <= d && t < nelems) {
            if (this.heap[t].compareTo(this.heap[min]) < 0) {
                min = t;
            }
            t = ithChild(index, ++i);
        }
        return min;
    }

    private int findMaxChild(int index) {
        int max = ithChild(index, 1);
        int i = BINARY;
        int t = ithChild(index, i);
        
        while (i <= d && t < nelems) {
            if (this.heap[t].compareTo(this.heap[max]) > 0) {
                max = t;
            }
            t = ithChild(index, ++i);

        }
        return max;
    }

    public String toString() {
      
        StringBuilder builder = new StringBuilder();
   
        for (int i = 0; i < nelems; i++) {
     
        builder.append(heap[i]).append(" ");
      
        }
       
        return builder.toString();
        }
}
