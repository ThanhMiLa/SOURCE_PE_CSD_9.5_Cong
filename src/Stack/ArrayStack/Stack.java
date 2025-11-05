package Stack.ArrayStack;
/*
 * Phần này dữ liệu đang dùng tạm là Integer, Hãy sửa lại theo như đề bài bằng các đối tượng như Student, Animal, Car, ... 
 */
public class Stack {
    int size;
    int capacity;
    Integer[] data;

    public Stack(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.data = new Integer[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void push(int value) {
        if (!isFull()) {
            data[size++] = value;
        } else {
            System.out.println("Stack is full, can't push");
        }
    }

    public Integer pop() {
        if (!isEmpty()) {
            int result = data[size - 1];
            data[size - 1] = null;
            size --;
            return result;
        } else {
            System.out.println("Stack is empty, can't pop");
            return null;
        }
    }

    public Integer peek() {
        if (!isEmpty()) {
            return data[size - 1];
        } else {
            return null;
        }
    }

    public void display(){
        for(int i = size - 1; i >= 0; i --){
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
    }
}

