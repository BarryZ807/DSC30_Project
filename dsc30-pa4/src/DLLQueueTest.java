/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * queue test
 * @author Zehui Zhang
 * @since 2021-02-01
 */
public class DLLQueueTest {
    
    DLLQueue<String> strqueue;
    DLLQueue<Integer> intqueue;
    DLLQueue<Character> charqueue;

    @Before
    public void setup() {
        strqueue = new DLLQueue<String>();
        intqueue = new DLLQueue<Integer>();
        charqueue = new DLLQueue<Character>();
    }

    @Test
    public void testConstructor() {
        new DLLQueue<String>();
        new DLLQueue<Integer>();
        new DLLQueue<Character>();
    }

    @Test 
    public void testEnqueueandSize() {
        strqueue.enqueue("at index 0");
        strqueue.enqueue("at index 1");
        strqueue.enqueue("at index 2");
        intqueue.enqueue(0);
        intqueue.enqueue(1);

        assertEquals(3, strqueue.size());
        assertEquals(2, intqueue.size());
        assertEquals(0, charqueue.size());
    }

    @Test
    public void testEmpty() {
        strqueue.enqueue("at index 0");
        strqueue.enqueue("at index 1");
        strqueue.enqueue("at index 2");
        intqueue.enqueue(0);
        intqueue.enqueue(1);

        assertFalse(strqueue.isEmpty());
        assertFalse(intqueue.isEmpty());
        assertTrue(charqueue.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnqueueThrowsIAE() {
        strqueue.enqueue(null);
    }

    @Test
    public void testPop() {
        strqueue.enqueue("at index 0");
        strqueue.enqueue("at index 1");
        strqueue.enqueue("at index 2");

        assertEquals(null, charqueue.dequeue());
        assertEquals("at index 0", strqueue.dequeue());
        assertEquals(2, strqueue.size());
        assertEquals("at index 1", strqueue.dequeue());
    }

    @Test
    public void testPeek() {
        strqueue.enqueue("at index 0");
        strqueue.enqueue("at index 1");
        strqueue.enqueue("at index 2");

        assertEquals("at index 0", strqueue.peek());

        strqueue.dequeue();
        assertEquals(2, strqueue.size());
        assertEquals("at index 1", strqueue.peek());

        strqueue.dequeue();
        assertEquals(1, strqueue.size());
        assertEquals("at index 2", strqueue.peek());

        strqueue.dequeue();
        assertEquals(0, strqueue.size());
        
        assertEquals(null, strqueue.peek());
    }

}
