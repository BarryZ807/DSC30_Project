import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BSTTest {
    int[] arr1 = {2,8,6,4,9,7,3,10,0,1};
    int[] arr2 = {1,2,3,4,5};
    int[] arr3 = {6,7,8,9,10};


    BST bst = new BST();

    @Test
    public void testEmpty() {
        assertEquals(true,bst.isEmpty());
    }


    /*
          test BST tree size
     */
    @Test
    public void TestSize(){
        assertEquals(0 ,bst.size());
        for (int i = 0; i < 2 ; i++) {
            bst.add(i, String.valueOf(i));
        }
        assertEquals(2,bst.size());

        for (int i = 2; i < arr1.length ; i++) {
            bst.add(i, String.valueOf(i));
        }
        assertEquals(arr1.length,bst.size());
    }


    /*
      test add function with exist key
     */
    @Test
    public void testAddFalse(){
        for (int i = 0; i < 2 ; i++) {
            bst.add(i, String.valueOf(i));
        }
        boolean expect = false;
        boolean res = bst.add(0);
        assertEquals(expect,res);
    }


    /*
      test find function
     */
    @Test
    public void testFind(){
        for (int i = 0; i < 2 ; i++) {
            bst.add(i, String.valueOf(i));
        }
        boolean expect = true;
        boolean res = bst.findKey(0);

        // key 0 in BST Tree
        assertEquals(expect,res);

        // key 4 not in BST nodde
        assertEquals(false,bst.findKey(4));
    }

    @Test
    public  void  TestCount(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.add(arr1[i], String.valueOf(arr1[i]));
        }
        assertEquals(4,bst.leafCount(bst.root));
    }
    // test remove one node
    @Test
    public void testRemoveNode1(){
        for (int i = 0; i < arr2.length ; i++) {
            bst.add(arr2[i], String.valueOf(arr2[i]));
        }
        bst.removeNode(3);
        assertEquals(4,bst.nElems);
    }

    // test remove the root
    @Test
    public void testRemoveNode2(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.add(arr1[i], String.valueOf(arr1[i]));
        }
        bst.removeNode(2);
        assertEquals(1,bst.root.key);
    }


    // test remove a key not exist
    @Test
    public void testRemoveNode3(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.add(arr1[i], String.valueOf(arr1[i]));
        }
        boolean res = bst.removeNode(11);
        assertEquals(false,res);
    }

}
