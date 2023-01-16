/*
 * Name: Zehui Zhang
 * PID:  A16151490
 */

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Binary search tree tester
 * 
 * @author Zehui Zhang
 * @since  2021-02-14
 */
public class BSTreeTester {
    
    private BSTree<Integer> intTree;
    private BSTree<Character> charTree;
    private BSTree<String> sTree;
    private BSTree<Integer> t;


    @Before
    public void setup() {
        intTree = new BSTree<Integer>();
        charTree = new BSTree<Character>();
        sTree = new BSTree<String>();
        t = new BSTree<Integer>();

        intTree.insert(1);
        intTree.insert(2);
        sTree.insert("Hello");

        t.insert(8); t.insert(3);t.insert(10);t.insert(1);t.insert(6);t.insert(4);
        t.insert(7);t.insert(14);t.insert(13);      
    }

    @Test
    public void testConstructor() {
        BSTree<Integer> i = new BSTree<Integer>();
        BSTree<Character> c = new BSTree<Character>();
        BSTree<String> s = new BSTree<String>();
    }

    @Test 
    public void testGetRoot() {
        assertEquals(null, charTree.getRoot());
        assertEquals(new Integer(1), intTree.getRoot().getKey());
        assertEquals("Hello", sTree.getRoot().getKey());
    }

    @Test
    public void testGetSize() {
        assertEquals(0, charTree.getSize());
        assertEquals(2, intTree.getSize());
        assertEquals(1, sTree.getSize());
    }

    @Test(expected = NullPointerException.class)
    public void testInsertThrowsNPE() {
        intTree.insert(null);
    }

    @Test
    public void testInsert() {
        assertTrue(intTree.insert(3));
        assertFalse(intTree.insert(2));
        assertTrue(intTree.insert(4));
        assertFalse(sTree.insert("Hello"));
        assertTrue(sTree.findKey("Hello"));

        assertTrue(intTree.findKey(1));
        assertTrue(intTree.findKey(2));
        assertTrue(intTree.findKey(3));
        assertTrue(intTree.findKey(4));
    }

    @Test
    public void testFind() {
        assertTrue(intTree.findKey(1));
        assertTrue(intTree.findKey(2));
        assertFalse(charTree.findKey('c'));
        assertFalse(intTree.findKey(0));
        assertTrue(sTree.findKey("Hello"));
    }

    @Test(expected = NullPointerException.class)
    public void testFindThrowsNPE() {
        intTree.findKey(null);
    }

    @Test(expected = NullPointerException.class)
    public void testInsertDataThrowsNPE() {
        intTree.insertData(null, 1);
        intTree.insertData(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertDataThrowsIAE() {
        intTree.insertData(0, 1);
        charTree.insertData('c', '!');
    }

    @Test
    public void testInsertData() {
        intTree.insertData(1, 100);
        intTree.insertData(1, 1000);
        intTree.insertData(1, 10000);
        sTree.insertData("Hello", "World");
        
        LinkedList<Integer> l = new LinkedList<>();
        l.add(100);l.add(1000);l.add(10000);
        LinkedList<String> s = new LinkedList<>();
        s.add("World");
        assertEquals(l, intTree.findDataList(1));
        assertEquals(s, sTree.findDataList("Hello"));
        sTree.insertData("Hello", "!");
        s.add("!");
        assertEquals(s, sTree.findDataList("Hello"));
    }

    @Test
    public void testHeight() {
        assertEquals(-1, charTree.findHeight());
        assertEquals(0, sTree.findHeight());
        assertEquals(1, intTree.findHeight());
        assertEquals(3, t.findHeight());
    }

    @Test
    public void testItConstructor() {
        Iterator<String> sit = sTree.iterator();
        Iterator<Integer> iit = intTree.iterator();
        Iterator<Character> cit = charTree.iterator();
    }

    @Test 
    public void testNextandHasNext() {
        
        Iterator<String> sit = sTree.iterator();
        Iterator<Integer> iit = intTree.iterator();
        Iterator<Character> cit = charTree.iterator();
        Iterator<Integer> it = t.iterator();

        assertFalse(cit.hasNext());
        assertTrue(sit.hasNext());
        assertTrue(iit.hasNext());

        
        assertEquals(new Integer(1), it.next());
        assertEquals(new Integer(3), it.next());
        assertEquals(new Integer(1), iit.next());
        
        
    }

    @Test
    public void testIntersection() {
        Iterator<Integer> it = t.iterator();
        Iterator<Integer> iit = intTree.iterator();
        ArrayList<Integer> r1 = new ArrayList<>();
        r1.add(1);
        assertEquals(r1, intTree.intersection(it, iit));
        

        Iterator<String> sit = sTree.iterator();
        ArrayList<String> r3 = new ArrayList<>();
        r3.add("Hello");
        BSTree<String> s = new BSTree<>();
        s.insert("Hello");
        Iterator<String> ssit = s.iterator();
        assertEquals(r3, s.intersection(sit, ssit));


        Iterator<Character> cit = charTree.iterator();
        ArrayList<Character> r2 = new ArrayList<>();
        BSTree<Character> c = new BSTree<>();
        Iterator<Character> ccit = c.iterator();
        assertEquals(r2, c.intersection(cit, ccit));
    }
    
    @Test
    public void testLevelCount() {
        assertEquals(1, t.levelCount(0));
        assertEquals(2, t.levelCount(1));
        assertEquals(3, t.levelCount(2));
        assertEquals(3, t.levelCount(3));
        assertEquals(-1, t.levelCount(4));
    }

}
