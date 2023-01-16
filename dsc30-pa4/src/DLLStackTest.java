/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * stack test
 * @author Zehui Zhang
 * @since 2021-02-01
 */
public class DLLStackTest {
    
    DLLStack<String> strstack;
    DLLStack<Integer> intstack;
    DLLStack<Character> charstack;

    @Before
    public void setup() {
        strstack = new DLLStack<String>();
        intstack = new DLLStack<Integer>();
        charstack = new DLLStack<Character>();
    }

    @Test
    public void testConstructor() {
        new DLLStack<String>();
        new DLLStack<Integer>();
        new DLLStack<Character>();
    }

    @Test 
    public void testPushandSize() {
        strstack.push("at index 0");
        strstack.push("at index 1");
        strstack.push("at index 2");
        intstack.push(0);
        intstack.push(1);

        assertEquals(3, strstack.size());
        assertEquals(2, intstack.size());
        assertEquals(0, charstack.size());
    }

    @Test
    public void testEmpty() {
        strstack.push("at index 0");
        strstack.push("at index 1");
        strstack.push("at index 2");
        intstack.push(0);
        intstack.push(1);

        assertFalse(strstack.isEmpty());
        assertFalse(intstack.isEmpty());
        assertTrue(charstack.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPushThrowsIAE() {
        strstack.push(null);
    }

    @Test
    public void testPop() {
        strstack.push("at index 0");
        strstack.push("at index 1");
        strstack.push("at index 2");

        assertEquals(null, charstack.pop());
        assertEquals("at index 2", strstack.pop());
        assertEquals(2, strstack.size());
        assertEquals("at index 1", strstack.pop());
    }

    @Test
    public void testPeek() {
        strstack.push("at index 0");
        strstack.push("at index 1");
        strstack.push("at index 2");

        assertEquals("at index 2", strstack.peek());

        strstack.pop();
        assertEquals(2, strstack.size());
        assertEquals("at index 1", strstack.peek());

        strstack.pop();
        assertEquals(1, strstack.size());
        assertEquals("at index 0", strstack.peek());

        strstack.pop();
        assertEquals(0, strstack.size());
        
        assertEquals(null, strstack.peek());
    }
}
