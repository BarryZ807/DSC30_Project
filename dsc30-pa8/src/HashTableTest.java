import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class HashTableTest {
    private HashTable h1;
    private HashTable h2;
    private HashTable h3;

    @Before
    public void setup() {
        h1 = new HashTable(5);
        h2 = new HashTable(5);
        h3 = new HashTable(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsIAE() {
        new HashTable(4);
    }
    
    @Test
    public void testConstrutor1() {
        new HashTable();
        new HashTable();
        new HashTable();
    }

    @Test
    public void testConstrutor2() {
        new HashTable(14);
        new HashTable(20);
        new HashTable(12);
    }

    @Test(expected = NullPointerException.class)
    public void testLookupThrowsNPE() {
        h1.lookup(null);
    }

    @Test
    public void testLookup() {
        assertFalse(h1.lookup("Hello"));
        h1.insert("Hello");
        assertTrue(h1.lookup("Hello"));
    }

    @Test(expected = NullPointerException.class)
    public void testInsertThrowsNPE() {
        h1.insert(null);
    }

    @Test
    public void testInsert() {
        h1.insert("Don't");
        h1.insert("look");
        h1.insert("at");
        h1.insert("me");
        assertEquals(4, h1.size());
        assertTrue(h1.lookup("Don't"));
        assertTrue(h1.lookup("look"));
        assertTrue(h1.lookup("at"));
        assertTrue(h1.lookup("me"));
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteThrowsNPE() {
        h1.delete(null);
    }

    @Test
    public void testDelete() {
        assertFalse(h1.delete("Hello"));
        h1.insert("Don't");
        h1.insert("look");
        h1.insert("at");
        h1.insert("me");
        assertTrue(h1.delete("Don't"));
        assertTrue(h1.delete("look"));
        assertEquals(2, h1.size());
        assertTrue(h1.lookup("at"));
        assertTrue(h1.lookup("me"));
        assertFalse(h1.lookup("Don't"));
        assertFalse(h1.lookup("look"));
    }

    @Test
    public void testSizeAndCapacity() {
        h1.insert("Don't");
        h1.insert("look");
        h1.insert("at");
        h1.insert("me");
        assertEquals(4, h1.size());
        assertEquals(10, h1.capacity());
        assertEquals(0, h2.size());
        assertEquals(5, h2.capacity());
        assertEquals(0, h3.size());
        assertEquals(5, h3.capacity());
    }

    @Test
    public void testStats() {
        System.out.println(h1.getStatsLog());
        h1.insert("Don't");
        h1.insert("look");

        h1.insert("at"); // rehash #1
        h1.insert("me"); // 1 collision
        h1.insert("hi"); // 5 collision total
        System.out.println(h1.getStatsLog());

        h1.insert("we"); // # rehash #2   10 collisions in total
        
        h1.insert("us");  // 16 collisions in total
        System.out.println(h1.getStatsLog());

        h1.insert("a");
        h1.insert("What about");
        h1.insert("What about me"); 
        h1.insert("Let's dance here"); 
        h1.insert("I have been waiting"); // rehash #3 16 collisions in total
        System.out.println(h1.getStatsLog());
    }

}
