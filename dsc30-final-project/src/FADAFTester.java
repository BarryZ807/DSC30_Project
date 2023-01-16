import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

    /* use this tester only when the accessibility of instance variable of FADAF: table, tree
    are public */

public class FADAFTester {

    FADAF<Integer, String> f;
    FADAF<Integer, String> g;

    @Before
    public void setup() {
        f = new FADAF<Integer, String>(10);
        g = new FADAF<Integer, String>(10);
        g.insert(0, "Hello", 2);
        g.insert(1, "!", 1);
        g.insert(2, "World", 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsException() {
        new FADAF<Integer, String>(9);
    }

    @Test
    public void test(){
        assertTrue(f.insert(0, "hello", 2));
        assertFalse(f.insert(0, "!", 2));
        assertEquals(2, f.size());

        assertTrue(f.insert(1, "World", 5));
        assertFalse(f.insert(1, "!", 7));
        assertEquals(7, f.size());

        assertTrue(f.insert(2, "!", 7));
        assertEquals(2, f.lookup(0));
        assertEquals(5, f.lookup(1));
        assertEquals(7, f.lookup(2));
        assertEquals(0, f.lookup(3));

        assertEquals(5, f.lookup(1));
        assertTrue(f.remove(1,1));
        assertEquals(4, f.lookup(1));

        assertTrue(f.remove(0, 1));
        assertEquals(1, f.lookup(0));
        assertTrue(f.remove(0, 1));
        assertEquals(0, f.lookup(0));

        assertTrue(f.removeAll(1));
        assertFalse(f.removeAll(0));

        f.insert(0, "Hello", 2);
        f.insert(3, "World", 3);
        assertTrue(f.update(3, "World!"));
        assertFalse(f.update(4, "w"));
        assertEquals("World!", f.table.lookup(3));

        LinkedList<Integer> l1 = new LinkedList<Integer>();
        l1.add(0);l1.add(0);l1.add(1);l1.add(2);l1.add(2);
        LinkedList<Integer> l2 = new LinkedList<Integer>();
        l2.add(0);l2.add(1);l2.add(2);
        LinkedList<Integer> l3 = new LinkedList<Integer>();
        l3.add(1);
        assertEquals(l1, g.getAllKeys(true));
        assertEquals(l2, g.getAllKeys(false));
        assertEquals(l3, g.getUniqueKeysInRange(0, 2));

        assertEquals(new Integer(0), f.getMinKey());
        assertEquals(new Integer(3), f.getMaxKey());

    }



    
}
