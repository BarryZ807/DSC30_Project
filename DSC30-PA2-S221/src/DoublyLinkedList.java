/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Doubly-Linked List Implementation
 *
 * @author Zehui Zhang
 * @since 08/10/2021
 */

    public class DoublyLinkedList {

    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        int data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(int element) {
            // Initialize variables
            data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(int element, Node nextNode, Node prevNode) {
            data = element;
            next = nextNode;
            prev = prevNode;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            prev = p;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Set the element
         *
         * @param e new element
         */
        public void setElement(int e) {
            data = e;
        }

        /**
         * Accessor to get the next Node in the list
         */
        public Node getNext() {
            return next;
        }

        /**
         * Accessor to get the prev Node in the list
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public int getElement() {
            return data;
        }

        /**
         * Remove this node from the list. Update previous and next nodes
         */
        public void remove() {
            prev.next = next;
            next.prev = prev;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        nelems = 0;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return Number of elements currently on the list
     */
    public int size() {
        return this.nelems;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index The index of the desired element.
     * @return The element stored in the Node with the desired index.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    public int get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size()-1) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = getNth(index);
        return newNode.getElement();
    }

    /**
     * Add an element to the end of the list
     *
     * @param data data to be added
     */
    public boolean add(int data) {
        Node newNode = new Node(data);
        if (nelems == 0) {
            newNode.prev = head;
            head.next = newNode;
            newNode.next = tail;
            tail.prev = newNode;
            this.nelems++;
            return true;
        } else {
            tail.prev.next = newNode;
            newNode.prev = tail.prev;
            newNode.next = tail;
            tail.prev = newNode;
            this.nelems++;
            return true;
        }
    }

    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room.
     *
     * @param index Where in the list to add the element.
     * @param data  Data to be added.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    public void add(int index, int data)
            throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(data);
        Node previousNode;
        Node nextNode;

        if(index == 0) {
            previousNode = head;
            nextNode = head.next;
        } else if (index == this.size() - 1) {
            previousNode = tail.prev;
            nextNode = tail;
        } else {
            previousNode = getNth(index - 1);
            nextNode = getNth(index + 1);
        }

        newNode.next = nextNode;
        newNode.prev = previousNode;
        previousNode.next = newNode;
        nextNode.prev = newNode;
        this.nelems++;
    }

    /**
     * Sets the value of an element at a certain index in the list.
     *
     * @param index Where in the list the data should be added.
     * @param data  Data to add.
     * @return Element that was previously at this index.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    public int set(int index, int data)
            throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size()-1) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = this.getNth(index);
        int returnElem = newNode.getElement();
        newNode.setElement(data);
        return returnElem;
    }

    /**
     * remove the element from position index in the list
     * @param index:index where in the list the data should be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if index<0 || index >= size
     */
    public int remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size()-1) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = this.getNth(index);
        newNode.remove();
        this.nelems--;
        return newNode.getElement();
    }

    /**
     * Clear the linked list
     */
    public void clear() {
        this.head.next = tail;
        this.tail.prev = head;
        this.nelems = 0;
    }

    /**
     * Determine if the list empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return nelems == 0;
    }

    // Helper method to get the Node at the Nth index
    private Node getNth(int index) {
        if (index == 0){
            return head.next;
        }
        Node newNode = head.next;
        for(int x = 0; x < this.nelems; x++) { //loops through list
            if (x == index-1){ //checks if at end of list
                newNode = newNode.next;
                break;
            }
            else{
                newNode = newNode.next;
            }
        }

        return newNode;
    }

    /**
     * Determine if this list contains the given data
     * @param data data to find
     * @return true if list contains given data, false otherwise
     */
    public boolean contains(Object data) {
        int ele = (int)data;
        Node newNode = head.next;
        for(int x = 0; x < this.size() - 1; x++) {
            if(newNode.getElement() == ele) {
                return true;
            } else {
                newNode = newNode.next;
            }
        }
        return false;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     * @return string representation
     */
    @Override
    public String toString() {
        String s = "[(head) -> ";
        Node curr = this.head.getNext();
        for (int i = 0; i < this.nelems; i++) {
            s += curr.getElement();
            s += " -> ";
            curr = curr.getNext();
        }
        s += "(tail)]";
        return s;
    }
}