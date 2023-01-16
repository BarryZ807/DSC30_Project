/*
 * Name: Zehui Zhang
 * PID: TODO
 */

import java.io.*;
import java.util.Stack;
import java.util.PriorityQueue;

/**
 * The Huffman Coding Tree
 */
public class HCTree {
    // alphabet size of extended ASCII
    private static final int NUM_CHARS = 256;
    // number of bits in a bytef
    private static final int BYTE_BITS = 8;

    // the root of HCTree
    private HCNode root;
    // the leaves of HCTree that contain all the symbols
    public HCNode[] leaves = new HCNode[NUM_CHARS];

    /**
     * The Huffman Coding Node
     */
    public class HCNode implements Comparable<HCNode> {

        byte symbol; // the symbol contained in this HCNode
        int freq; // the frequency of this symbol
        HCNode c0, c1, parent; // c0 is the '0' child, c1 is the '1' child

        /**
         * Initialize a HCNode with given parameters
         *
         * @param symbol the symbol contained in this HCNode
         * @param freq   the frequency of this symbol
         */
        HCNode(byte symbol, int freq) {
            this.symbol = symbol;
            this.freq = freq;
        }

        /**
         * Getter for symbol
         *
         * @return the symbol contained in this HCNode
         */
        byte getSymbol() {
            return this.symbol;
        }

        /**
         * Setter for symbol
         *
         * @param symbol the given symbol
         */
        void setSymbol(byte symbol) {
            this.symbol = symbol;
        }

        /**
         * Getter for freq
         *
         * @return the frequency of this symbol
         */
        int getFreq() {
            return this.freq;
        }

        /**
         * Setter for freq
         *
         * @param freq the given frequency
         */
        void setFreq(int freq) {
            this.freq = freq;
        }

        /**
         * Getter for '0' child of this HCNode
         *
         * @return '0' child of this HCNode
         */
        HCNode getC0() {
            return c0;
        }

        /**
         * Setter for '0' child of this HCNode
         *
         * @param c0 the given '0' child HCNode
         */
        void setC0(HCNode c0) {
            this.c0 = c0;
        }

        /**
         * Getter for '1' child of this HCNode
         *
         * @return '1' child of this HCNode
         */
        HCNode getC1() {
            return c1;
        }

        /**
         * Setter for '1' child of this HCNode
         *
         * @param c1 the given '1' child HCNode
         */
        void setC1(HCNode c1) {
            this.c1 = c1;
        }

        /**
         * Getter for parent of this HCNode
         *
         * @return parent of this HCNode
         */
        HCNode getParent() {
            return parent;
        }

        /**
         * Setter for parent of this HCNode
         *
         * @param parent the given parent HCNode
         */
        void setParent(HCNode parent) {
            this.parent = parent;
        }

        /**
         * Check if the HCNode is leaf (has no children)
         *
         * @return if it's leaf, return true. Otherwise, return false.
         */
        boolean isLeaf() {
            if (this.c0 == null && this.c1 == null) {
                return true;
            }
            return false;
        }

        /**
         * String representation
         *
         * @return string representation
         */
        public String toString() {
            return "Symbol: " + this.symbol + "; Freq: " + this.freq;
        }

        /**
         * Compare two nodes
         *
         * @param o node to compare
         * @return int positive if this node is greater
         */
        public int compareTo(HCNode o) {
            if (this.getFreq() > o.getFreq()) {
                return 1;
            } else if (this.getFreq() < o.getFreq()) {
                return -1;
            } else if (this.getFreq() == o.getFreq() && this.symbol < o.symbol) {
                return -1;
            } else if (this.getFreq() == o.getFreq() && this.symbol > o.symbol) {
                return 1;
            }
            return 0;
        }
    }

    /**
     * Returns the root node
     *
     * @return root node
     */
    public HCNode getRoot() {
        return root;
    }

    /**
     * Sets the root node
     *
     * @param root node to set
     */
    public void setRoot(HCNode root) {
        this.root = root;
    }

