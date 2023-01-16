
/** 
 * NAME: First Last
 * PID: Axxxxxxxx
 */

 /**
 * Description of Sample Class
 * 
 * @author First Last
 * @since ${date}
 */



class StartUp{
    /** 
    * Description of constructor
    * @param para Description of para
    */
    public StartUp(){}
    /**
     * This method checks whether the index and value have the same parity
     *
     * @param arr An integer array
     * @return parity A boolean array indicating if index and value has the same parity
     */
    public static boolean[] arrEvenOdd(int[] arr)
    {   
        boolean[] parity = new boolean[arr.length];
        //edge cases
        if ( arr.length == 0 ){return parity;} 

        for ( int i = 0; i < arr.length; i++ )
        {
            int abs = Math.abs(arr[i]);
            if ( abs % 2 == 0 && i % 2 == 0 ) { parity[i] = true;}
            else if (abs % 2 == 0 && i % 2 == 1 ) {parity[i] = false;}
            else if ( abs % 2 == 1 && i % 2 == 1 ) { parity[i] = true;}
            else if ( abs % 2 == 1 && i % 2 == 0 ) { parity[i] = false;}
        }
        return parity;
    }
    
    /**
     * This method takes a string input and returns this string with all vowels removed.
     *
     * @param input A string 
     * @return output A string containing rest characters with vowels removed from input
     */
    public static String removeVowels(String input)
    {
        String output = input.replaceAll("[AEIOUaeiou]","");
        return output;
    }

    /**
     * This method takes a string compound and calculates its mass.
     *
     * @param compound A String containing elements followed by their counts
     * @return mass An integer indicating the mass of compound
     */
    public static int compoundMass(String compound)
    {
        int mass = 0;
        for (int i = 0; i < compound.length(); i += 2)
            mass += ( compound.charAt(i) - 64 ) * Character.getNumericValue(compound.charAt(i+1)); // using ASCII table
        return mass;
    }

    /**
     * This method returns the binarized matrix of given matrix.
     *
     * @param matrix A 2-D array with doubles
     * @param threshold A double indiciating the threshold
     * @return binarized A 2-D binarized matrix 
     */
    public static int[][] binarizeMatrix(double[][] matrix, double threshold)
    {
        int[][] binarized = new int[matrix.length][matrix[0].length];
        for ( int i = 0 ; i < matrix.length; i++ ){
            for ( int j = 0; j < matrix[0].length; j++ ){
                if ( matrix[i][j] < threshold ){ binarized[i][j] = 0;}
                else{ binarized[i][j] = 1; }
            }
        }
        return binarized;
    }

    /**
     * This method encrypts the input string by applying the Atbash cipher on letters (both
        uppercase and lowercase) and reversing the encrypted string, and then returns the
        reversed encrypted string.
     *
     * @param input A string 
     * @return encrypted A string of reversed encrypted string of input 
     */
    public static String encryptString(String input)
    {
        String encrypted = "";
        for (int i = input.length() - 1; i >= 0; i-- ){
            char c = input.charAt(i);
            // current character is upper case or lower case
            if (c <= 'Z' && c >= 'A' ){
                int value = (int) c;
                int e = 65 + 90 - value;
                char ch = (char) e;
                encrypted += ch ;  //65 is A, 90 is Z in ASCII table
                
            }else if ( c <= 'z' && c >= 'a'){
                int value = (int) c;
                int e = 97 + 122 - value;
                char ch = (char) e;
                encrypted += ch ; // a = 97, z = 122 in ASCII table
                
            }else{
                encrypted += c;
            }
        }
        return encrypted;
    }

    /**
     * This method takes an array arr and a direction, and rotates the array
     *  to the specified direction for 1 position (index).
     *  When direction is true, rotate to the left; otherwise to the right.
     *
     * @param arr An array with doubles
     * @param direction A boolean value indicating the direction of rotation 
     * @return A rotated array of arr 
     */
    public static double[] rotateArray(double[] arr, boolean direction)
    {
        if ( direction ){
            for (int i = 1; i < arr.length ; i++ ){
                double temp = arr[i];
                arr[i] = arr[i-1];
                arr[i-1] = temp;
            }
        }else{
            for (int i = 1; i < arr.length; i++ ){
                double temp = arr[i];
                arr[i] = arr[0];
                arr[0] = temp;
            }
        }
        return arr;
    }

    /**
     * This method takes an array arr , rotates every three consecutive numbers.
     *
     * @param arr An array with doubles
     * @return arr A rotated array of arr 
     */
    public static double[] rotateTriplets(double[] arr)
    {
        int numTriplets = arr.length / 3;
        int numRemain = arr.length % 3;
        for (int i = 0; i < numTriplets; i++){
            //rotate left
            if ( i % 2 == 0){
                for (int j = 1; j < 3; j++ ){
                    double temp = arr[3*i + j];
                    arr[3*i + j] = arr[3*i + j-1];
                    arr[3*i + j-1] = temp;
                }
            }
            //rotate right
            else if (i % 2 == 1){
                for (int k = 1; k < 3; k++ ){
                    double temp = arr[3*i + k];
                    arr[3*i + k] = arr[3*i];
                    arr[3*i] = temp;
                }
            }
        } 
        //swap if numRemain is 2
        if ( numRemain == 2){
            double temp = arr[arr.length - 2];
            arr[arr.length - 2] = arr[arr.length - 1];
            arr[arr.length - 1] = temp;
        }
        return arr;
    }

    /**
     * This method checks if set1 is a subset of set2, 
     * and returns true if it is (false otherwise).
     *
     * @param set1 An integer array
     * @param set2 An integer array
     * @return A boolean value checks if set1 is a subset of set2
     */
    public static boolean subsetChecker(int[] set1, int[] set2)
    {
        // if set 1 is empty, then it is vacuously a subset of set2
        if (set1.length == 0) {return true;}

        int i = 0; 
        int j = 0; 
        for (i = 0; i < set1.length; i++) { 
            for (j = 0; j < set2.length; j++) 
                if (set1[i] == set2[j]) 
                    break; 
            if (j == set2.length) 
                return false; 
        } 
        return true; 
    }

    /**
     * This method checks if all digits in num are in the same row 
     * or column of the numpad (shown on the right).
     *
     * @param num An integer array
     * @return  A boolean value 
    */
    public static boolean numpadSRC(int num)
    {
       
        String number = String.valueOf(num);
        String[] digits = number.split("(?<=.)"); 
        int[] nums = new int[digits.length];
        
        for (int i = 0; i < digits.length; i++){
            nums[i] = Integer.parseInt(digits[i]);
        }       

        String[] rows = {"0","123","456","789"};
        String[] cols = {"0147", "0258", "0369"};

        for (int i = 0 ; i < rows.length; i++){
            boolean[] contain = new boolean[nums.length];
            boolean allTrue = true;
            for(int j = 0; j < nums.length; j++){
                 if (rows[i].contains(String.valueOf(nums[j])))
                    contain[j] = true;
            }
            for( boolean value : contain){
                if(!value)
                    allTrue = false;
            }
            if (allTrue)
                return true;

        }
        for (int i = 0 ; i < cols.length; i++){
            boolean[] contain = new boolean[nums.length];
            boolean allTrue = true;
            for(int j = 0; j < nums.length; j++){
                 if (cols[i].contains(String.valueOf(nums[j])))
                    contain[j] = true;
            }
            for( boolean value : contain){
                if(!value)
                    allTrue = false;
            }
            if (allTrue)
                return true;
            
        }
        return false;
    }

}