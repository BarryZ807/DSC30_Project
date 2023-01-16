import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class IntStackTest {

    IntStack stack1 = new IntStack(5, 0.8, 0.2);
    IntStack stack2 = new IntStack(5, 0.8);
    IntStack stack3 = new IntStack(5);

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest1() {
        IntStack newStack = new IntStack(1, 0.9, 0.2);
        IntStack newStack1 = new IntStack(5, 0.4, 0.1);
        IntStack newStack2 = new IntStack(6, 0.7, 0.6);
    }

    @Test
    public void test_constructor1(){
        IntStack s4 = new IntStack(5,0.8,0.2);
        IntStack s5 = new IntStack(5,0.9,0.1);
        IntStack s6 = new IntStack(5,0.8,0.05);
        assertEquals(5, s4.capacity());
        assertEquals(5, s5.capacity());
        assertEquals(5, s6.capacity());
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorTest2() {
        IntStack newStack = new IntStack(1, 0.9);
        IntStack newStack1 = new IntStack(5, 0.4);
        IntStack newStack2 = new IntStack(6, 0.7);
    }

    @Test
    public void test_constructor2(){
        IntStack s4 = new IntStack(5,0.8);
        IntStack s5 = new IntStack(5,0.9);
        IntStack s6 = new IntStack(5,0.8);
        assertEquals(5, s4.capacity());
        assertEquals(5, s5.capacity());
        assertEquals(5, s6.capacity());
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorTest3() {
        IntStack newStack = new IntStack(1);
        IntStack newStack1 = new IntStack(5);
        IntStack newStack2 = new IntStack(6);
    }

    @Test
    public void test_constructor3(){
        IntStack s4 = new IntStack(5);
        IntStack s5 = new IntStack(100);
        IntStack s6 = new IntStack(3000);
        assertEquals(5, s4.capacity());
        assertEquals(100, s5.capacity());
        assertEquals(3000, s6.capacity());
    }

    @Test
    public void isEmptyTest() {
        assertTrue(stack1.isEmpty());
        assertTrue(stack2.isEmpty());
        assertTrue(stack3.isEmpty());
        stack1.push(3);
        stack2.push(2);
        stack3.push(1);
        assertFalse(stack3.isEmpty());
        assertFalse(stack2.isEmpty());
        assertFalse(stack1.isEmpty());
    }

    @Test
    public void clear(){
        stack1.push(3);
        stack1.clear();
        assertEquals(5, stack1.capacity());

        for(int x = 0; x < 5; x++) {
            stack2.push(4);
        }
        stack2.push(1);
        stack2.push(2);
        stack2.push(3);
        stack2.push(4);
        stack2.push(5);

        stack2.clear();
        assertEquals(10, stack2.capacity());


        stack3.push(1);
        stack3.push(2);
        stack3.push(3);
        stack3.push(4);
        stack3.push(5);

        stack3.clear();
        assertEquals(5, stack3.capacity());
    }

    @Test
    public void size(){
        stack1.push(2);
        assertEquals(1, stack1.size());

        for(int x = 0; x < 5; x++) {
            stack2.push(x+1);
        }
        assertEquals(5, stack2.size());

        stack3.push(2);
        stack3.push(1);
        stack3.pop();
        assertEquals(1, stack3.size());
    }

    @Test
    public void capacity() {
        for(int x = 0; x < 4; x++) {
            stack1.push(x+1);
        }
        stack1.pop();
        stack1.pop();
        assertEquals(5, stack1.capacity());

        for(int x = 0; x < 5; x++) {
            stack2.push(x+1);
        }
        assertEquals(5, stack2.capacity());

        for(int x = 0; x < 6; x++) {
            stack3.push(x+1);
        }
        assertEquals(10, stack3.capacity());
    }

    @Test
    public void peek() {
        int[] pushElems = {1, 2, 3, 4, 5, 6};
        int[] pushElems2 = {1, 2, 3, 4, 5};
        stack1.multiPush(pushElems);
        assertEquals(6, stack1.peek());

        stack2.multiPush(pushElems);
        assertEquals(6, stack2.peek());

        stack3.multiPush(pushElems2);
        assertEquals(5, stack3.peek());
    }

    @Test (expected = EmptyStackException.class)
    public void peekTest() {
        stack1.peek();
    }

    @Test
    public void push(){
        stack1.push(1);
        stack1.push(2);
        assertEquals(5, stack1.capacity());

        for(int x = 0; x < 4; x++) {
            stack2.push(5);
        }
        assertEquals(5, stack2.capacity());

        for(int x = 0; x < 4; x++) {
            stack3.push(6);
        }
        assertEquals(5, stack3.capacity());
    }

    @Test
    public void pop(){
        for(int x = 0; x < 4; x++) {
            stack2.push(3);
        }
        stack2.pop();
        stack2.pop();
        assertEquals(5, stack2.capacity());

        for(int x = 0; x < 4; x++) {
            stack3.push(3);
        }
        stack3.pop();
        stack3.pop();
        assertEquals(5, stack3.capacity());

        for(int x = 0; x < 4; x++) {
            stack3.push(x);
        }
        stack3.pop();
        stack3.pop();
        assertEquals(5, stack3.capacity());
    }

    @Test (expected = EmptyStackException.class)
    public void popTest() {
        stack1.pop();
    }

    @Test
    public void multiPush() {
        int[] arr = {7,9,10,13,58};
        stack1.multiPush(arr);
        assertEquals(5, stack1.size());

        stack2.multiPush(arr);
        assertEquals(5, stack2.size());

        int[] arr2 = {7,9,10,13};
        stack3.multiPush(arr);
        assertEquals(5, stack1.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void multiPushTest() {
        int[] arr = null;
        stack3.multiPush(arr);
    }

    @Test
    public void multiPop() {
        int[] arr = {9, 7, 8,89,76};
        stack1.multiPush(arr);
        stack1.multiPop(3);
        assertEquals(2, stack1.size());

        stack2.multiPush(arr);
        stack2.multiPop(2);
        assertEquals(3, stack2.size());

        int[] arr2 = {9, 7, 8,89,7};
        stack3.multiPush(arr);
        stack3.multiPop(1);
        assertEquals(4, stack3.size());

    }

    @Test (expected = IllegalArgumentException.class)
    public void multiPopTest() {
        int[] arr = {3, 8, 5,7};
        stack3.multiPush(arr);
        stack3.multiPop(-1);
    }
}
