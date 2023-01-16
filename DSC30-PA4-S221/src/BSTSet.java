/**
 * Set implementation using BST
 */
public class BSTSet<T extends Comparable<? super T>>{
    private BSTree set;

    /**
     * Constructor that initialize an empty set using binary search tree
     */
    public BSTSet() {
        set = new BSTree();
    }

    /**
     * Return the number of elements in the set
     * @return the number of elements in the set
     */
    public int size() {
        return set.getSize();
    }

    /**
     * Return true if the set is empty, false otherwise
     * @return true if the set is empty, false otherwise
     */
    public boolean isEmpty() {
        return set.getSize()==0;
    }

    /**
     * Return true if the element is in the set, false otherwise
     * @param element needed to check
     * @return true if the element is in the set, false otherwise
     */
    public boolean contains(T element) {
        return set.contains(element);
    }

    /**
     * Insert an element into the current set.
     * @param element needed to insert
     * @return true if insertion is successful, false otherwise.
     */
    public boolean insert(T element) {
        return set.insert(element);
    }

    /**
     * Remove the element from the current set.
     * @param element needed to remove
     * @return true if removal is successful, false otherwise
     */
    public boolean remove(T element) {
        return set.remove(element);
    }

    /**
     * Find the union of the current set and other set.
     * @param other
     * @return a new set that contains all the elements needed
     */
    public BSTSet union(BSTSet other){
        BSTSet merger = new BSTSet();

        // Unionise this set and s
        //Have to do both since the sets are different
        recursionUnion(merger, set.getRoot());
        recursionUnion(merger, other.set.getRoot());
        return merger;
    }

    /**
     * Helper function for union
     * @param s BST set
     * @param start Bst node
     */
    private void recursionUnion(BSTSet s, BSTree.BSTNode start) {

        // If root not null
        if (start != null) {

            // Add the elements of the set together, then move left and right and continue
            // adding
            s.insert(start.key);
            recursionUnion(s, start.left);
            recursionUnion(s, start.right);
        }
    }


    /**
     * Find the intersection of the current set and other set.
     * @param other set
     * @return a new set that contains the elements common to both sets.
     */
    public BSTSet intersection(BSTSet other){
        BSTSet result = new BSTSet();
        if(set == null || set== null)
            return result; //if one empty, intersection will be empty
        else {
            result = this; //copy this first
            recursionIntersection(result,other,set.getRoot()); //check and only keep same value

        }
        return result;
    }

    /**
     * Helper function for insersection
     * @param result Bst set
     * @param s Bst set
     * @param t Bst node
     */
    private void recursionIntersection(BSTSet result,BSTSet s, BSTree.BSTNode t) {
        if(t!=null) {
            //remove only when the element does not appear in s
            if(!s.contains(t.key)){
                result.remove(t.key);
            }
            recursionIntersection(result,s,t.left);//remove value at the left
            recursionIntersection(result,s,t.right); //remove value at the right
        }

    }

    /**
     * Find the complementation of the current set and other set.
     * @param other set
     * @return a new set that contains the elements common to both sets.
     */
    public BSTSet complement(BSTSet other){
        BSTSet result = new BSTSet();
        if(set.getRoot() == null || other.set.getRoot()== null)
            return result; //if one empty, intersection will be empty
        else {
            result = this; //copy this first
            recursionComplement(result,other,set.getRoot()); //check and only keep same value

        }
        return result;
    }

    /**
     * Helper function for complement
     * @param result Bst set
     * @param s Bst set
     * @param t Bst node
     */
    private void recursionComplement(BSTSet result,BSTSet s, BSTree.BSTNode t) {
        if(t!=null) {
            //remove only when the element does not appear in s
            if(s.contains(t.key)){
                result.remove(t.key);
            }
            recursionComplement(result,s,t.left);//remove value at the left
            recursionComplement(result,s,t.right); //remove value at the right
        }

    }

    /**
     * Check if the current set and other set are totally equal.
     * @param other set
     * @return a new set that contains the elements that are in the current set, but not in other set.
     */
    public boolean equal(BSTSet other){
        return recursionEqual(set.getRoot(),other.set.getRoot());
    }

    /**
     * Helper function for equal
     * @param p bst node
     * @param q bst node
     * @return result
     */
    public boolean recursionEqual(BSTree.BSTNode p, BSTree.BSTNode q){
        boolean flag = false;

        if(p==null && q==null) {
            return true;
        } else if(p==null || q==null) {
            return false;
        } else if(p.key != q.key) {
            return false;
        }
        return recursionEqual(p.left,q.left) && recursionEqual(p.right,q.right);
    }
}
