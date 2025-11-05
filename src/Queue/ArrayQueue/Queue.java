package Queue.ArrayQueue;
/*
 * Phần này dữ liệu đang dùng tạm là Integer, Hãy sửa lại theo như đề bài bằng các đối tượng như Student, Animal, Car, ... 
 */
public class Queue {
    int[] array;
    int size;
    int first, last;

    public Queue(int size) {
        this.size = size;
        array = new int[size];
        first = -1;
        last = -1;
    }

    public boolean isEmpty() {
        return first == -1;
    }

    public boolean isFull() {
        return (first == 0 && last == size - 1) || (first == last + 1);
    }

    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Cannot enqueue because queue is full");
            return;
        }

        if (isEmpty()) {
            first = last = 0;
        } else {
            last = (last + 1) % size;
        }

        array[last] = x;
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Cannot dequeue because queue is empty");
            return;
        }

        if (first == last) {
            first = last = -1;
        } else {
            first = (first + 1) % size;
        }
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return Integer.MIN_VALUE;
        }
        return array[first];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        int i = first;
        while (true) {
            System.out.println(array[i]);
            if (i == last) break;
            i = (i + 1) % size;
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue(10);
        queue.enqueue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(9);
        queue.enqueue(3);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(10);
        queue.enqueue(8);
        queue.enqueue(1);
        queue.display();
    }
}
