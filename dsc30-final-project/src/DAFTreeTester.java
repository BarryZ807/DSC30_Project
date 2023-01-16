import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

    /* use this tester only if the accessibility of DAFNode is public and
     the accessibility of toString() in DAFNode is public*/

public class DAFTreeTester {
    
    private DAFTree<Integer, String> t;

    @Before
    public void setup() {
        t = new DAFTree<Integer, String>();
    }

    @Test
    public void testConstructor() {
        new DAFTree<Integer, String>();
        new DAFTree<Character, String>();
        new DAFTree<String, String>();
    }

    @Test 
    public void testGetRoot() {
        assertEquals(null, t.getRoot());
    }

    @Test
    public void testGetSize() {
        assertEquals(0, t.size());
    }

    @Test(expected = NullPointerException.class)
    public void testInsertThrowsNPE() {
        t.insert(null, null, 0);
    }

    @Test
    public void testInsert() {
        DAFTree<Integer, String>.DAFNode<Integer, String> A = t.insert(0, "Hello", 2);
        assertEquals("Hello", A.getData());
        DAFTree<Integer, String>.DAFNode<Integer, String> B = t.insert(1, "!", 1);
        assertEquals(new Integer(1), B.getKey());
        DAFTree<Integer, String>.DAFNode<Integer, String> C = t.insert(2, "World", 1);
        assertEquals(1, C.getCount());
        DAFTree<Integer, String>.DAFNode<Integer, String> D = t.insert(2, "World", 2);
        assertEquals(3, D.getCount());
        assertEquals(C, D);
        DAFTree<Integer, String>.DAFNode<Integer, String> E = t.insertDuplicate(2, 2);
        assertEquals(5, E.getCount());
        assertEquals(5, C.getCount());
    }

    @Test
    public void testTree() {
        DAFTree<Integer, String>.DAFNode<Integer, String> A = t.insert(0, "Hello", 2);
        assertEquals("Hello", A.getData());
        assertEquals(A, t.lookup(0));
        DAFTree<Integer, String>.DAFNode<Integer, String> B = t.insert(1, "!", 1);
        assertEquals(new Integer(1), B.getKey());
        assertEquals(B, t.lookup(1));
        DAFTree<Integer, String>.DAFNode<Integer, String> C = t.insert(2, "World", 1);
        assertEquals(1, C.getCount());
        DAFTree<Integer, String>.DAFNode<Integer, String> D = t.insert(2, "World", 2);
        assertEquals(3, D.getCount());
        assertEquals(C, t.lookup(2));
        assertEquals(C, D);

        assertEquals(B, A.getRight());
        assertEquals(C, B.getRight());

        DAFTree<Integer, String>.DAFNode<Integer, String> E = t.insertDuplicate(2, 2);
        assertEquals(5, E.getCount());
        assertEquals(5, C.getCount());

        assertEquals(8, t.size());
        assertEquals(3, t.nUniqueKeys());   
        
        t.updateData(2, "World!");
        assertEquals("World!", C.getData());

        DAFTree<Integer, String>.DAFNode<Integer, String> F = t.remove(2, 2);
        assertEquals(F, C);
        assertEquals(6, t.size());
        assertEquals(3, t.nUniqueKeys());

        DAFTree<Integer, String>.DAFNode<Integer, String> G = t.remove(2, 5);
        assertEquals(G, C);
        assertEquals(3, t.size());
        assertEquals(2, t.nUniqueKeys());

        DAFTree<Integer, String>.DAFNode<Integer, String> H = t.insert(2, "World", 3);
        assertEquals(A, t.findExtreme(false));
        assertEquals(H, t.findExtreme(true));

        assertEquals(A, t.getRoot());
        assertEquals(B, t.getRoot().getRight());
    }

  
    @Test 
    public void testNextandHasNext() {

        DAFTree<Integer, String>.DAFNode<Integer, String> A = t.insert(0, "Hello", 2);
        t.insert(1, "!", 1);
        t.insert(2, "World", 3);
        Iterator<Integer> it = t.iterator();

        assertEquals(A, t.getRoot());
        assertTrue(it.hasNext());

        assertEquals(new Integer(0), it.next());
        assertEquals(new Integer(0), it.next());
        assertEquals(new Integer(1), it.next());
        assertEquals(new Integer(2), it.next());
        assertEquals(new Integer(2), it.next());
        assertEquals(new Integer(2), it.next());
        assertFalse(it.hasNext());
        
        
    }
 
}

