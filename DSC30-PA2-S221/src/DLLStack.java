import java.util.EmptyStackException;

/**
 * Stack implementation using Doubly-linked list.
 */
public class DLLStack {

    private DoublyLinkedList stack;

    /**
     * Initialize the instance variable
     */
    public DLLStack() {
        stack = new DoublyLinkedList();

    }

    /**
     * Return the number of elements
     * @return num of elements
     */
    public int size() {
        return stack.size();
    }

    /**
     * Chek if stack is empty
     * @return boolean
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Add the given data to stack
     * @param data integers
     */
    public void push(int data) {
        stack.add(data);
    }

    /**
     * Remove and return the first element
     * @return the first element
     */
    public int pop() {
        if(stack.isEmpty()) {
            throw new EmptyStackException();
        } else {
            return stack.remove(stack.size() - 1);
        }
    }

    /**
     * Peek and return the top element
     * @return the top element
     */
    public int peek() {
        if(stack.isEmpty()) {
            throw new EmptyStackException();
        } else {
            return stack.get(stack.size() - 1);
        }
    }

}
