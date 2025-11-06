package Queue.PiorityQueue;

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
    public String toString() {
        return "Student [ID=" + ID + ", name=" + name + ", gpa=" + gpa + "]";
    }

}

class Node {
    Student data;
    Node next;

    public Node(Student data) {
        this.data = data;
        this.next = null;
    }
}

public class PriorityQueue {
    private Node head; 
    private int size;

    public PriorityQueue() {
        head = null;
        size = 0;
    }

    public void enqueue(Student s) {
        Node newNode = new Node(s);
        if (head == null || s.compareTo(head.data) > 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && s.compareTo(current.next.data) <= 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Hàng đợi rỗng!");
            return null;
        }
        Student s = head.data;
        head = head.next;
        size--;
        return s;
    }

    public Student peek() {
        if (isEmpty()) {
            System.out.println("Hàng đợi rỗng!");
            return null;
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Hàng đợi rỗng!");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.enqueue(new Student("1", "Thanh", 3.2));
        queue.enqueue(new Student("2", "Hoang", 3.1));
        queue.enqueue(new Student("3", "Vu", 2.2));
        queue.enqueue(new Student("4", "Linh", 3.8));
        queue.enqueue(new Student("5", "Tung", 4.0));
        queue.display();
    }
}

