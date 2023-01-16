/*
 * NAME: TODO
 * PID:  TODO
 */
 
/**
 * TODO: add class header
 */
public class RuntimeAnalysis {

    private static final int NUM_DATA = 1000000;
    private static final int NUM_RUN = 10;
    private static final int NUM_TEST = 5;
    private static final int MIN = 0;
    private static final int MAX = 100000;
    private static final int[] TimSortParamValues = {4, 8, 16, 32, 64, 128};

    /**
     * Returns an array of random numbers
     *
     * @param size the number of random numbers wanted
     * @param min  the min value for random number
     * @param max  the max value for random number
     * @return an array of random numbers
     */
    public static int[] randomNumbers(int size, int min, int max) {

        int[] randNums = new int[size];
        for (int i = 0; i < size; i++) {
            randNums[i] = (int) ((Math.random() * ((max - min) + 1)) + min);
        }
        return randNums;
    }

    public static void timeInsertionSort(int[] data, int numRun) {
        long startTime = 0, endTime = 0, totalTime = 0;
        Sorts sorts = new Sorts();
        int[] temp = new int[data.length];
        System.arraycopy(data, 0, temp, 0, data.length);
        for (int i = 0; i < numRun; i++) {
            System.arraycopy(temp, 0, data, 0, temp.length);
            startTime = System.currentTimeMillis();
            sorts.InsertionSort(data, 0, data.length - 1);
            endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        System.out.println("Benchmarking insertion sort: ");
        System.out.println("Number of data to sort: " + data.length);
        System.out.println("Average time taken to sort: " + totalTime / numRun + " ms");
        System.out.println();
    }

    public static void timeMergeSort(int[] data, int numRun) {
        long startTime = 0, endTime = 0, totalTime = 0;
        Sorts sorts = new Sorts();
        int[] temp = new int[data.length];
        System.arraycopy(data, 0, temp, 0, data.length);
        for (int i = 0; i < numRun; i++) {
            System.arraycopy(temp, 0, data, 0, temp.length);
            startTime = System.currentTimeMillis();
            sorts.MergeSort(data, 0, data.length - 1);
            endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        System.out.println("Benchmarking merge sort: ");
        System.out.println("Number of data to sort: " + data.length);
        System.out.println("Average time taken to sort: " + totalTime / numRun + " ms");
        System.out.println();
    }

    public static void timeTimSort(int[] data, int numRun, int param) {
        long startTime = 0, endTime = 0, totalTime = 0;
        Sorts sorts = new Sorts();
        int[] temp = new int[data.length];
        System.arraycopy(data, 0, temp, 0, data.length);
        for (int i = 0; i < numRun; i++) {
            System.arraycopy(temp, 0, data, 0, temp.length);
            startTime = System.currentTimeMillis();
            sorts.TimSort(data, 0, data.length - 1, param);
            endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        System.out.println("TimSort Parameter Value: " + param);
        System.out.println("Benchmarking quick sort: ");
        System.out.println("Number of data to sort: " + data.length);
        System.out.println("Average time taken to sort: " + totalTime / numRun + " ms");
        System.out.println();

    }


    public static void main(String[] args) {
        int numData;

        numData = NUM_DATA;
        for (int i = 0; i < NUM_TEST; i++) {
            int[] data = randomNumbers(numData, MIN, MAX);
            for (int j = 0;j< TimSortParamValues.length; j++){
                timeTimSort(data, NUM_RUN, TimSortParamValues[j] );
            }

            numData += numData;
        }
    }
}


