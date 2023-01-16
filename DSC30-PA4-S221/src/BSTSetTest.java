import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class BSTSetTest {

    int[] arr1 = {1,2,3,4,6,7,8,9};
    int[] arr2 = {1,2,3,4,6};
    int[] arr3 = {7,8,9};
    int[] arr4 = {5,4,3,6,7,9,2};
    int[] arr5 = {10,2,3,4,6};


    BSTSet bstSet1 = new BSTSet();
    BSTSet bstSet2 = new BSTSet();
    BSTSet bstSet3 = new BSTSet();


    /*
     * test add element
     */
    @Test
    public void testInsert() {
        for (int i = 0; i < arr1.length ; i++) {
            bstSet1.insert(arr1[i]);
        }
        assertEquals(arr1.length,bstSet1.size());
    }

    @Test
    public void testInsert1() {
        for (int i = 0; i < arr2.length ; i++) {
            bstSet2.insert(arr2[i]);
        }
        assertEquals(arr2.length,bstSet2.size());
    }

    @Test
    public void testInsert2() {
        for (int i = 0; i < arr3.length ; i++) {
            bstSet3.insert(arr3[i]);
        }
        assertEquals(arr3.length,bstSet3.size());
    }

    /*
     * test remove element
     */
    @Test
    public void testRemove() {
        for (int i = 0; i < arr1.length ; i++) {
            bstSet1.insert(arr1[i]);
        }
        bstSet1.remove(arr1[0]);
        assertEquals(arr1.length-1,bstSet1.size());
    }

    @Test
    public void testRemove1() {
        for (int i = 0; i < arr2.length ; i++) {
            bstSet2.insert(arr2[i]);
        }
        boolean res = bstSet2.remove(arr2[2]);
        assertEquals(true,res);
    }

    @Test
    public void testRemove2() {
        for (int i = 0; i < arr3.length ; i++) {
            bstSet3.insert(arr3[i]);
        }
        boolean res = bstSet3.remove(1);
        assertEquals(false,res);
    }

    /*
     * test contain element
     */
    @Test
    public void testContain() {
        for (int i = 0; i < arr1.length ; i++) {
            bstSet1.insert(arr1[i]);
        }
        assertEquals(true,bstSet1.contains(arr1[0]));
    }

    @Test
    public void testContain1() {
        for (int i = 0; i < arr2.length ; i++) {
            bstSet2.insert(arr2[i]);
        }
        assertEquals(false,bstSet2.contains(100));
    }

    @Test
    public void testContain2() {
        for (int i = 0; i < arr3.length ; i++) {
            bstSet3.insert(arr3[i]);
        }
        assertEquals(true,bstSet3.contains(8));
    }

    /*
     * test not contain element
     */
    @Test
    public void testNotContain() {
        for (int i = 0; i < arr1.length ; i++) {
            bstSet1.insert(arr1[i]);
        }
        assertEquals(false,bstSet1.contains(11));
    }



    /*
     * test equal
     */
    @Test
    public void testEqual1() {
        for (int i = 0; i < arr1.length ; i++) {
            bstSet1.insert(arr1[i]);
        }
        for (int i = 0; i < arr2.length ; i++) {
            bstSet2.insert(arr2[i]);
        }

        assertEquals(false,bstSet1.equal(bstSet2));

    }


    /*
     * test equal
     */
    @Test
    public void testEqual2() {
        for (int i = 0; i < arr1.length ; i++) {
            bstSet1.insert(arr1[i]);
        }
        for (int i = 0; i < arr4.length ; i++) {
            bstSet2.insert(arr4[i]);
        }

        assertEquals(false,bstSet1.equal(bstSet2));

    }


    /*
     * test equal
     */
    @Test
    public void testEqual3() {
        for (int i = 0; i < arr4.length ; i++) {
            bstSet1.insert(arr4[i]);
        }
        for (int i = 0; i < arr4.length ; i++) {
            bstSet2.insert(arr4[i]);
        }

        assertEquals(true,bstSet1.equal(bstSet2));

    }

    /*
     * test union
     */
    @Test
    public void testUnion1() {
        for (int i = 0; i < arr1.length ; i++) {
            bstSet1.insert(arr1[i]);
        }
        for (int i = 0; i < arr2.length ; i++) {
            bstSet2.insert(arr2[i]);
        }
        BSTSet res = bstSet1.union(bstSet2);

        // check the size of the result
        assertEquals(bstSet1.size(),res.size());

        // res is the same as bstSet1
        assertEquals(true,bstSet1.equal(res));
    }


    /*
     * test union
     */
    @Test
    public void testUnion2() {
        for (int i = 0; i < arr5.length ; i++) {
            bstSet1.insert(arr5[i]);
        }
        for (int i = 0; i < arr4.length ; i++) {
            bstSet2.insert(arr4[i]);
        }

        int[] arr = {10,2,3,4,6,5,7,9};
        BSTSet expect = new BSTSet();
        for (int i = 0; i < arr.length; i++) {
            expect.insert(arr[i]);
        }


        BSTSet res = bstSet1.union(bstSet2);

        // check the size of the result
        assertEquals(expect.size(),res.size());
        // res is the same as bstSet1
        assertEquals(true,expect.equal(res));
    }


    /*
     * test union
     */
    @Test
    public void testIntersection1() {
        for (int i = 0; i < arr1.length ; i++) {
            bstSet1.insert(arr1[i]);
        }
        for (int i = 0; i < arr2.length ; i++) {
            bstSet2.insert(arr2[i]);
        }
        BSTSet res = bstSet2.intersection(bstSet1);

        // check the size of the result
        assertEquals(bstSet2.size(),res.size());

        // res is the same as bstSet2
        assertEquals(true,bstSet2.equal(res));
    }

    /*
     * test union
     */
    @Test
    public void testIntersection2() {
        for (int i = 0; i < arr5.length ; i++) {
            bstSet1.insert(arr5[i]);
        }
        for (int i = 0; i < arr4.length ; i++) {
            bstSet2.insert(arr4[i]);
        }

        int[] arr = {2,3,4,6};
        BSTSet expect = new BSTSet();
        for (int i = 0; i < arr.length; i++) {
            expect.insert(arr[i]);
        }


        BSTSet res = bstSet1.intersection(bstSet2);

        // check the size of the result
        assertEquals(expect.size(),res.size());
        // res is the same as bstSet1
        assertEquals(true,expect.equal(res));
    }


    /*
     * test complement
     */
    @Test
    public void testComplement1() {
        for (int i = 0; i < arr1.length ; i++) {
            bstSet1.insert(arr1[i]);
        }
        for (int i = 0; i < arr2.length ; i++) {
            bstSet2.insert(arr2[i]);
        }

        for (int i = 0; i < arr3.length ; i++) {
            bstSet3.insert(arr3[i]);
        }
        BSTSet res1 = bstSet1.complement(bstSet2);

        // check the size of the result
        assertEquals(bstSet3.size(),res1.size());

        // res is the same as bstSet2
        assertEquals(true,bstSet3.equal(res1));

    }



    /*
     * test complement
     */
    @Test
    public void testComplement2() {
        for (int i = 0; i < arr1.length ; i++) {
            bstSet1.insert(arr1[i]);
        }
        for (int i = 0; i < arr2.length ; i++) {
            bstSet2.insert(arr2[i]);
        }

        for (int i = 0; i < arr3.length ; i++) {
            bstSet3.insert(arr3[i]);
        }
        BSTSet res = bstSet2.complement(bstSet1);

        BSTSet expected = new BSTSet();

        // check the size of the result
        assertEquals(0,res.size());

        // expect result is a null bst set.

        assertEquals(true,expected.equal(res));

    }






}
