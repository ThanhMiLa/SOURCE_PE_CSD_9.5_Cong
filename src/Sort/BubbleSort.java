package Sort;
/*
 * Phần này dữ liệu đang dùng tạm là Integer, Hãy sửa lại theo như đề bài bằng các đối tượng như Student, Animal, Car, ... 
 */
public class BubbleSort {
    public static void bubbleSort(int[] a){
        for (int i = 0; i < a.length - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < a.length - i - 1; j++) {
                if(a[j] > a[j + 1]){
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swap = true;
                }
            }
            if(!swap){
                return;
            }
        }
    }
}
