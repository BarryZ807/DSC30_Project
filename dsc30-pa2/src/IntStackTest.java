import org.junit.*;
import static org.junit.Assert.*;


public class IntStackTest {

    private IntStack s1;
    private IntStack s2;
    private IntStack s3;

    @Before
    public void setUp() {
        s1 = new IntStack(5,0.8,0.2);
        s2 = new IntStack(5, 0.8);
        s3 = new IntStack(5);
    }

    @After
    public void tearDown() {
        s1 = null;
        s2 = null;
        s3 = null;
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

    @Test
    public void test_constructor2(){
        IntStack s4 = new IntStack(5,0.8);
        IntStack s5 = new IntStack(5,0.9);
        IntStack s6 = new IntStack(5,0.8);
        assertEquals(5, s4.capacity());
        assertEquals(5, s5.capacity());
        assertEquals(5, s6.capacity());
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
    public void test_capacity() {
        assertEquals( 5, s1.capacity());
        assertEquals( 5, s2.capacity());
        assertEquals( 5, s3.capacity());
    }
    @Test
    public void test_empty(){
        assertTrue(s1.isEmpty());
        assertTrue(s2.isEmpty());
        assertTrue(s3.isEmpty());
    }

    @Test
    public void test_size(){
        int[] data = {1,2,3};
        s1.multiPush(data);
        assertEquals(3, s1.size());
        s1.pop();
        assertEquals(2, s1.size());
        s1.multiPop(3);
        assertEquals(0, s1.size());
    }
    @Test
    public void test_clear(){
        int[] data = {1,2,3,4,5};
        s1.multiPush(data);
        s2.multiPush(data);
        s3.multiPush(data);
        s1.clear();
        s2.clear();
        s3.clear();
        assertTrue(s1.isEmpty());
        assertTrue(s2.isEmpty());
        assertTrue(s3.isEmpty());


    }

    @Test
    public void test_push(){
        s1.push(1);
        s2.push(5);
        s2.push(6);
        s3.push(9);
        s3.push(-6);
        s3.push(-19999);
        assertEquals(1, s1.size());
        assertEquals(2, s2.size());
        assertEquals(3, s3.size());

    }

    @Test
    public void test_pop(){
        s1.push(1);
        s1.pop();
        s2.push(5);
        s2.push(6);
        s2.pop();
        s3.push(9);
        s3.push(-6);
        s3.push(-19999);
        s3.pop();
        s3.pop();
        assertEquals(0, s1.size());
        assertEquals(1, s2.size());
        assertEquals(1, s3.size());

    }
    @Test
    public void test_peek() {
        s1.push(1);
        assertEquals(1, s1.size());
        assertEquals(1, s1.peek());
        assertEquals(1, s1.pop());
        assertTrue(s1.isEmpty());

        s2.push(1);
        assertEquals(1, s2.size());
        assertEquals(1, s2.peek());
        assertEquals(1, s2.pop());
        assertTrue(s2.isEmpty());

        s3.push(1);
        assertEquals(1, s3.size());
        assertEquals(1, s3.peek());
        assertEquals(1, s3.pop());
        assertTrue(s3.isEmpty());
    }


    @Test
    public void test_multipush(){
        int[] arr = {1,2,3,4,5,6,7,8,9};
        s1.multiPush(arr);
        assertEquals(20, s1.capacity());
        s2.multiPush(arr);
        assertEquals(20, s2.capacity());
        s3.multiPush(arr);
        assertEquals(20, s3.capacity());
    }

    @Test
    public void test_multipop(){
        s1.multiPop(7);
        assertEquals(5, s1.capacity());
        s2.multiPop(7);
        assertEquals(5, s3.capacity());
        s3.multiPop(7);
        assertEquals(5, s3.capacity());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testForConstructorException() {
        new IntStack(5, 0.5, 0.5);
        new IntStack(5, 0.5);
        new IntStack(4);
    }

    @Test(expected= IllegalArgumentException.class)
    public void testForMultipushException() {
        int[] arr = {};
        s1.multiPush(arr);
        s2.multiPush(arr);
        s3.multiPush(arr);
    }

    @Test(expected= IllegalArgumentException.class)
    public void testForMultipopException() {
        s1.multiPop(-1);
        s2.multiPop(-1);
        s3.multiPop(-1);
    }



}

