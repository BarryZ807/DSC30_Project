import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class HashTableTester {
    private HashTable<Integer, String> t;

    @Before
    public void setup() {
        t = new HashTable<Integer, String>(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsIAE() {
        new HashTable<Integer, String>(9);
    }

    @Test
    public void testConstructor() {
        new HashTable<Integer, String>(15);
        new HashTable<Integer, String>(20);
    }

    @Test(expected = NullPointerException.class)
    public void testInsertThrowsNPE1() {
        t.insert(0, null);
    }
    @Test(expected = NullPointerException.class)
    public void testInsertThrowsNPE2() {
        t.insert(null, "hey");
    }
    @Test(expected = NullPointerException.class)
    public void testUpdateThrowsNPE1() {
        t.update(null, "hey");
    }
    @Test(expected = NullPointerException.class)
    public void testUpdateThrowsNPE2() {
        t.update(0, null);
    }
    @Test(expected = NullPointerException.class)
    public void testDeleteThrowsNPE() {
        t.delete(null);
    }
    @Test(expected = NullPointerException.class)
    public void testLookUpThrowsNPE() {
        t.lookup(null);
    }

    @Test
    public void testInsert() {
        assertTrue(t.insert(0, "Hello"));
        assertTrue(t.insert(1, "World"));
        assertFalse(t.insert(0, "who"));
    }

    @Test
    public void testDeleteAndUpdate() {
        assertTrue(t.insert(0, "Hello"));
        assertTrue(t.insert(1, "World"));
        assertTrue(t.delete(0));
        assertEquals(1, t.size());

        assertTrue(t.insert(0, "Hello"));
        assertEquals(2, t.size());
        assertTrue(t.update(0, "Hello!"));
        assertEquals("Hello!", t.lookup(0));
    }

    @Test
    public void testRehash() {
        t.insert(0,"a");
        t.insert(1,"b");
        t.insert(2,"c");
        t.insert(3,"d");
        t.insert(4,"e");
        t.insert(5,"f");
        t.insert(6, "g");
        t.insert(7, "h");
        assertEquals(20, t.capacity());
    }
}
