package Heap;

/**
 * Source này hiện tại đang dùng kiểu dữ liệu đói tượng Student, mọi người copy paste hãy thay đổi Student thành kiểu dữ liệu mà đề bài yêu cầu
 * Nhớ phải vào insert code để Implement equal vs hashCode, tự thêm getter vs setter nếu đề bài yêu cầu, 
 * Tìm hiểu thêm cách cài đặt hàm compareTo() vì ở đây compareTo() theo mẫu là so sánh gpa là kiểu double, tìm hiểu thêm cách so sánh theo Integer hay String
 */
class Student {
    private String ID;
    private String name;
    private double gpa;

    public Student(String iD, String name, double gpa) {
        ID = iD;
        this.name = name;
        this.gpa = gpa;
    }

    public int compareTo(Student o) {
        if (this.gpa > o.gpa)
            return 1;
        else if (this.gpa < o.gpa)
            return -1;
        return 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ID == null) ? 0 : ID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (ID == null) {
            if (other.ID != null)
                return false;
        } else if (!ID.equals(other.ID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Student [ID=" + ID + ", name=" + name + ", gpa=" + gpa + "]";
    }

    // Tự thêm getter vs Setter

}

public class MaxHeap {
    private Student[] data;
    private int maxSize;
    private int currentSize = 0;

    public MaxHeap(int size) {
        this.maxSize = size;
        data = new Student[size];
    }

    public boolean add(Student value) {
        if (currentSize == maxSize)
            return false;
        data[currentSize] = value;
        siftUp(currentSize);
        currentSize++;
        return true;
    }

    public boolean remove(Student value) {
        int index = findNode(value);
        if (index >= 0) {
            data[index] = data[currentSize - 1];
            data[currentSize - 1] = null;
            currentSize--;
            siftDown(index);
            return true;
        } else {
            return false;
        }
    }

    public boolean update(Student oldValue, Student newValue) {
        int index = findNode(oldValue);
        if (index < 0)
            return false;

        data[index] = newValue;

        if (index > 0 && data[index].compareTo(data[(index - 1) / 2]) > 0) {
            siftUp(index);
        } else {
            siftDown(index);
        }
        return true;
    }

    public void siftUp(int index) {
        if (index == 0)
            return;
        int parantIndex = (index - 1) / 2;
        if (data[parantIndex].compareTo(data[index]) < 0) {
            Student temp = data[index];
            data[index] = data[parantIndex];
            data[parantIndex] = temp;
            siftUp(parantIndex);
        }
    }

    public void siftDown(int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < currentSize && data[left].compareTo(data[largest]) > 0) {
            largest = left;
        }

        if (right < currentSize && data[right].compareTo(data[largest]) > 0) {
            largest = right;
        }

        if (largest != index) {
            Student temp = data[index];
            data[index] = data[largest];
            data[largest] = temp;
            siftDown(largest);
        }
    }

    public int findNode(Student value) {
        for (int i = 0; i < currentSize; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public Student peek() {
        return currentSize == 0 ? null : data[0];
    }

    public Student poll() {
        if (currentSize == 0)
            return null;
        Student top = data[0];
        remove(top);
        return top;
    }

    public void display() {
        for (int i = 0; i < currentSize; i++) {
            System.out.println(data[i]);
        }
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(10);
        heap.add(new Student("1", "Thanh", 3.5));
        heap.add(new Student("2", "Hoang", 3.2));
        heap.add(new Student("3", "Anh", 2.1));
        heap.add(new Student("4", "Bao", 2.8));
        heap.add(new Student("5", "Tung", 2.0));
        heap.remove(new Student("2", null, 0));
        heap.display();

    }

}
