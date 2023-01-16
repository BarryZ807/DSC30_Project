/*
 * Name: Zehui Zhang
 * PID:  A16151490
 */

/**
 * A priority queue implementation
 *
 * @param <T> Generic type
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    private dHeap<T> pQueue;
    private static int DEFAULT_SIZE = 5;

    /**
     * Constructor that creates a new priority queue
     * 
     * @param initialSize the given size
     */
    public MyPriorityQueue(int initialSize) {
        this.pQueue = new dHeap<T>(DEFAULT_SIZE, initialSize, true);
    }

    /**
     * Inserts an element into the Priority Queue. The element received cannot be
     * null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        this.pQueue.add(element);
        return true;
    }

    /**
     * Retrieves the head of this Priority Queue (largest element), or null if the
     * queue is empty.
     *
     * @return The head of the queue (largest element), or null if queue is empty.
     */
    public T poll() {
        if (this.isEmpty()){
            return null;
        }
        return this.pQueue.remove();
        
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        pQueue.clear();
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if
     * this queue is empty.
     * 
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() {
        if (this.isEmpty()){
            return null;
        } 
        return this.pQueue.element();
        
    }

    /**
     * Return true is the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        if (pQueue.size() == 0){
            return true;
        }
        return false;
    }

}
