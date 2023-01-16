/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */


public class BST{

    public int nElems;
    public BSTNode root;

    protected class BSTNode {
        int key;
        String  data;
        BSTNode left;
        BSTNode right;

        /**
         * Constructor that initializes the instance variables
         * @param left BSTNode
         * @param right BSTNode
         * @param key integer
         * @param data String that contains data
         */
        public BSTNode(BSTNode left, BSTNode right, int key, String data) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
        }


        /**
         * Constructor that initializes the instance variables with no data
         * @param left BSTNode
         * @param right BSTNode
         * @param key integer
         */
        public BSTNode(BSTNode left, BSTNode right, int key) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.data = new String();
        }


        /**
         *  Constructor that initializes the instance variables with no child
         * @param key integer
         * @param data String of data
         */
        public BSTNode(int key, String data) {
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
        }


        /**
         * Getter for the data
         * @return the data
         */
        public String getData() {
            return  this.data;
        }


        /**
         * Getter for the node’s key.
         * @return the key
         */
        public int getKey() {
            return  this.key;
        }


        /**
         * Setter for the data in the node.
         * @param data string of data
         */
        public void setData(String data) {
            this.data = data;
        }


        /**
         * Setter for a child node for the current node.
         * @param child the child need to set
         * @param isLeft a boolean
         */
        public void setChild(BSTNode child, boolean isLeft) {
            if (isLeft){
                this.left = child;
            }else {
                this.right = child;
            }

        }


        /**
         * Setter for a child node for the current node.
         * @param child the child need to set
         */
        public void setChild(BSTNode child) {
            this.left = child;
        }


        /**
         * Getter for a child node for the current node.
         * @param isLeft boolean
         * @return the child
         */
        public BSTNode getChild(boolean isLeft) {
            if (isLeft){
                return this.left;
            }else{
                return this.right;
            }

        }


        /**
         * Getter for a child node for the current node.
         * @return the child
         */
        public BSTNode getChild() {
            return this.left;
        }


        /**
         * Adds data to the node, overwriting data that already exists
         * @param data data added
         */
        public void addData(String data) {
            this.data +=data;
        }


        /**
         * Removes data from the node.
         * @param data data removed
         * @return true if succeed, vice verse
         */
        public boolean removeData(String data) {
            if (this.data == data){
                this.data = null;
                return  true;
            }else {
                return  false;
            }

        }
    }


    /**
     * A 0-arg constructor that initializes root to null and nelems to 0
     */
    public BST() {
        this.root = null;
        this.nElems = 0;
    }


    /**
     * Returns the root of BST.
     * @return the root, or null if empty
     */
    public BSTNode getRoot() {
        return  this.root;
    }


    /**
     * Returns number of elements in the tree
     * @return the size
     */
    public int size() {
        return this.nElems;
    }


    /**
     * Inserts a key into the BST, along with data associated with it.
     * @param key int need to insert
     * @param data string of data
     * @return true if succeed, vice versa
     */
    public boolean add(int key, String data) {
       if(findKey(key)){
           return false;
       }
       if (root == null){
           root = new BSTNode(key,data);
           this.nElems++;
           return true;
       }
        return insertHelpFunc(this.root,key,data);

    }


    /**
     * Inserts a key into the BST, along with data associated with it.
     * @param key int need to insert
     * @return true if succeed, vice versa
     */
    public boolean add(int key) {
        if(findKey(key)){
            return false;
        }
        if (root == null){
            root = new BSTNode(key,null);
            this.nElems++;
            return true;
        }
        return insertHelpFunc(root,key,null);

    }


    /**
     * Helper method for insert
     */
    private boolean insertHelpFunc(BSTNode root, int key,String data){

         if (key>(root.getKey()) ) {
            if (root.getChild(false) == null) {
                root.setChild(new BSTNode(null, null, key,data),false);
                this.nElems++;
            } else {
                insertHelpFunc(root.right, key,data);
            }
        } else if (key<(root.getKey()) ) {
            if (root.getChild() == null) {
                root.setChild(new BSTNode(null, null, key,data));
                this.nElems++;
            } else {
                insertHelpFunc(root.left, key,data);
            }
        }
        return true;
    }


    /**
     * check if empty
     * @return true if it is, vice versa
     */
    public boolean isEmpty() {
        return  this.nElems == 0;
    }


    /**
     * Checks if the tree contains a node under the specified key.
     * @param key need to find
     * @return true if it does, vice versa
     */
    public boolean findKey(int key) {
        return findHelperFunc(this.root,key);
    }


    /**
     * Helper method for find
     */
    private boolean findHelperFunc (BSTNode root, int key){

        if (root == null) {
            return false;
        } else if (root.key ==key) {
            return true;
        } else if (key<(root.key) ) {
            return findHelperFunc(root.left, key);
        } else if (key>(root.key) ){
            return findHelperFunc(root.right, key);
        }
        return true;
    }


    /**
     * Returns the data of the node under the specified key.
     * @param key the place of data need to get
     * @return the data
     */
    public String getData(int key) {
        if (key == 0){
            throw new NullPointerException();
        }
        if (findKey(key) == false){
            throw new IllegalArgumentException();
        }

        BSTNode nodeCompare = this.root;
        while (nodeCompare.key!=(key) ) {
            if (key<(nodeCompare.key) ) {
                nodeCompare = nodeCompare.left;
            } else if (key>(nodeCompare.key) ) {
                nodeCompare = nodeCompare.right;
            }
        }

        return nodeCompare.getData();
    }


    /**
     * Adds new data to the specified key’s node, overwriting existing data.
     * @param key int
     * @param data string of data
     * @return true if the addition was successful, or false if the node doesn’t exist.
     */
    public boolean addData(int key, String data) {
        if (!findKey(key)){
            return false;
        }else{
            BSTNode nodeCompare = this.root;
            while (nodeCompare.key!=(key) ) {
                if (key<(nodeCompare.key) ) {
                    nodeCompare = nodeCompare.left;
                } else if (key>(nodeCompare.key) ) {
                    nodeCompare = nodeCompare.right;
                }
            }
            nodeCompare.addData(data);
        }
        return true;
    }


    /**
     * Removes the specified data from the node.
     * @param key int
     * @param data string of data
     * @return  Returns true if the data removal was a success, false if there is no data to remove
     */
    public boolean removeData(int key, String data) {
        if (!findKey(key)){
            return false;
        }else{
            BSTNode nodeCompare = this.root;
            while (nodeCompare.key!=(key) ) {
                if (key<(nodeCompare.key) ) {
                    nodeCompare = nodeCompare.left;
                } else if (key>(nodeCompare.key) ) {
                    nodeCompare = nodeCompare.right;
                }
            }
            nodeCompare.removeData(data);
        }
        return true;
    }


    /**
     * Removes the node under a given key.
     * @param key give key
     * @return true if the removal was a success, false if the key wasn’t found.
     */
    public boolean removeNode(int key) {
        if (!findKey(key)) {
            return false;
        }
        root = getRemovalReplacer(root,key);
        if (root ==null){
            return  false;
        }else {
            return  true;
        }
    }


    /**
     * Helper function of removeNode
     * @param curNode BSTNode
     * @param key give key
     * @return current Node
     */
    public BSTNode getRemovalReplacer(BSTNode curNode,int key){
        if (curNode == null) {
            return null;
        }

        if (curNode.key == key) {

            if (curNode.left == null && curNode.right == null) {

                return null;

            } else if (curNode.left == null) {
                nElems--;
                return curNode.right;

            } else if (curNode.right == null) {
                nElems--;
                return curNode.left;

            } else {
                int lmax = max(curNode.left);
                curNode.key = lmax;
                curNode.left = getRemovalReplacer(curNode.left, lmax);
                nElems--;
                return curNode;
            }

        }

        if (key > curNode.key) {
            curNode.right = getRemovalReplacer(curNode.right, key);
        } else {
            curNode.left = getRemovalReplacer(curNode.left, key);
        }

        return curNode;
    }


    /**
     * Helper function to find the max
     * @param curNode BSTNode
     * @return max
     */
    private int max(BSTNode curNode) {

        if (curNode.right == null) {
            return curNode.key;
        }

        return max(curNode.right);

    }


    /**
     * A recursive method that returns the amount of leaf nodes
     * @param root BSTNode
     * @return the amount of leaf nodes
     */
    public int leafCount(BSTNode root) {
        return leafCountHelper(this.getRoot());
    }


    /**
     * Helper for the leafCount method
     *
     * @param root Root node
     * @return The number of leaf nodes in the tree
     */
    private int leafCountHelper(BSTNode root) {

        if (root == null) {
            return 0;
        } else if(root.right == null || root.left == null) {
            return 1;
        }

        int left = leafCountHelper(root.left);
        int right = leafCountHelper(root.right);
        return left + right;
    }
}
