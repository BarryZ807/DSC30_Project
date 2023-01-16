import org.junit.*;
import static org.junit.Assert.*;

/**
 * JUnit examples on testing Image Editor.
 *
 * You can use this test file to write your own test cases.
 * If you want to submit it, please change the information
 * below to your name and start date.
 * @author DSC 30
 * @since  1/12/2021
 */
public class ImageEditorTest {

    // Declare test variables on instance level so it can be
    // shared among different test methods. Must do this for
    // variables initialized in @Before methods.
    ImageEditor ie1, ie2, ie3;

    @Before
    public void setup() throws Exception {
        // You can declare local variables if you need.
        int[][] img1 = new int[][] {
                new int[] {0, 0, 0},
                new int[] {0, 0, 0},
                new int[] {0, 0, 0}
        };
        // int[][] img2 = ...;
        // int[][] img3 = ...;

        // Initialize the instance test variable here.
        ie1 = new ImageEditor(img1);
        // ie2 = new ImageEditor(img2);
        // ie3 = new ImageEditor(img3);
    }


    // You can declare private helper methods in JUnit files.
    // A helper method could save you some work.
    private void assert2DArrayEquals(int[][] expecteds, int[][] actuals) {
        // if two arguments are referring to the same int[][], they are equal
        if (expecteds != actuals) {

            // fail() method will fail the test directly, can add messages
            // which will appear with the exception trace on the console

            // check null
            if ((expecteds == null) || (actuals == null)) fail("null argument");

            // check i-dimension
            if (expecteds.length != actuals.length) fail("different i-dimension");

            // iterate through i-dimension to check j-dimension
            for (int i = 0; i < expecteds.length; i++) {
                assertArrayEquals("different row at index " + i, expecteds[i], actuals[i]);
            }
        }
    }


    // Assert exception is thrown
    //
    // Notice that, with this approach, one test method can only
    // assert one throw, so you need multiple test methods if
    // you want to assert multiple throw of exceptions.
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorEmptyImageThrowsIAE() {
        ImageEditor ieEmpty = new ImageEditor(new int[0][0]);
    }


    // Example of testing one method. You can increase the
    // number of operations easily by utilizing loops.
    @Test
    public void testAssign() {
        // use for-loop to do more operations easily
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ie1.assign(i, j, i+j);
            }
        }

        // assert
        int[][] expected = new int[][] {
                new int[] {0, 1, 2},
                new int[] {1, 2, 3},
                new int[] {2, 3, 4}
        };
        assert2DArrayEquals(expected, ie1.getImage());
    }


    // You can also simulate a real-world situation in
    // a test method. You can use multiple methods and
    // assert the output after each stage.
    @Test
    public void testRealWorldSituation() {
        // for loop that performs 3 * 3 * 2 = 18 operations in total
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ie1.assign(i, j, i*j);
                ie1.scale(i, j, 1.5);
            }
        }

        // you should trace the above operations by hand
        // and hardcode the result you get as expected result
        int[][] expected1 = new int[][] {
                new int[] {0, 0, 0},
                new int[] {0, 1, 3},
                new int[] {0, 3, 6}
        };
        assert2DArrayEquals(expected1, ie1.getImage());

        // undo 4 operations. since we did 18 operations,
        // all undo() calls should return true
        for (int i = 0; i < 4; i++) {
            assertTrue(ie1.undo());
        }

        // then check the image. the undone 4 operations were
        // performed on position (2, 1) and (2, 2), so after
        // undoing them, colors on these positions should be
        // reset to 0
        int[][] expected2 = new int[][] {
                new int[] {0, 0, 0},
                new int[] {0, 1, 3},
                new int[] {0, 0, 0}  // notice the change here
        };
        assert2DArrayEquals(expected2, ie1.getImage());

        // redo 2 operations, so that we can restore the value
        // at position (2, 1)
        for (int i = 0; i < 2; i++) {
            assertTrue(ie1.redo());
        }

        int[][] expected3 = new int[][] {
                new int[] {0, 0, 0},
                new int[] {0, 1, 3},
                new int[] {0, 3, 0}  // notice the change here
        };
        assert2DArrayEquals(expected3, ie1.getImage());

        // assign position (2, 2) directly, and then redo 2 operations.
        // since a new operation is performed, the redo stack is cleared,
        // so the redo() calls should return false
        ie1.assign(2, 2, 255);
        for (int i = 0; i < 2; i++) {
            assertFalse(ie1.redo());
        }

        int[][] expected4 = new int[][] {
                new int[] {0, 0,   0},
                new int[] {0, 1,   3},
                new int[] {0, 3, 255}  // notice the change here
        };
        assert2DArrayEquals(expected4, ie1.getImage());

        // if you are confident enough that your operations
        // will not affect other entries, you don't have to
        // assert the entire 2D array all the time, just
        // asserting the modified positions will suffice
        assertEquals(255, ie1.getImage()[2][2]);
    }

}