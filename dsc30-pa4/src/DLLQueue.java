/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */

/**
 * A doubly linked list queue
 * @param <T> generic container
 * @author Zehui Zhang
 * @since 2021-02-01
 */
public class DLLQueue<T> {

    private DoublyLinkedList<T> queue;

    public DLLQueue() {
        queue = new DoublyLinkedList<T>();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        queue.add(data);
    }

    public T dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.remove(0);
    }

    public T peek() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.get(0);
    }

}
