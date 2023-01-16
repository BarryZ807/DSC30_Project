/*
 * NAME: Zehui Zhang
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * A doubly linked list test
 * @author Zehui Zhang
 * @since 2021-08-14
 */
public class DoublyLinkedListTest {
    DoublyLinkedList intlist;

    @Before
    public void setup() {
        intlist = new DoublyLinkedList();
    }

    @Test
    public void testConstructor() {
        new DoublyLinkedList();
    }



    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddThrowIOBE() {
        intlist.add(-1, 1);
    }

    @Test
    public void testSize() {
        intlist.add(-5);
        assertEquals(1, intlist.size());

    }

    @Test
    public void testClear() {


        intlist.add(-5);
        assertEquals(1, intlist.size());

        intlist.clear();
    }

    @Test
    public void testAddNoIndex() {

        intlist.add(-5);
        assertEquals(1, intlist.size());

    }

    @Test
    public void testAddIndexAndGet() {
        intlist.add(0, 0);
        intlist.add(1, 1);
        intlist.add(2, 2);
        intlist.add(1, 3);
        assertEquals(0, intlist.get(0));
        assertEquals(3, intlist.get(1));
        assertEquals(2, intlist.get(2));
        assertEquals(0, intlist.get(3));
    }


    @Test
    public void testContains() {

        intlist.add(-5);

        assertFalse(intlist.contains(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsIOBE() {
        intlist.add(1);
        intlist.add(2);
        intlist.add(2, 3);
        intlist.add(1, 4);

        intlist.get(4);
        intlist.get(-1);
    }

    @Test
    public void testIsEmpty() {

        intlist.add(-5);
        assertFalse(intlist.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoeThrowsIOBE() {
        intlist.add(0);
        intlist.add(1);
        intlist.add(2, 2);
        intlist.remove(3);
        intlist.remove(-1);
        intlist.remove(0);
    }

    @Test
    public void testRemove() {

        intlist.add(-5);


        intlist.remove(0);
        assertEquals(0, intlist.size());
        assertFalse(intlist.contains(-5));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetThrowsIOB() {
        intlist.add(0);
        intlist.add(1);
        intlist.set(-1, 2);
        intlist.set(2, 3);
    }


    @Test
    public void testSet() {
        intlist.add(1);
        intlist.add(2);
        intlist.add(2, 3);
        intlist.add(1, 4);

        intlist.set(0, 0);
        intlist.set(2, 2);
        intlist.set(3, 3);

        assertEquals(0, intlist.get(0));
        assertEquals(4, intlist.get(1));
        assertEquals(2, intlist.get(2));
        assertEquals(3, intlist.get(3));
    }

    @Test
    public void testToString() {
        intlist.add(3);
        intlist.add(4);
        intlist.add(2, 5);
        intlist.add(1, 0);
        intlist.add(1);

        assertEquals("[(head) -> 3 -> 0 -> 5 -> 1 -> 0 -> (tail)]", intlist.toString());
    }


}

