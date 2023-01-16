/*
 * NAME: Zehui Zhang
 */

import org.junit.*;
import static org.junit.Assert.*;
/**
 * stack test
 * @author Zehui Zhang
 * @since 2021-08-14
 */
public class DLLStackTest {

    DLLStack intstack;

    @Before
    public void setup() {
        intstack = new DLLStack ();
    }

    @Test
    public void testConstructor() {
        new DLLStack ();
    }

    @Test
    public void testPushandSize() {

        intstack.push(0);
        intstack.push(1);

        assertEquals(2, intstack.size());
    }

    @Test
    public void testEmpty() {
     ;
        intstack.push(0);
        intstack.push(1);

        assertFalse(intstack.isEmpty());
    }



    @Test
    public void testPop() {
        intstack.push(0);
        intstack.push(1);
        intstack.push(2);

        assertEquals(2, intstack.pop());
        assertEquals(2, intstack.size());
        assertEquals(1, intstack.pop());
    }

    @Test
    public void testPeek() {
        intstack.push(0);
        intstack.push(1);
        intstack.push(2);
        assertEquals(2, intstack.peek());

        intstack.pop();
        assertEquals(2, intstack.size());
        assertEquals(1, intstack.peek());

        intstack.pop();
        assertEquals(1, intstack.size());
        assertEquals(0, intstack.peek());

        intstack.pop();
        assertEquals(0, intstack.size());

    }
}
