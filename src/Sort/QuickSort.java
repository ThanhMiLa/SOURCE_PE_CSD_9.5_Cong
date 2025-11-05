package Sort;
/*
 * Phần này dữ liệu đang dùng tạm là Integer, Hãy sửa lại theo như đề bài bằng các đối tượng như Student, Animal, Car, ... 
 */
public class QuickSort {
    static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;

        return i + 1;
    }

    static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int partition = partition(array, left, right);
            quickSort(array, left, partition - 1);
            quickSort(array, partition + 1, right);
        }
    }
}
