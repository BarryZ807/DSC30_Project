/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */

import java.util.AbstractList;


/**
 * A doubly linked list class
 * @author Zehui Zhang
 * @since 2021-01-28
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            this.data = element;
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
        private Node(T element, Node nextNode, Node prevNode) {
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
            this.prev = null;
            this.next = null;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        this.nelems = 0;
        this.head = new Node(null, this.tail, null);
        this.tail = new Node(null, null, this.head);
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        } else if (this.nelems == 0) {
            Node newnode = new Node(element, this.tail, this.head);
            this.head.setNext(newnode);
            this.tail.setPrev(newnode);
            this.nelems += 1;
            return true;
        }
        else {
            Node lastTail = this.tail.getPrev();
            Node newnode = new Node(element, this.tail, lastTail);
            lastTail.setNext(newnode);
            this.tail.setPrev(newnode);
            this.nelems += 1;
            return true;
        }
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param index index to be added an element
     * @param element data to be added
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index > nelems) {
            throw new IndexOutOfBoundsException();
        } else if (element == null) {
            throw new NullPointerException();
        }

        if (index == 0 && index != nelems) { //add at very beginning
            Node lastHead = this.head.getNext();
            Node newhead = new Node(element, lastHead, this.head);
            this.head.setNext(newhead);
            lastHead.setPrev(newhead);
        } else if (index == nelems && index != 0) { //add at the very end
            Node lastTail = this.tail.getPrev();
            Node newnode = new Node(element, this.tail, lastTail);
            lastTail.setNext(newnode);
            this.tail.setPrev(newnode);
        } else if (index == 0 && index == nelems) { //no elemeent exists
            Node newnode = new Node(element, this.tail, this.head);
            this.head.setNext(newnode);
            this.tail.setPrev(newnode);
        } else { //add in the middle of list
            Node curr = this.head.getNext();
            for (int i = 1; i < index; i++){
                curr = curr.getNext();
            }
            Node newnode = new Node(element, curr.getNext(), curr);
            curr.getNext().setPrev(newnode);
            curr.setNext(newnode);
        }
        this.nelems += 1;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element the data to be searched
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object element) {
        T data = (T) element;
        Node curr = this.head.getNext();
        for (int i = 0; i < nelems; i++) {
            if (curr.getElement().equals(data)) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index the index of elements to be retrieved
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > nelems -1) {
            throw new IndexOutOfBoundsException();
        }
        Node curr = this.head.getNext();
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index the index of Node to be got
     */
    private Node getNth(int index) {
        Node curr = this.head.getNext();
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr;
    }

    /**
     * Determine if the list empty
     *
     */
    @Override
    public boolean isEmpty() {
        if (this.nelems != 0) {
            return false;
        }
        return true;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index the index of data to be removed
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.nelems - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            T data = this.head.getNext().getElement();
            this.nelems -= 1;
            this.head.getNext().remove();
            return data;
        } else if (index == nelems - 1) {
            T data = this.tail.getPrev().getElement();
            this.nelems -= 1;
            this.tail.getPrev().remove();
            return data;
        } else {
            Node curr = this.head.getNext();
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            T data = curr.getElement();
            this.nelems -= 1;
            curr.remove();
            return data;
        }
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index index of element to be set
     * @param element element to be set
     * @return data value previously stored at the position
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index > nelems - 1) {
            throw new IndexOutOfBoundsException();
        } else if (element == null) {
            throw new NullPointerException();
        }
        T data = null;
        if (index == nelems -1) {
            data = this.tail.getPrev().getElement();
            this.tail.getPrev().setElement(element);
        } else {
            Node curr = this.head.getNext();
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            data = curr.getElement();
            curr.setElement(element);
        }
        return data;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return amount of elements currently on the list
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * @return string representation of doubly linked list
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

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Inserts another linked list of the same type into this one
     *
     * @param index a given index of this list
     * @param otherList an other list to be manipulated
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {
        //Determine if index is valid
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.nelems += otherList.size();
        if (index == 0) {
            Node myhead = this.head.getNext();
            Node newhead = otherList.getNth(0);
            Node othertail = otherList.getNth(otherList.size()-1);
        
            this.head.setNext(newhead);
            newhead.setPrev(this.head);
            othertail.setNext(myhead);
            myhead.setPrev(othertail);

        } else if (index == this.size() - otherList.size()) {
            Node mytail = this.tail.getPrev();
            Node newhead = otherList.getNth(0);
            Node othertail = otherList.getNth(otherList.size()-1);
            
            mytail.setNext(newhead);
            newhead.setPrev(mytail);
            this.tail.setPrev(othertail);
            othertail.setNext(this.tail);

        } else {
            Node curr = this.getNth(index - 1);
            Node next = curr.getNext();
            Node newhead = otherList.getNth(0);
            Node othertail = otherList.getNth(otherList.size()-1);
            curr.setNext(newhead);
            newhead.setPrev(curr);
            next.setPrev(othertail);
            othertail.setNext(next);
        }
        

    }

    /**
     * Determine the starting indices that match the subSequence
     *
     * @param subsequence a subsequence to be found
     * @return arr an array of strating indices of subsequences in this
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        if (subsequence.size() > this.size()) {
            int[] arr = {};
            return arr;
        }
        // A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        // Add implementation to find the starting indices
        for (int i = 0; i < this.size() - subsequence.size() + 1; i++) {
            boolean overlap = true;

            for (int j = 0; j < subsequence.size(); j++) {
                if (this.getNth(i+j).getElement() != subsequence.getNth(j).getElement()){
                    overlap = false;
                }
            }
            if (overlap) {
                indices.add(i);
            }
        }
        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }

}