import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.NoSuchElementException;

public class dHeapTest {

    int[] arr1 = {2,8,6,4,9,7,3,10,0,1};
    int[] arr2 = {2,8,6,4,9};
    int[] arr3 = {7,3,10,0,1};




    /*
     * test add element
     */
    @Test
    public void dHeapAdd() {
        dHeap dHeap = new dHeap(6);
        assertEquals( 0,dHeap.size());

        for (int i = 0; i < arr1.length ; i++) {
            dHeap.add(arr1[i]);
        }
        assertEquals( 10,dHeap.size());
    }

    @Test
    public void dHeapAdd1() {
        dHeap dHeap = new dHeap(5);
        for (int i = 0; i < arr2.length ; i++) {
            dHeap.add(arr2[i]);
        }
        assertEquals(5,dHeap.size());

        dHeap.remove();
        assertEquals(4,dHeap.size());

    }

    @Test
    public void dHeapAdd2() {
        dHeap dHeap = new dHeap(8);
        for (int i = 0; i < arr3.length ; i++) {
            dHeap.add(arr3[i]);
        }

        dHeap.remove();
        dHeap.remove();
        dHeap.remove();
        dHeap.remove();
        assertEquals(1 ,dHeap.size());

        dHeap.clear();
        assertEquals(0 ,dHeap.size());

    }

    /*
     * test add null element
     */
    @Test(expected = NullPointerException.class)
    public void testAddThrowIOBE() {
        dHeap dHeap = new dHeap(10);
        String data = null;

        dHeap.add(data);
    }


    /*
     * test remove element
     */
    @Test
    public void dHeapRemove() {
        dHeap dHeap = new dHeap(10);
        for (int i = 0; i < arr1.length ; i++) {
            dHeap.add(arr1[i]);
        }

        int res = (int) dHeap.remove();

        assertEquals(10 ,res);

    }
    /*
     * test remove element
     */
    @Test(expected = NoSuchElementException.class)
    public void dHeapRemoveNull() {
        dHeap dHeap = new dHeap(10);


        int res = (int) dHeap.remove();

        assertEquals(10 ,res);

    }

    /*
     * test get element
     */
    @Test
    public void dHeapElement() {
        dHeap dHeap = new dHeap(10);
        for (int i = 0; i < arr1.length ; i++) {
            dHeap.add(arr1[i]);
        }

        int res = (int) dHeap.element();

        assertEquals(10 ,res);

    }

    @Test
    public void dHeapElement2() {
        dHeap dHeap = new dHeap(10);
        for (int i = 0; i < arr2.length ; i++) {
            dHeap.add(arr2[i]);
        }

        int res = (int) dHeap.element();

        assertEquals(9 ,res);

    }

    @Test
    public void dHeapElement3() {
        dHeap dHeap = new dHeap(10);
        for (int i = 0; i < arr3.length ; i++) {
            dHeap.add(arr3[i]);
        }

        int res = (int) dHeap.element();

        assertEquals(10 ,res);

    }

    /*
    test for clear
     */

    @Test
    public void dHeapclear(){
        dHeap dHeap = new dHeap(4);
        for (int i = 0; i < arr2.length ; i++) {
            dHeap.add(arr2[i]);
        }
        assertEquals(5 ,dHeap.size());
        dHeap.clear();
        assertEquals(0 ,dHeap.size());
        dHeap.add(arr1[6]);
        assertEquals(1 ,dHeap.size());

    }



}
