import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class dHeapTester {
    
    private dHeap<Integer> d2;
    private dHeap<Integer> d3;
    private dHeap<Integer> d4;
    private dHeap<Integer> dd;


    @Before
    public void setup() {
        d2 = new dHeap<>(2, 6, false);
        d3 = new dHeap<>(3, 6, false);
        d4 = new dHeap<>(4, 6, true);
        dd = new dHeap<>(3, 6, true);

        d2.add(5); d2.add(89); d2.add(52); d2.add(73); d2.add(46);
        d3.add(94); d3.add(3); d3.add(89); d3.add(78); d3.add(82); d3.add(76); d3.add(24);
        dd.add(94); dd.add(3); dd.add(78); dd.add(82); dd.add(76); dd.add(24);
    }

    @Test
    public void testConstructor1() {
        new dHeap<Integer>();
        new dHeap<Integer>();
        new dHeap<Integer>();
    }

    @Test
    public void testConstructor2() {
        new dHeap<Integer>(4);
        new dHeap<Integer>(6);
        new dHeap<Integer>(7);
    }

    @Test
    public void testConstructor3() {
        new dHeap<Integer>(4, 6, false);
        new dHeap<Integer>(5, 20, true);
        new dHeap<Integer>(7, 100, true);
    }

    @Test(expected = NullPointerException.class)
    public void testAddThrowsNPE() {
        d2.add(null);
    }

    @Test
    public void testAdd() {
        d2.add(1); d2.add(48); d3.add(-3); d3.add(25);
        assertEquals(new Integer(1), d2.element()); 
        assertEquals(new Integer(-3), d3.element());
        assertEquals(new Integer(94), dd.element());
    }

    @Test 
    public void testSize() {
        assertEquals(5, d2.size());
        assertEquals(7, d3.size());
        assertEquals(0, d4.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testElementThrowNSE() {
        d4.element();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveThrowNSE() {
        d4.remove();
    }

    @Test
    public void testElement() {
        assertEquals(new Integer(5), d2.element()); 
        assertEquals(new Integer(3), d3.element());
        assertEquals(new Integer(94), dd.element());
    }

    @Test
    public void testRemove() {
        d3.remove();
        assertEquals(new Integer(24), d3.element());
        d3.remove();
        assertEquals(new Integer(76), d3.element());
        d3.remove();    
        assertEquals(new Integer(78), d3.element());
        d3.remove();
        assertEquals(new Integer(82), d3.element());   

        int j = dd.remove();
        assertEquals(94, j);
        assertEquals(new Integer(82), dd.element());
        assertEquals(new Integer(82), dd.remove());
        assertEquals(new Integer(78), dd.element());
    }

    @Test
    public void testClear() {
        d2.clear();
        assertEquals(0, d2.size());
        d3.clear();
        assertEquals(0, d3.size());
        dd.clear();
        assertEquals(0, dd.size());
        
    }

    
}
