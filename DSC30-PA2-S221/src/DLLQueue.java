import java.util.NoSuchElementException;

/**
 * Queue implementation using Doubly-linked list.
 */
public class DLLQueue {

    private DoublyLinkedList queue;

    /**
     * Initialize the instance variables
     */
    public DLLQueue() {
        queue = new DoublyLinkedList();

    }

    /**
     * Return the number of elements
     * @return num of elements
     */
    public int size() {
        return queue.size();
    }

    /**
     * Chek if queue is empty
     * @return boolean
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Add given data to queue
     * @param data integers
     */
    public void enqueue(int data) {
        queue.add(data);

    }

    /**
     * Remove and return the first element
     * @return the first element
     */
    public int dequeue() {
        if(queue.isEmpty()) {
             throw  new NoSuchElementException();
        } else {
            return queue.remove(0);
        }
    }

    /**
     * Peek and return the top element
     * @return the top element
     */
    public int peek() {
        if(queue.isEmpty()) {
            throw  new NoSuchElementException();
        } else {
            return queue.get(0);
        }
    }

}
