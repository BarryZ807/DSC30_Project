/*
    Name: Zehui Zhang
    PID:  A16151490
 */

import java.util.EmptyStackException;

/**
 * A method that will decompose a normal arithmetic expression into a special kind of expression
 * @author Zehui Zhang
 * @since  13/01/2021
 */
public class ExprDecomposer {

    /**
     * Decomposes the given arithmetic expression
     * @param expr an expression string
     * @return decom a character list of decomposed experssion
     */
    public char[] decompose(String expr) {
        // get the length of decomposed expression
        int num = 0;
        int numNoParen = 0;
        for (int i = 0; i < expr.length(); i++){
            char c = expr.charAt(i);
            if ( isDigit(c) || isOperator(c) || c == '(' || c == ')'){
                num++;
            }
            if ( isDigit(c) || isOperator(c)){
                numNoParen++;
            }
        }
        // construct a char list of nomral expression with an extra prantheses outside
        char[] ex = new char[num+2];
        ex[0] = '(';
        int j = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (isDigit(c) || isOperator(c) || c == '(' || c == ')'){
                ex[j+1] = c;
                j++;
            }
        }
        ex[num+1] = ')';


        // go through the normal expression and compute the decomposed expression
        char[] decom = new char[numNoParen];
        int index = 0;
        CharStack paren = new CharStack(30);
        CharStack digits = new CharStack(30);
        CharStack digitsTemp = new CharStack(30);
        CharStack ops = new CharStack(30);

