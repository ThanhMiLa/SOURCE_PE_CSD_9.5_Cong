package Sort;
/*
 * Phần này dữ liệu đang dùng tạm là Integer, Hãy sửa lại theo như đề bài bằng các đối tượng như Student, Animal, Car, ... 
 */
public class InsertionSort {
    public static void insertionSort(int[] a){
        for (int i = 1; i < a.length; i++) {
            int minValue = a[i];
            int index = i;
            while (index > 0 && minValue < a[index - 1]) {
                a[index] = a[index - 1];
                index --;
            }
            a[index] = minValue;
        } 
    }
}
