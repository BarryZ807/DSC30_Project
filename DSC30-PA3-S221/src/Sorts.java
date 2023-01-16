/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */



public class Sorts{
    private static final int HALF_LIST = 2;


    /**
     * This method performs insertion sort on the input arraylist
     *
     * @param arr The arr we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */

    public void InsertionSort(int[] arr, int start, int end) {
        for (int i = start; i <= end; ++i) {
            int j = i;
            while (j > start && arr[j]<arr[j - 1]) {
                int temp = arr[j];
                arr[j]= arr[j - 1];
                arr[j - 1]= temp;
                --j;
            }
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param arr The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */

    public void MergeSort(int[] arr, int start, int end) {

        if (start < end)
        {
            int middle = start + (end - start) / HALF_LIST;
            MergeSort(arr, start, middle);
            MergeSort(arr , middle + 1, end);

            merge(arr, start, middle, end);
        }

    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param left left-most index we want to merge
     * @param middle the middle index we want to merge
     * @param right right-most index we want to merge
     */
    private void merge(int[] arr, int left, int middle, int right) {
        int[] temp = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = middle + 1;
        // Compare the left and right elements, which is smaller, and fill that element into temp
        while (p1 <= middle && p2 <= right) {
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // After the above loop exits, fill the remaining elements into temp in turn
        // Only one of the following two while will execute
        while (p1 <= middle) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= right) {
            temp[i++] = arr[p2++];
        }
        // Copy the final sorted result to the original array
        for (i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
    }


    /**
     * This method performs a modified QuickSort that switches to insertion sort after a certina cutoff
     *
     * @param arr The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param param The length of the initial splits that are sorted prior to merging
     */

    public void TimSort(int[] arr, int start, int end, int param) {
        if (param > end-start) {
            InsertionSort(arr, start, end);
        }

        for (int x = start; x <= end; x += param) {
            InsertionSort(arr, x, Math.min(x+param-1, end));
        }


        for (int y = param; y < (end-start+1); y *= HALF_LIST ) {
            for (int z = start; z < end; z += HALF_LIST * y) {
                int right = Math.min((z + HALF_LIST * y - 1), end);
                int mid = z + y -1;
                if(mid > end) {
                    break;
                }

                merge(arr, z, mid, right);
            }
        }
    }
}
