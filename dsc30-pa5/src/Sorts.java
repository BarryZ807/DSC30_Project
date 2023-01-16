/*
 * NAME: Zehui Zhang
 * PID:  A16151490
 */
import java.util.ArrayList;


/**
 * Sorts class.
 * @param <T> Generic type
 * @author Zehui Zhang
 * @since  2021-02-05
 */
public class Sorts<T extends Comparable<? super T>> {

    private static final int MIDDLE_IDX = 2;

    /**
     * This method performs insertion sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void InsertionSort(ArrayList<T> list, int start, int end) {
        if (start < end) {
            for (int i = start + 1; i <= end; i++) {
                for (int j = i; j > start; j--) {
                    if (list.get(j).compareTo(list.get(j - 1)) < 0) {

                        T temp = list.get(j);
                        list.set(j, list.get(j-1));
                        list.set(j-1, temp);
                    }
                }  
            }
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void MergeSort(ArrayList<T> list, int start, int end) {

        if (start < end)
        {
            int mid = start + (end - start) / MIDDLE_IDX;
            MergeSort(list, start, mid);
            MergeSort(list , mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param m the middle index we want to merge
     * @param r right-most index we want to merge
     */
    private void merge(ArrayList<T> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<T> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                mergedNums.add(arr.get(left));
                left++;
            }
            else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }

        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
        int p = partition(list, start, end);
        if (start < p- 1){
            QuickSort(list, start, p - 1);
        }
        if (p < end){
            QuickSort(list, p + 1, end);
        } 
    }
    /**
     * partition helper function for QuickSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param h right-most index we want to merge
     */
    private int partition(ArrayList<T> arr, int l, int h) {

        int p = (h + l) / MIDDLE_IDX;
        T pivot = arr.get(p);
        int ind = l;
        // find the correct index of pivot
        for (int i = l; i < h + 1; i++){
            if (arr.get(i).compareTo(pivot) < 0){
                ind++;
            }
        }
        //put it in the right place
        T temp = arr.get(ind);
        arr.set(ind, arr.get(p));
        arr.set(p, temp);
        //partition
        int i = l;
        int j = h;
        while (i <= j) {
            while (arr.get(i).compareTo( pivot)<0)
                  i++;
            while (arr.get(j).compareTo( pivot)>0)
                  j--;
            if (i <= j) {
                  temp = arr.get(i);
                  arr.set(i,arr.get(j));
                  arr.set(j, temp);
                  i++;
                  j--;
            }
      }
        return ind;
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param cutoff the minimum length of an subsection of the arraylist
     *               such that we switch to Insertion Sort
     */
    public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
        int p = partition(list, start, end);
        if (start < p - 1){
            if (p - 1 - start <= cutoff) {
                InsertionSort(list, p - 1, end);
            }else {
                QuickSort(list, start, p - 1);
            }
        }
        if (p < end){
            if (end - p - 1 <= cutoff) {
                InsertionSort(list, p + 1, end);
            } else{
                QuickSort(list, p + 1, end);
            }
        } 
    }

    /**
     * This method performs a Tim sort that with a certain param
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param param The length of the initial splits that are sorted prior to merging
     */
    public void TimSort(ArrayList<T> list, int start, int end, int param){
        // Insertion sort if param >= length of list
        if (param >= end - start + 1) {
            InsertionSort(list, start, end);
        } 
        // Else if param < length of list
        else{
            ArrayList<Integer> lens = new ArrayList<Integer>(); //lengths of each subarray
            ArrayList<Integer> indices = new ArrayList<Integer>(); //indices of last index of each subarray
            // add indices ana lengths of each subarray 
            int i = start - 1;
            while (i + param <= end - 1){
                i += param;
                indices.add(i);
                lens.add(param);
            }
            //add index and length of last subarray 
            indices.add(end);
            lens.add(end - i);

            // Sort subarray with insertion sort
            for (int k = 0; k < lens.size(); k++) {
                if (k == 0){
                    InsertionSort(list, start, indices.get(0));
                } else{
                    InsertionSort(list, indices.get(k - 1) + 1, indices.get(k));
                }
            }
            //iteration of sweeping
            while (lens.size() > 1) {
                // base case with two sub arrays
                if (lens.size() == MIDDLE_IDX) {
                    merge(list, start, indices.get(0), end);
                    lens.remove(1);
                    lens.set(0, end - start + 1);
                } 
                // more than two sub arrays
                else {
                    int num_merged = lens.size() / MIDDLE_IDX;
                    int j = 0;
                    // sweeping 
                    while (j < num_merged) {
                        if (j == 0){
                            merge(list, start, indices.get(0), indices.get(1));
                        } else{
                            merge(list, indices.get(MIDDLE_IDX * j - 1) + 1, indices.get(MIDDLE_IDX * j), indices.get(MIDDLE_IDX * j + 1));
                        }
                        j++;
                    }
                    //update indices and lengths
                    int k = 0;
                    while (k < num_merged) {
                        indices.set(MIDDLE_IDX * k, null);
                        int temp = lens.get(MIDDLE_IDX * k + 1);
                        lens.set(MIDDLE_IDX * k + 1, null);
                        lens.set(MIDDLE_IDX * k, temp + lens.get(MIDDLE_IDX * k));
                        k++;
                    }
                    while(indices.contains(null)) {
                        indices.remove(null); 
                    }
                    while(lens.contains(null)) {
                        lens.remove(null);
                    }
                }

            }
        }
    }

}
