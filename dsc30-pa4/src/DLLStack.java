/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */

/**
 * A doubly linked list stack 
 * @param <T> generic container
 * @author Zehui Zhang
 * @since 2021-02-01
 */
public class DLLStack<T> {

    private DoublyLinkedList<T> stack;

    public DLLStack() {
        stack = new DoublyLinkedList<T>();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        stack.add(data);
    }

    public T pop() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.get(stack.size() - 1);
    }

}
