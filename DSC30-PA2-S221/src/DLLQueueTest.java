/*
 * NAME: Zehui Zhang
 */

import org.junit.*;
import static org.junit.Assert.*;


/**
 * queue test
 * @author Zehui Zhang
 * @since 2021-08-14
 */
public class DLLQueueTest {

    DLLQueue intqueue;

    @Before
    public void setup() {
        intqueue = new DLLQueue ();
    }

    @Test
    public void testConstructor() {
        new DLLQueue ();
    }

    @Test
    public void testEnqueueandSize() {
        intqueue.enqueue(0);
        intqueue.enqueue(1);

        assertEquals(2, intqueue.size());
    }

    @Test
    public void testEmpty() {

        intqueue.enqueue(0);
        intqueue.enqueue(1);

        assertFalse(intqueue.isEmpty());
    }



    @Test
    public void testPop() {
        intqueue.enqueue(0);
        intqueue.enqueue(1);
        intqueue.enqueue(2);

        assertEquals(0, intqueue.dequeue());
        assertEquals(2, intqueue.size());
        assertEquals(1, intqueue.dequeue());
    }

    @Test
    public void testPeek() {
        intqueue.enqueue(0);
        intqueue.enqueue(1);
        intqueue.enqueue(2);

        assertEquals(0, intqueue.peek());

        intqueue.dequeue();
        assertEquals(2, intqueue.size());
        assertEquals(1, intqueue.peek());

        intqueue.dequeue();
        assertEquals(1, intqueue.size());
        assertEquals(2, intqueue.peek());

        intqueue.dequeue();
        assertEquals(0, intqueue.size());

    }

}

