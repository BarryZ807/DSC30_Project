/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * A doubly linked list test
 * @author Zehui Zhang
 * @since 2021-01-28
 */
public class DoublyLinkedListTest {
    DoublyLinkedList<String> strlist;
    DoublyLinkedList<Integer> intlist;
    DoublyLinkedList<Character> charlist;
    @Before
    public void setup() {
        strlist = new DoublyLinkedList<String>();
        intlist = new DoublyLinkedList<Integer>();
        charlist = new DoublyLinkedList<Character>();
    }

    @Test
    public void testConstructor() {
        new DoublyLinkedList<String>();
        new DoublyLinkedList<Integer>();
        new DoublyLinkedList<Character>();
    }
    @Test (expected = NullPointerException.class)
    public void testAddThrowNPE(){
        strlist.add(null);
        intlist.add(null);
        charlist.add(null);
        
        strlist.add(0,null);
        intlist.add(0,null);
        charlist.add(0,null);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddThrowIOBE(){
        strlist.add(3,"!");
        intlist.add(-1,1);
        charlist.add(1,'c');
    }

    @Test
    public void testSize() {
        strlist.add("Hello");
        strlist.add("mn");
        assertEquals(2, strlist.size());
        intlist.add(-5);
        assertEquals(1, intlist.size());
        charlist.add('h');
        assertEquals(1, charlist.size());
    }

    @Test
    public void testClear() {
        strlist.add("Hello");
        strlist.add("ij");
        assertEquals(2, strlist.size());
        
        strlist.clear();
        assertEquals(0,strlist.size());

        intlist.add(-5);
        assertEquals(1, intlist.size());

        intlist.clear();
        assertEquals(0,strlist.size());

        charlist.clear();
        assertEquals(0, charlist.size());
        assertEquals(0, charlist.size());
    }

    @Test 
    public void testAddNoIndex() {
        strlist.add("Hello");
        strlist.add("ij");
        assertEquals(2, strlist.size());

        intlist.add(-5);
        assertEquals(1, intlist.size());

        charlist.add('h');
        charlist.add('i');
        charlist.add('!');
        assertEquals(3, charlist.size());
    }

    @Test
    public void testAddIndexAndGet() {
        strlist.add(0,"Hello");
        strlist.add(1,"ij");
        strlist.add(2, "uv");
        strlist.add(1,"at index 1");
        assertEquals("Hello", strlist.get(0));
        assertEquals("at index 1", strlist.get(1));
        assertEquals("ij", strlist.get(2));
        assertEquals("uv", strlist.get(3));
    }


    @Test
    public void testContains() {
        strlist.add("Hello");
        strlist.add("ij");
        strlist.add(2, "uv");
        strlist.add(1,"at index 1");
        intlist.add(-5);
        charlist.add('h');
        charlist.add(1,'i');

        assertTrue(strlist.contains("Hello"));
        assertTrue(strlist.contains("at index 1"));
        assertTrue(strlist.contains("ij"));
        assertTrue(strlist.contains("uv"));
        assertFalse(intlist.contains(1));
        assertTrue(charlist.contains('i'));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsIOBE() {
        strlist.add("Hello");
        strlist.add("ij");
        strlist.add(2, "uv");
        strlist.add(1,"at index 1");

        strlist.get(4);
        strlist.get(-1);
    }

    @Test
    public void testIsEmpty() {
        strlist.add("Hello");
        strlist.add("ij");
        strlist.add(2, "uv");
        strlist.add(1,"at index 1");
        intlist.add(-5);
        assertFalse(strlist.isEmpty());
        assertFalse(intlist.isEmpty());
        assertTrue(charlist.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoeThrowsIOBE() {
        strlist.add("Hello");
        strlist.add("ij");
        strlist.add(2, "uv");
        strlist.remove(3);
        strlist.remove(-1);
        charlist.remove(0);
    }

    @Test
    public void testRemove() {
        strlist.add("Hello");
        strlist.add("ij");
        strlist.add(2, "uv");
        strlist.add(1,"at index 1");
        intlist.add(-5);
        charlist.add('h');
        charlist.add(1,'i');

        intlist.remove(0);
        assertEquals(0, intlist.size());
        assertFalse(intlist.contains(-5));

        assertEquals("uv" ,strlist.remove(3));
        assertEquals(3, strlist.size());
        assertFalse(strlist.contains("uv"));
        assertTrue(strlist.contains("Hello"));
        assertTrue(strlist.contains("at index 1"));
        assertTrue(strlist.contains("ij"));
        
        assertEquals("at index 1", strlist.remove(1));
        assertEquals(2, strlist.size());
        assertTrue(strlist.contains("Hello"));
        assertFalse(strlist.contains("at index 1"));
        assertTrue(strlist.contains("ij"));

        assertEquals("ij", strlist.remove(1));
        assertEquals("Hello", strlist.remove(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetThrowsIOB() {
        strlist.add("Hello");
        strlist.add("ij");
        strlist.set(-1,"s");
        strlist.set(2,"s");
    }

    @Test(expected = NullPointerException.class)
    public void testSetThrowsNPE() {
        strlist.add("Hello");
        strlist.set(0,null);
    }

    @Test
    public void testSet() {
        strlist.add("Hello");
        strlist.add("ij");
        strlist.add(2, "uv");
        strlist.add(1,"at index 1");

        strlist.set(0, "0");
        strlist.set(2, "2");
        strlist.set(3, "3");

        assertEquals("0", strlist.get(0));
        assertEquals("at index 1", strlist.get(1));
        assertEquals("2", strlist.get(2));
        assertEquals("3", strlist.get(3));
    }

    @Test
    public void testToString() {
        strlist.add("Hello");
        strlist.add("ij");
        strlist.add(2, "uv");
        strlist.add(1,"at index 1");
        intlist.add(1);

        assertEquals("[(head) -> Hello -> at index 1 -> ij -> uv -> (tail)]", strlist.toString());
        assertEquals("[(head) -> 1 -> (tail)]",intlist.toString());
        assertEquals("[(head) -> (tail)]", charlist.toString());
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testSpliceThrowsIOB() {
        DoublyLinkedList<Character> c = new DoublyLinkedList<Character>();
        charlist.add('A');
        charlist.add('B');
        charlist.add('C');
        charlist.splice(-1,c);
        charlist.splice(4,c);
    }

    @Test
    public void testSplice() {
        DoublyLinkedList<Character> c = new DoublyLinkedList<Character>();
        c.add('D');
        c.add('E');
        c.add('F');
        charlist.add('A');
        charlist.add('B');
        charlist.add('C');
        charlist.splice(1, c);
        assertEquals(6, charlist.size());
        assertEquals("[(head) -> A -> D -> E -> F -> B -> C -> (tail)]", charlist.toString());
    }

    @Test
    public void testSplice1() {
        DoublyLinkedList<Character> c = new DoublyLinkedList<Character>();
        c.add('D');
        c.add('E');
        c.add('F');
        charlist.add('A');
        charlist.add('B');
        charlist.add('C');
        charlist.splice(0, c);
        assertEquals(6, charlist.size());
        assertEquals("[(head) -> D -> E -> F -> A -> B -> C -> (tail)]", charlist.toString());
    }

    @Test
    public void testSplice2() {
        DoublyLinkedList<Character> c = new DoublyLinkedList<Character>();
        c.add('D');
        c.add('E');
        c.add('F');
        charlist.add('A');
        charlist.add('B');
        charlist.add('C');
        charlist.splice(2, c);
        assertEquals(6, charlist.size());
        assertEquals("[(head) -> A -> B -> D -> E -> F -> C -> (tail)]", charlist.toString());
    }

    @Test
    public void testSplice3() {
        DoublyLinkedList<Character> c = new DoublyLinkedList<Character>();
        c.add('D');
        c.add('E');
        c.add('F');
        charlist.add('A');
        charlist.add('B');
        charlist.add('C');
        charlist.splice(3, c);
        assertEquals(6, charlist.size());
        assertEquals("[(head) -> A -> B -> C -> D -> E -> F -> (tail)]", charlist.toString());
    }

    @Test
    public void testMatch() {
        DoublyLinkedList<Character> c = new DoublyLinkedList<Character>();
        DoublyLinkedList<Character> c1 = new DoublyLinkedList<Character>();
        c.add('A'); 
        c.add('B'); 
        c.add('A');
        assertEquals(3, c.size());
        c1.add('A'); c1.add('C');c1.add('A');c1.add('B');c1.add('A'); c1.add('C');c1.add('A');c1.add('B');c1.add('A');
        int[] arr = {2,6};
        int[] arr1 = {};
        int[] arr2 = {0,2};
        assertArrayEquals(arr, c1.match(c));
        DoublyLinkedList<Character> c2 = new DoublyLinkedList<Character>();
        c2.add('C');
        assertArrayEquals(arr1, c2.match(c));
        DoublyLinkedList<Character> c3 = new DoublyLinkedList<Character>();
        c3.add('A'); c3.add('B'); c3.add('A');c3.add('B');c3.add('A');
        assertArrayEquals(arr2, c3.match(c));



    }
}
