import static org.junit.Assert.*;

import org.junit.Test;

public class SortsTest {
    @Test
    public void InsertionSortTest() {
        int[] arr1 = {9, 8, 10, 6, 7, 5, 4, 2, 3, 1};
        Sorts sorts1 = new Sorts();
        sorts1.InsertionSort(arr1, 0, arr1.length - 1);
        int res1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(arr1, res1);

        int[] arr2 = {2, 5, 1, 7};
        Sorts sorts2 = new Sorts();
        sorts2.InsertionSort(arr2, 0, arr2.length - 1);
        int res2[] = {1, 2, 5, 7};
        assertArrayEquals(arr2, res2);

        int[] arr3 = {3, 2, 1};
        Sorts sorts3 = new Sorts();
        sorts3.InsertionSort(arr3, 0, arr3.length - 1);
        int res3[] = {1, 2, 3};
        assertArrayEquals(arr3, res3);
    }

    @Test
    public void MergeSortTest() {
        int[] arr1 = {9 ,8, 10 ,6 ,7 ,5 ,4 ,2 ,3, 1};
        Sorts sorts1 = new Sorts();
        sorts1.MergeSort(arr1,0,arr1.length-1);
        int res1[] = {1 ,2, 3 ,4 ,5 ,6, 7, 8 ,9, 10 };
        assertArrayEquals(arr1,res1);

        int[] arr2 = {2,5,1,7};
        Sorts sorts2 = new Sorts();
        sorts2.MergeSort(arr2,0,arr2.length-1);
        int res2[] = {1,2,5,7 };
        assertArrayEquals(arr2,res2);

        int[] arr3 = {3,2,1};
        Sorts sorts3 = new Sorts();
        sorts3.MergeSort(arr3,0,arr3.length-1);
        int res3[] = {1 ,2, 3};
        assertArrayEquals(arr3,res3);

    }


    @Test
    public void TimSortTest() {
        int[] arr1 = {9 ,8, 10 ,6 ,7 ,5 ,4 ,2 ,3, 1};
        Sorts sorts1 = new Sorts();
        sorts1.TimSort(arr1,0,arr1.length-1,4);
        int res1[] = {1 ,2, 3 ,4 ,5 ,6, 7, 8 ,9, 10 };
        assertArrayEquals(arr1,res1);

        int[] arr2 = {2,5,1,7};
        Sorts sorts2 = new Sorts();
        sorts2.TimSort(arr2,0,arr2.length-1,2);
        int res2[] = {1,2,5,7 };
        assertArrayEquals(arr2,res2);

        int[] arr3 = {3,2,1};
        Sorts sorts3 = new Sorts();
        sorts3.TimSort(arr3,0,arr3.length-1,2);
        int res3[] = {1 ,2, 3};
        assertArrayEquals(arr3,res3);
    }

}
