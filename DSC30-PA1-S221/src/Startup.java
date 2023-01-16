/*
    Name: Zehui Zhang
    PID:  A16151490
 */

/**
 * Implement methods in Java
 * @author Zehui Zhang
 * @since  08/03/2021
 */

public class Startup {
    /**
     * This method takes an array arr and checks whether an odd position contains a
     * string with odd length and an even position contains a string with even length.
     * @param arr string list
     * @return boolean array
     */
    public static boolean[] arrEvenOdd(String[] arr){
        // Create the boolean list with same length of arr to store the result
        boolean [] result = new boolean [arr.length];
        // Magic number
        int Double = 2;

        // For loop to check elements one by one
        for (int i = 0; i < arr.length; i++){
            //inner for loop to check if the elements satisfy the requirement
            if((arr[i].length() % Double) == (i % Double)){
                // return true if it is
                result[i] = true;
            } else{
                // return false if not
                result[i] = false;
            }
        }
        return result;
    }

    /**
     * This method takes a string input and return this string with all digits(0-9)
     * replaced with their upper-case spelled-out word
     * @param input String
     * @return String
     */
    public static String spellDigits(String input) {
        // String list contains all upper-case spelled out worlds of all digits
        String[] num = new String[]{"ZERO","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE"};
        // empty string to store the result
        String result = "";

        // for loop to check elements one by one
        for (int i = 0; i < input.length(); i++){
            // check if the element is the digit
            if (input.charAt(i) >= '0' && input.charAt(i) <= '9'){
                // replace with upper case world
                char[] chararray = {input.charAt(i)};
                int intnum = Integer.parseInt(new String(chararray));
                result += num[intnum];
            } else{
                // if not just append the original element
                result += input.charAt(i);
            }
        }
        return result;
    }

    /**
     * This method takes a string input and returns a new string that has the case
     * of all letters swapped.
     * @param input String
     * @return String
     */
    public static String swapCase(String input) {
        //By using the string builder to modify the string and store it
        StringBuilder temp = new StringBuilder(input);

        for (int i = 0; i < temp.length(); i++){
            // separate the string to character
            char cr = temp.charAt(i);
            // if it is upper case turning to lower
            if (Character.isUpperCase(cr)){
                temp.setCharAt(i, Character.toLowerCase(cr));
            } else{
                temp.setCharAt(i, Character.toUpperCase(cr));
            }// if it si lower case turning to upper
        }
        return temp.toString();
    }

    /**
     * This methof takes valid 2-dimensional matrix (i.e. number of rows and columns > 0)
     * and returns the binarized matrix using midrange. To binarize a matrix, each element smaller
     * than the midrange (the average of maximum and minimum element) is changed to 0,
     * and other elements are changed to 1.
     * @param matrix int[][]
     * @return int[][]
     */
    public static int[][] binarizeMatrixByMidrange (double[][] matrix){
        // Set the empty matrix with certain rows and cols.
        int[][] ints = new int[matrix.length][matrix[0].length];

        double max = 0;
        double min = matrix[0][0];
        // For loop to check elements one by one
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (max < matrix[i][j]) {
                    // Get the maximum value
                    max = matrix[i][j];
                }
                if (min > matrix[i][j]) {

                    // Get the minimum value
                    min = matrix[i][j];//算出最小值
                }
            }
        }

        // Find the midrange of max and min
        double midrange = (max +min)/2;

        // For loop to append the numbers to empty matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if(matrix[i][j] >= midrange){
                    ints[i][j] = 1;
                }else{
                    ints[i][j] = 0;
                }
            }
        }

        return ints;
    }


    /**
     * This method checks if every element of set1 is within distance one on the
     * number line from an element of set 2.
     * @param set1 int[]
     * @param set2 int[]
     * @return boolean
     */
    public static boolean allByOneChecker(int[] set1, int[] set2) {
        int count = 0;
        // double for loop to check if every elements in set1 is one of elements in set2 within distance 1
        for (int i = 0; i < set1.length; i++)
        {
            for (int j = 0; j < set2.length; j++)
            {
                // check if within the distance 1
                if (set1[i] == set2[j] || (set1[i]>=set2[j]-1 && set1[i]<=set2[j]+1 )){
                    count++;
                    break;
                }
            }
        }
        if (count == set1.length)
            return true;
        return false;
    }

    /**
     * This method checks if all digits in num are in the same row or column of the numpad
     * (shown on the right). If num only has 1 digit (0 - 9), it will count as in the same row or column.
     * You can assume that num is not negative.
     * @param num
     * @return
     */
    public static boolean numpadSRC(int num) {
        int row = 0;
        int col = 0;
        String strNum = Integer.toString(num);

        // Check by rows
        if(strNum.contains("0"))
            row++;
        if(strNum.contains("1") || strNum.contains("2") || strNum.contains("3"))
            row++;
        if(strNum.contains("4") || strNum.contains("5") || strNum.contains("6"))
            row++;
        if(strNum.contains("7") || strNum.contains("8") || strNum.contains("9"))
            row++;

        // Check by columns
        if(strNum.contains("1") || strNum.contains("4") || strNum.contains("7"))
            col++;
        if(strNum.contains("2") || strNum.contains("5") || strNum.contains("8"))
            col++;
        if(strNum.contains("3") || strNum.contains("6") || strNum.contains("9"))
            col++;

        if(row == 1 || col == 1) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args){
        /* Test for #1 */
        String[] arr1 = {"m", "ari", "na la", "nglois.", "dsc30", "!"};
        boolean[] result1 = arrEvenOdd(arr1);
        for (int i = 0; i < result1.length; i++){
            System.out.println(result1[i] + ",");
        }

        /* Test for #2 */
        System.out.println(spellDigits("DSC 30"));
        System.out.println(spellDigits("A1B2c3d4E5"));
        System.out.println(spellDigits("No digits"));

        /* Test for #3 */
        System.out.println(swapCase("abcdefg, hijklmn, OPQRST, UVWXYZ!"));
        System.out.println(swapCase("ENCRYPT encrypt?"));
        System.out.println(swapCase("CsE BaSeMeNt >.<"));

        /* Test for #4 */
        double [][] maxtrix = {{-100.0, 11.12, 133.001},{4122.5, 25.23,  -61.442},{-1092.1,   2.84, 771.555}};

        int [][] ints = new int[maxtrix.length][maxtrix[0].length];
        ints = binarizeMatrixByMidrange(maxtrix);

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j]);
            }
            System.out.println();
        }

        /* Test for #5 */
        int[] set1 = {1,2,3,5};
        int[] set2 = {1,2,3};
        int[] set3 = {};
        int[] set4 = {-2,6,7,8};
        int[] set5 = {-10,-2,1,2,3,6,7,8,9};
        int[] set6 = {3,5};
        int[] set7 = {4};
        System.out.println(allByOneChecker(set1,set2));
        System.out.println(allByOneChecker(set3,set2));
        System.out.println(allByOneChecker(set4,set5));
        System.out.println(allByOneChecker(set6,set7));

        /* Test for #6 */
        System.out.println(numpadSRC(7711404));
        System.out.println(numpadSRC(35777));
    }
}