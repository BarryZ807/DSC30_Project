/*
    Name: Zehui Zhang
    PID:  A16151490
 */

/**
 * The image editor will maintain the current image, and provides several painting operations
 * along with an undo/redo functionality.
 * @author Zehui Zhang
 * @since  13/01/2021
 */
public class ImageEditor {

    /* static constants, feel free to add more if you need */
    private static final int MAX_PIXEL_VALUE      = 255;
    private static final int STACKS_INIT_CAPACITY = 30;
    int amt = 3;
    int even = 2;

    /* instance variables, feel free to add more if you need */
    private int[][] image;
    private IntStack undo;
    private IntStack redo;

    /**
     * Constructor that initializes the image with the given image,
     * and initializes two stacks for undo and redo with capacity of 30.
     * @param image a 2D array representing the image
     */
    public ImageEditor(int[][] image) {
        if (image.length == 0 || image[0].length == 0 ){
            throw new IllegalArgumentException();
        }
        int rowLen = image[0].length;
        for (int i = 1; i < image.length; i++){
            if (image[i].length != rowLen){
                throw new IllegalArgumentException();
            }
        }

        this.image = image;
        this.undo = new IntStack(STACKS_INIT_CAPACITY);
        this.redo = new IntStack(STACKS_INIT_CAPACITY);
    }

    /**
     * Returns the image as a 2D array.
     * @return image
     */
    public int[][] getImage() {
        return image;
    }

    /**
     * Multiplies the color value of the pixel at the given position by the given scale factor
     * @param i row number；
     * @param j col number；
     * @param scaleFactor；
     */
    public void scale(int i, int j, double scaleFactor) {
        if (i >= image.length || j >= image[0].length || i < 0 || j < 0){
            throw new IndexOutOfBoundsException();
        }
        if (scaleFactor < 0){
            throw new IllegalArgumentException();
        }
        redo.clear();
        undo.push(i);
        undo.push(j);
        undo.push(image[i][j]);
        int pixel = (int) (image[i][j] * scaleFactor);
        if (pixel > MAX_PIXEL_VALUE){
            image[i][j] = MAX_PIXEL_VALUE;

        }else{
            image[i][j] = pixel;
        }
    }

    /**
     * Assigns a given color value to the pixel at the given position.
     * @param i row number
     * @param j col number
     * @param color the given color
     */
    public void assign(int i, int j, int color) {
        if (i >= image.length || j >= image[0].length || i < 0 || j < 0){
            throw new IndexOutOfBoundsException();
        }
        if (color < 0 || color > MAX_PIXEL_VALUE ){
            throw new IllegalArgumentException();
        }

        redo.clear();
        undo.push(i);
        undo.push(j);
        undo.push(image[i][j]);
        image[i][j] = color;
    }

    /**
     * Sets the color value of the pixel at the given position as zero.
     * @param i row number
     * @param j col number
     */
    public void delete(int i, int j) {
        if (i >= image.length || j >= image[0].length || i < 0 || j < 0){
            throw new IndexOutOfBoundsException();
        }
        redo.clear();
        undo.push(i);
        undo.push(j);
        undo.push(image[i][j]);
        image[i][j] = 0;
    }

    /**
     * Updates the image by undoing the latest operation.
     * @return set a boolean indicating whether we have successfully undo an operation
     */
    public boolean undo() {
        if (undo.isEmpty() || undo.size() < amt){
            return false;
        }
        int[] op = undo.multiPop(amt);
        int i = op[even];
        int j = op[1];
        int color = op[0];

        int[] redoOp = new int[amt];
        redoOp[0] = i;
        redoOp[1] = j;
        redoOp[even] = image[i][j];
        image[i][j] = color;
        redo.multiPush(redoOp);

        return true;
    }

    /**
     * Updates the image by redoing the next operation.
     * @return set a boolean indicating whether we have successfully redo an operation
     */
    public boolean redo() {
        if (redo.isEmpty() || redo.size() < amt){
            return false;
        }

        int[] op = redo.multiPop(amt);
        int[] undoOp = new int[amt];
        int i = op[even];
        int j = op[1];
        undoOp[0] = i;
        undoOp[1] = j;
        undoOp[even] = image[i][j];
        image[i][j] = op[0];
        undo.multiPush(undoOp);
        return true;
    }
}