    /**
     * build the HCTree
     *
     * @param freq
     */
    public void buildTree(int[] freq) {
        // create leaf nodes
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                byte symbol = (byte) i;
                HCNode n = new HCNode(symbol, freq[i]);
                this.leaves[i] = n;
            }
        }

        // add leaves to priorityQueue
        PriorityQueue<HCNode> myqueue = new PriorityQueue<>();
        for (int i = 0; i < freq.length; i++) {
            if (this.leaves[i] != null) {
                myqueue.add(this.leaves[i]);
            }
        }

        // build the HCTree
        while(myqueue.size() > 1) {
            HCNode e1 = myqueue.poll();
            HCNode e2 = myqueue.poll();

            byte b1 = e1.getSymbol();
            byte b2 = e2.getSymbol();
            int a1 = b1 & 0xff;
            int a2 = b2 & 0xff;

            HCNode p = new HCNode(e1.getSymbol(), e1.getFreq() + e2.getFreq());
            p.setC0(e1);
            p.setC1(e2);
            e1.setParent(p);
            e2.setParent(p);

            if (e1.isLeaf()){
                this.leaves[a1] = e1;
            }
            if (e2.isLeaf()) {
                this.leaves[a2] = e2;
            }

            this.root = p;
            myqueue.add(p);
        }
    }

    /**
     * For a given symbol, use the HCTree built before to
     * find its encoding bits and write 
     * those bits to the given BitOutputStream.
     *
     * @param symbol
     * @param out
     * @throws IOException
     */
    public void encode(byte symbol, BitOutputStream out) throws IOException {
        int ascii = symbol & 0xff;
        HCNode curr = this.leaves[ascii];

        while (curr.getParent()!= null) {
            curr = this.leaves[ascii];
            while (curr.getParent()!= null) {
                if (curr.parent.getC0().compareTo(curr) == 0) {
                    out.writeBit(0);
                } else if (curr.parent.getC1().compareTo(curr) == 0){
                    out.writeBit(1);
                }
                curr = curr.parent;
            }
        }
    }

    /**
     * Decodes the bits from BitInputStream and 
     * returns a byte that represents the symbol 
     * that is encoded by a sequence of bits from BitInputStream.
     *
     * @param in
     * @return
     * @throws IOException
     */
    public byte decode(BitInputStream in) throws IOException {
        HCNode curr = this.root;
        int bit = in.readBit();
        while (true) {
			int temp = bit;
			HCNode nextNode = null;
			if      (temp == 0) nextNode = curr.c0;
			else if (temp == 1) nextNode = curr.c1;

			if (nextNode.isLeaf()){
				return nextNode.getSymbol();
            }
			else 
				curr = nextNode;
        }
    }

    /**
     * “print out” the structure of the HCTree in bits.
     *
     * @param node
     * @param out
     * @throws IOException
     */
    public void encodeHCTree(HCNode node, BitOutputStream out) throws IOException {
        // if the node is leaf node 
        if (node.isLeaf()) {
            out.writeBit(1);
            out.writeByte(node.getSymbol());
        } else {
            out.writeBit(0);
            encodeHCTree(node.getC0(), out);
            encodeHCTree(node.getC1(), out);
        }
    }

    /**
     * building the original HCTree from the header that we 
     * “printed” in bits when encoding the HCTree.
     *
     * @param in
     * @return
     * @throws IOException
     */
    public HCNode decodeHCTree(BitInputStream in) throws IOException {
        //not a leaf node
        if (in.readBit() == 0) {
            byte b = (byte) 1;
            HCNode p = new HCNode(b, 1);
            HCNode c0 = decodeHCTree(in);
            HCNode c1 = decodeHCTree(in);
            //connect with new parent
            p.setC0(c0);
            p.setC1(c1);
            c0.setParent(p);
            c1.setParent(p);
            int a1 = c0.getSymbol() & 0xff;
            int a2 = c1.getSymbol() & 0xff;
            return p;

        } else {
            // a leaf node
            byte b = in.readByte();
            return new HCNode(b, 1);
        }
        
    }

}