        for (int i = 0; i< ex.length; i++) {
            char c =  ex[i];

            //different cases of c
            if ( c == '(') {
                paren.push(c);
            }else if ( isDigit(c)) {
                digits.push(c);
            }else if (isOperator(c)){
                ops.push(c);
            }else if (c == ')') {
                // Case 1 : the top of paren stack is (
                if (paren.peek() == '(') {
                    // push numbers in digit into digitTemp
                    while (!digits.isEmpty()){
                        digitsTemp.push(digits.pop());
                    }
                    //add all numbers in digitTemp to decom
                    while (!digitsTemp.isEmpty()){
                        decom[index] = digitsTemp.pop();
                        index++;
                    }
                    // then push one operator into decom
                    if (!ops.isEmpty()){
                        decom[index] = ops.pop();
                    }
                    index++;
                    //pop out the ( on top of parentheses stack
                    if (!paren.isEmpty()){
                        paren.pop();
                    }
                }else if (paren.peek() == ')'){ //Case 2: the top of paren stack is )
                    decom[index] = ops.pop();
                    index++;
                }
            }
        }
        return decom;
    }

    /**
     * UTILITY METHOD, DO NOT MODIFY *
     * Check if the given token represents a digit
     * @param token to check
     * @return boolean true if token is a digit, false otherwise
     */
    private boolean isDigit(char token) {
        return (token >= '0') && (token <= '9');
    }

    /**
     * UTILITY METHOD, DO NOT MODIFY *
     * Check if the given token represents an operator
     * @param token to check
     * @return boolean true if token is an operator, false otherwise
     */
    private boolean isOperator(char token) {
        return (token == '+') || (token == '-') || (token == '*') || (token == '/');
    }

    /**
     * Inner class CharStack.
     * Note: You can remove methods and variables that you will not use for
     * this question, but you must keep both push() and pop() methods and they
     * should function properly.
     */
    protected class CharStack {

        /* TODO: add variables, constructors and methods that
                 you will use to implement the decompose() method.  */
        /* Declare constants and magic numbers */
        private char[] stack;
        private int nElems;
        private int initialCapacity;
        private int capacity;
        private double loadF;
        private double shrinkF;
        private int cap = 5;
        private double lf = 0.67;
        private double sf = 0.33;
        private int even = 2;
        private double quarter = 0.25;
        private double threequarters = 0.75;

        /**
         * Constructor that initializes a stack with the given capacity,
         * load factor and shrink factor.
         * @param capacity an integer indicating initial capacity of stack
         * @param loadF the load factor
         * @param shrinkF the shrink factor
         */
        public CharStack(int capacity, double loadF, double shrinkF) {

            if (capacity >= cap && lf <= loadF && loadF <= 1 && 0 < shrinkF && shrinkF <= sf) {

                this.stack = new char[capacity];
                this.nElems = 0;
                this.loadF = loadF;
                this.shrinkF = shrinkF;
                initialCapacity = capacity;
                this.capacity = capacity;


            } else {
                throw new IllegalArgumentException();
            }
        }

        /**
         * Constructor that initializes a stack with the given capacity,
         * load factor.
         * @param capacity an integer indicating initial capacity of stack
         * @param loadF the load factor
         */
        public CharStack(int capacity, double loadF) {
            if (capacity >= cap && lf <= loadF && loadF <= 1) {

                this.stack = new char[capacity];
                this.nElems = 0;
                this.loadF = loadF;
                this.shrinkF = quarter;
                initialCapacity = capacity;
                this.capacity = capacity;

            } else {
                throw new IllegalArgumentException();
            }
        }

        /**
         * Constructor that initializes a stack with the given capacity,
         * load factor.
         * @param capacity an integer indicating initial capacity of stack
         */
        public CharStack(int capacity) {
            if (capacity >= cap) {

                this.stack = new char[capacity];
                this.nElems = 0;
                this.loadF = threequarters;
                this.shrinkF = quarter;
                initialCapacity = capacity;
                this.capacity = capacity;

            } else {
                throw new IllegalArgumentException();
            }
        }

        /**
         * Checks if the stack is empty. Returns true if it is empty, false otherwise.
         * @return a boolean indicating whether stack is empty
         */
        public boolean isEmpty(){
            if (nElems == 0) {
                return true;
            }else{
                return false;
            }
        }
        /**
         * Clears all elements in the stack.
         */
        public void clear(){
            nElems = 0;
        }

        /**
         * Returns the number of elements currently stored in the stack.
         * @return stack size
         */
        public int size(){
            return nElems;
        }

        /**
         * Returns the maximum number of elements the stack currently can store.
         * In other words, the length of the backed data array.
         * @return capacity of stack
         */
        public int capacity(){
            return capacity;
        }

        /**
         * Returns the top element of the stack.
         * @return first integer on stack
         */
        public char peek(){
            if (nElems == 0){
                throw new EmptyStackException();
            }
            return stack[nElems-1];
        }

        /**
         * Pushes all numbers in the array elems to the stack.
         */
        public void multiPush(char[] elems){
            if (elems.length == 0){
                throw new IllegalArgumentException();

            }
            int i = 0;
            while (i < elems.length) {
                if ((double) nElems / capacity >= loadF){
                    capacity = capacity * even;
                    char[] mystack = new char[capacity];
                    for(int j = 0; j < nElems; j++){
                        mystack[j] = stack[j];
                    }
                    this.stack = mystack;
                }
                stack[nElems] = elems[i];
                nElems++;
                i++;
            }

        }

        /**
         * Pops the given amount of elements from the stack
         * @return elems top amount element of stack
         */
        public char[] multiPop(int amount){
            if (amount < 0){
                throw new IllegalArgumentException();
            }
            int number = Math.min(amount, nElems);
            char[] elems = new char[number];

            for(int i = 0; i < number; i++){
                elems[i] = stack[nElems-1];
                nElems --;
                if ((double)nElems / capacity <= shrinkF){
                    capacity = capacity / even;
                    if (capacity < initialCapacity){
                        capacity = initialCapacity;
                    }
                }
            }
            return elems;
        }


        /**
         * Returns the top element of the stack.
         */
        public void push(char element) {
            if ((double)nElems / capacity >= loadF){
                capacity = capacity * even;
                char[] mystack = new char[capacity];

                for(int i = 0; i < nElems; i++){
                    mystack[i] = stack[i];
                }
                this.stack = mystack;
            }
            stack[nElems] = element;
            nElems ++;
        }

        /**
         * Returns and removes the top element of the stack.
         * @return top element of stack
         */
        public char pop() {
            char element = ' ';
            if (nElems > 0){
                element = stack[nElems - 1];
            } else {
                throw new EmptyStackException();
            }
            nElems--;
            if ((double) nElems / capacity <= shrinkF){
                capacity = capacity / even;

                if (capacity < initialCapacity){
                    capacity = initialCapacity;
                }
            }
            return element;
        }
    }
}
