package Sort;
/*
 * Phần này dữ liệu đang dùng tạm là Integer, Hãy sửa lại theo như đề bài bằng các đối tượng như Student, Animal, Car, ... 
 */
public class MergerSort {
    static void merge(int[] array, int left, int mid, int right){
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] array1 = new int[n1];
        int[] array2 = new int[n2];

        for (int i = 0; i < n1; i++) {
            array1[i] = array[left + i];
        }

        for (int i = 0; i < n2; i++) {
            array2[i] = array[mid + i + 1];
        }

        int i = 0, j = 0;
        int index = left;

        while (i < n1 && j < n2) {
            if(array1[i] <= array2[j]){
                array[index] = array1[i];
                i++;
            }else{
                array[index] = array2[j];
                j++;
            }
            index ++;
        }
        
        while (i < n1) {
            array[index] = array1[i];
            i++;
            index ++;
        }

        while(j < n2){
            array[index] = array2[j];
            j++;
            index ++;
        }
    }

    static void mergeSort(int[] array, int left, int right){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }
}
