package Sort;
/*
 * Phần này dữ liệu đang dùng tạm là Integer, Hãy sửa lại theo như đề bài bằng các đối tượng như Student, Animal, Car, ... 
 */
public class SelectionSort {
    public static void selectionSort(int[] a){
        int minIndex = -1;
        int minValue = 0;
        for (int i = 0; i < a.length; i++) {
            minIndex = i;
            minValue = a[i];
            for (int j = i + 1; j < a.length; j++) {
                if(a[j] < minValue){
                    minValue = a[j];
                    minIndex = j;
                }
            }
            int temp = a[i];
            a[i] = minValue;
            a[minIndex] = temp;
        }
    }
}
