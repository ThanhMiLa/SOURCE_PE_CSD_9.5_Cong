package Heap;

/**
 * Source này hiện tại đang dùng kiểu dữ liệu đói tượng Student, mọi người copy
 * paste hãy thay đổi Student thành kiểu dữ liệu mà đề bài yêu cầu
 * Nhớ phải vào insert code để Implement equal vs hashCode, tự thêm getter vs
 * setter nếu đề bài yêu cầu,
 * Tìm hiểu thêm cách cài đặt hàm compareTo() vì ở đây compareTo() theo mẫu là
 * so sánh gpa là kiểu double, tìm hiểu thêm cách so sánh theo Integer hay
 * String
 */
class Student implements Comparable<Student> {
    private String ID;
    private String name;
    private double gpa;

    public Student(String iD, String name, double gpa) {
        ID = iD;
        this.name = name;
        this.gpa = gpa;
    }

    @Override
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

public class MinHeap {
    private Student[] data;
    private int maxSize;
    private int currentSize = 0;

    public MinHeap(int size) {
        this.maxSize = size;
        data = new Student[size];
    }

    /* ============================ INSEART ============================= */
    public boolean insert(Student value) {
        if (currentSize == maxSize)
            return false;
        data[currentSize] = value;
        siftUp(currentSize);
        currentSize++;
        return true;
    }

    /* ============================ REMOVE ============================= */
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

    public int findNode(Student value) {
        for (int i = 0; i < currentSize; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /* ============================ UPDATE ============================= */
    public boolean update(Student oldValue, Student newValue) {
        int index = findNode(oldValue);
        if (index < 0)
            return false;
        data[index] = newValue;
        if (index > 0 && data[index].compareTo(data[(index - 1) / 2]) < 0) {
            siftUp(index);
        } else {
            siftDown(index);
        }
        return true;
    }

    /* ================================== DUYỆT================================== */
    // PostOrder
    public void postOrder() {
        postOrder(0);
    }

    public void postOrder(int i) {
        if (i >= currentSize) {
            return;
        }
        postOrder(2 * i + 1);
        postOrder(2 * i + 2);
        System.out.println(data[i]);
    }

    // InOrder
    public void inOrder() {
        inOrder(0);
    }

    public void inOrder(int i) {
        if (i >= currentSize) {
            return;
        }
        inOrder(2 * i + 1);
        System.out.println(data[i]);

        inOrder(2 * i + 2);
    }

    // PreOrder
    public void preOrder() {
        preOrder(0);
    }

    public void preOrder(int i) {
        if (i >= currentSize) {
            return;
        }
        System.out.println(data[i]);
        preOrder(2 * i + 1);
        preOrder(2 * i + 2);
    }

    /*
     * ============================ SiftUp vs SiftDown ============================
     */
    public void siftUp(int index) {
        if (index == 0)
            return;
        int parantIndex = (index - 1) / 2;
        if (data[parantIndex].compareTo(data[index]) > 0) {
            Student temp = data[index];
            data[index] = data[parantIndex];
            data[parantIndex] = temp;
            siftUp(parantIndex);
        }
    }

    public void siftDown(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        // min-heap: chọn con NHỎ hơn
        if (left < currentSize && data[left].compareTo(data[smallest]) < 0) {
            smallest = left;
        }

        if (right < currentSize && data[right].compareTo(data[smallest]) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            Student temp = data[index];
            data[index] = data[smallest];
            data[smallest] = temp;
            siftDown(smallest);
        }
    }

    /* ============================ PEEK vs POLL ============================= */
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

    /* ============================ FIND MAX (PreOrder) =========================== */
    public Student findMaxPreOrder() {
        if (currentSize == 0)
            return null;
        return findMaxPreOrder(0);
    }

    private Student findMaxPreOrder(int i) {
        if (i >= currentSize)
            return null;

        Student max = data[i];

        Student leftMax = findMaxPreOrder(2 * i + 1);
        if (leftMax != null && leftMax.compareTo(max) > 0)
            max = leftMax;

        Student rightMax = findMaxPreOrder(2 * i + 2);
        if (rightMax != null && rightMax.compareTo(max) > 0)
            max = rightMax;

        return max;
    }

    /* ============================ FIND MAX (InOrder) ============================ */
    public Student findMaxInOrder() {
        if (currentSize == 0)
            return null;
        return findMaxInOrder(0);
    }

    private Student findMaxInOrder(int i) {
        if (i >= currentSize)
            return null;

        Student max = findMaxInOrder(2 * i + 1);
        if (max == null)
            max = data[i];
        else if (data[i] != null && data[i].compareTo(max) > 0)
            max = data[i];

        Student rightMax = findMaxInOrder(2 * i + 2);
        if (rightMax != null && rightMax.compareTo(max) > 0)
            max = rightMax;

        return max;
    }

    /* ============================ FIND MAX (PostOrder)============================ */
    public Student findMaxPostOrder() {
        if (currentSize == 0)
            return null;
        return findMaxPostOrder(0);
    }

    private Student findMaxPostOrder(int i) {
        if (i >= currentSize)
            return null;

        Student leftMax = findMaxPostOrder(2 * i + 1);
        Student rightMax = findMaxPostOrder(2 * i + 2);

        Student max = null;
        if (leftMax != null)
            max = leftMax;
        if (rightMax != null && (max == null || rightMax.compareTo(max) > 0))
            max = rightMax;
        if (max == null || (data[i] != null && data[i].compareTo(max) > 0))
            max = data[i];

        return max;
    }

}
