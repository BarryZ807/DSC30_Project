import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Iterator;


public class BSTreeTest {

    int[] arr1 = {2,8,6,4,9,7,3,10,0,1};
    int[] arr2 = {1,2,3,4,5};
    int[] arr3 = {10,13,1,3,4,7,8,6,14};
    int[] arr4 = {};


    BSTree bst = new BSTree();

    /*
      test BSTree tree getSize
 */
    @Test
    public void TestConstruct(){
        BSTree bst1 = new BSTree();
        assertEquals(0,bst1.getSize());
    }

    /*
          test BSTree tree getSize
     */
    @Test
    public void TestSize(){
        assertEquals(0 ,bst.getSize());
        for (int i = 0; i < 2 ; i++) {
            bst.insert(i);
        }
        assertEquals(2,bst.getSize());

        for (int i = 2; i < arr1.length ; i++) {
            bst.insert(i);
        }
        assertEquals(arr1.length,bst.getSize());
    }


    /*
      test add function with exist key
     */
    @Test
    public void testAddFalse(){
        for (int i = 0; i < 2 ; i++) {
            bst.insert(i);
        }
        boolean expect = false;
        boolean res = bst.insert(0);
        assertEquals(expect,res);
    }

    @Test
    public void testAddFalse1(){
        for (int i = 0; i < 4 ; i++) {
            bst.insert(i);
        }
        boolean res = bst.insert(8);
        assertEquals(true,res);
    }

    @Test
    public void testAddFalse2(){
        for (int i = 0; i < 2 ; i++) {
            bst.insert(i);
        }
        boolean res = bst.insert(98);
        assertEquals(true,res);
    }


    /*
      test find function
     */
    @Test
    public void testFind(){
        for (int i = 0; i < 3 ; i++) {
            bst.insert(i);
        }
        boolean expect = true;
        boolean res = bst.contains(0);
        boolean res1 = bst.contains(0);

        // key 0 in BSTree Tree
        assertEquals(expect,res);

        // key 2 in BSTree Tree
        assertEquals(expect,res1);

        // key 6 not in BSTree nodde
        assertEquals(false,bst.contains(6));
    }

