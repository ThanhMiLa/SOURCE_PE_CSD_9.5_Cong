package Sort;

import java.util.Arrays;

public class StringRadixSort {

    private static void countingSort(String[] arr, int pos) {
        int n = arr.length;
        String[] output = new String[n];
        int[] count = new int[256]; 

        for (int i = 0; i < n; i++) {
            int ch = (pos < arr[i].length()) ? arr[i].charAt(pos) : 0;
            count[ch]++;
        }

        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int ch = (pos < arr[i].length()) ? arr[i].charAt(pos) : 0;
            output[count[ch] - 1] = arr[i];
            count[ch]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    public static void radixSort(String[] arr) {
        int maxLen = 0;
        for (String s : arr) {
            maxLen = Math.max(maxLen, s.length());
        }

        for (int pos = maxLen - 1; pos >= 0; pos--) {
            countingSort(arr, pos);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"cat", "bat", "apple", "dog", "ant"};

        System.out.println("Trước khi sắp xếp:");
        System.out.println(Arrays.toString(arr));

        radixSort(arr);

        System.out.println("Sau khi sắp xếp:");
        System.out.println(Arrays.toString(arr));
    }
}

