package pro.sky.javacoursepart2.sortPractice;

import java.util.Random;

public class SortPractice {
    final int CAPACITY = 100_000;
    public int[] intArr = new int[CAPACITY];

    public static void main(String[] args) {
        SortPractice obj = new SortPractice();
        obj.setIntArray();

        int[] arr1 = obj.intArr.clone();
        int[] arr2 = obj.intArr.clone();
        int[] arr3 = obj.intArr.clone();
        int[] arr4 = obj.intArr.clone();
        int[] arr5 = obj.intArr.clone();

        long start;

        start = System.currentTimeMillis();
        sortBubble(arr1); // 12s - slowest
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        sortSelection(arr2); // 3s - medium
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        sortInsertion(arr3); // 0.5s - fastest non recursive
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        quickSort(arr4, 0, obj.CAPACITY - 1); // fastest recursive 12ms
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        mergeSort(arr5); // recursive, faster than non recursive methods 18ms
        System.out.println(System.currentTimeMillis() - start);

    }

    private void setIntArray() {
        Random rand = new Random();
        for (int i = 0; i < CAPACITY; i++) {
            rand.nextInt();
            intArr[i] = rand.nextInt();
        }
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    //  QUICKSORT (RECURSIVE)
    public static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    // MERGESORT (RECURSIVE)
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }
}