    @Test
    public  void  TestHeight(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.insert(arr1[i]);
        }
        assertEquals(4,bst.findHeight());
    }

    @Test
    public  void  TestHeight1(){
        for (int i = 0; i < arr2.length ; i++) {
            bst.insert(arr2[i]);
        }
        assertEquals(4,bst.findHeight());
    }

    @Test
    public  void  TestHeight2(){
        for (int i = 0; i < arr3.length ; i++) {
            bst.insert(arr3[i]);
        }
        assertEquals(5,bst.findHeight());
    }

    // test remove one node
    @Test
    public void testRemoveNode1(){
        for (int i = 0; i < arr2.length ; i++) {
            bst.insert(arr2[i]);
        }
        bst.remove(3);
        assertEquals(4,bst.getSize());
    }

    // test remove the root
    @Test
    public void testRemoveNode2(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.insert(arr1[i]);
        }
        bst.remove(2);
        assertEquals(1,bst.getRoot().getKey());
    }


    // test remove a key not exist
    @Test
    public void testRemoveNode3(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.insert(arr1[i]);
        }
        boolean res = bst.remove(11);
        assertEquals(false,res);
    }


    @Test
    public void testprintPreOrder1(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.insert(arr1[i]);
        }
        String res = bst.printPreOrder();
        String expected = "2  0  1  8  6  4  3  7  9  10";

        assertEquals(expected,res);
    }

    @Test
    public void testprintPreOrder2(){
        for (int i = 0; i < arr3.length ; i++) {
            bst.insert(arr3[i]);
        }
        String res = bst.printPreOrder();
        String expected = "10  1  3  4  7  6  8  13  14";
//
        assertEquals(expected,res);
    }

    @Test
    public void testprintPreOrder3(){
        for (int i = 0; i < arr4.length ; i++) {
            bst.insert(arr4[i]);
        }
        String res = bst.printPreOrder();
        String expected = "";

        assertEquals(expected,res);
    }

    @Test
    public void testprintInOrder1(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.insert(arr1[i]);
        }
        String res = bst.printInOrder();
        String expected = "0  1 2     3 4 6  7 8  9  10";

        assertEquals(expected,res);
    }

    @Test
    public void testprintInOrder2(){
        for (int i = 0; i < arr2.length ; i++) {
            bst.insert(arr2[i]);
        }
        String res = bst.printInOrder();
        String expected = "1  2  3  4  5";

        assertEquals(expected,res);
    }

    @Test
    public void testprintInOrder3(){
        for (int i = 0; i < arr4.length ; i++) {
            bst.insert(arr4[i]);
        }
        String res = bst.printInOrder();
        String expected = "";

        assertEquals(expected,res);
    }


    @Test
    public void testprintPostOrder1(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.insert(arr1[i]);
        }
        String res = bst.printPostOrder();
        String expected = "1 0     3 4  7 6   10 9 8 2";

        assertEquals(expected,res);
    }

    @Test
    public void testprintPostOrder2(){
        for (int i = 0; i < arr3.length ; i++) {
            bst.insert(arr3[i]);
        }
        String res = bst.printPostOrder();
        String expected = "6  8 7 4 3 1   14 13 10";

        assertEquals(expected,res);
    }

    @Test
    public void testprintPostOrder3(){
        for (int i = 0; i < arr4.length ; i++) {
            bst.insert(arr4[i]);
        }
        String res = bst.printPostOrder();
        String expected = "";

        assertEquals(expected,res);
    }

    @Test
    public  void testfindHeight1(){
        for (int i = 0; i < arr3.length ; i++) {
            bst.insert(arr3[i]);
        }
        int res = bst.findHeight();
        int expected = 5;

        assertEquals(expected,res);
    }
    @Test
    public  void testfindHeight2(){
        for (int i = 0; i < arr2.length ; i++) {
            bst.insert(arr2[i]);
        }
        int res = bst.findHeight();
        int expected = 4;

        assertEquals(expected,res);
    }

    @Test
    public  void testfindHeight3(){
        for (int i = 0; i < arr3.length ; i++) {
            bst.insert(arr3[i]);
        }
        int res = bst.findHeight();
        int expected = 5;

        assertEquals(expected,res);
    }

    @Test
    public void testBSTreeIterator(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.insert(arr1[i]);
        }
        Iterator<Integer> iter = bst.iterator();
        StringBuilder res = new StringBuilder(arr1.length *3);
        while (iter.hasNext()){
            res.append(iter.next()).append(" ");
        }
        StringBuilder expected = new StringBuilder(arr1.length*3);
        expected.append("0 1 2 3 4 6 7 8 9 10 ");
        assertEquals(expected.toString(),res.toString());
    }

    @Test
    public void testBSTreeIterator1(){
        for (int i = 0; i < arr2.length ; i++) {
            bst.insert(arr2[i]);
        }
        Iterator<Integer> iter = bst.iterator();
        StringBuilder res = new StringBuilder(arr2.length *3);
        while (iter.hasNext()){
            res.append(iter.next()).append(" ");
        }
        StringBuilder expected = new StringBuilder(arr2.length*3);
        expected.append("1 2 3 4 5 ");
        assertEquals(expected.toString(),res.toString());
    }

    @Test
    public void testBSTreeIterator2(){
        for (int i = 0; i < arr3.length ; i++) {
            bst.insert(arr3[i]);
        }
        Iterator<Integer> iter = bst.iterator();
        StringBuilder res = new StringBuilder(arr3.length *3);
        while (iter.hasNext()){
            res.append(iter.next()).append(" ");
        }
        StringBuilder expected = new StringBuilder(arr3.length*3);
        expected.append("1 3 4 6 7 8 10 13 14 ");
        assertEquals(expected.toString(),res.toString());
    }

    @Test
    public void testBSTreeHasNext(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.insert(arr1[i]);
        }
        Iterator<Integer> iter = bst.iterator();
        StringBuilder res = new StringBuilder(arr1.length *3);
        while (iter.hasNext()){
            res.append(iter.next()).append(" ");
        }
        StringBuilder expected = new StringBuilder(arr1.length*3);
        expected.append("0 1 2 3 4 6 7 8 9 10 ");
        assertEquals(expected.toString(),res.toString());
    }

    @Test
    public void testBSTreeHasNext1(){
        for (int i = 0; i < arr2.length ; i++) {
            bst.insert(arr2[i]);
        }
        Iterator<Integer> iter = bst.iterator();
        StringBuilder res = new StringBuilder(arr2.length *3);
        while (iter.hasNext()){
            res.append(iter.next()).append(" ");
        }
        StringBuilder expected = new StringBuilder(arr2.length*3);
        expected.append("1 2 3 4 5 ");
        assertEquals(expected.toString(),res.toString());
    }

    @Test
    public void testBSTreeHasNext2(){
        for (int i = 0; i < arr3.length ; i++) {
            bst.insert(arr3[i]);
        }
        Iterator<Integer> iter = bst.iterator();
        StringBuilder res = new StringBuilder(arr3.length *3);
        while (iter.hasNext()){
            res.append(iter.next()).append(" ");
        }
        StringBuilder expected = new StringBuilder(arr3.length*3);
        expected.append("1 3 4 6 7 8 10 13 14 ");
        assertEquals(expected.toString(),res.toString());
    }

    @Test
    public void testBSTreeNext(){
        for (int i = 0; i < arr3.length ; i++) {
            bst.insert(arr3[i]);
        }
        Iterator<Integer> iter = bst.iterator();
        int res = iter.next();
        assertEquals(1,res);
    }
    @Test
    public void testBSTreeNext1(){
        for (int i = 0; i < arr2.length ; i++) {
            bst.insert(arr2[i]);
        }
        Iterator<Integer> iter = bst.iterator();
        int res = iter.next();
        assertEquals(1,res);
    }
    @Test
    public void testBSTreeNext2(){
        for (int i = 0; i < arr1.length ; i++) {
            bst.insert(arr1[i]);
        }
        Iterator<Integer> iter = bst.iterator();
        int res = iter.next();
        assertEquals(0,res);
    }

}
