package Queue.LinkedListQueue;

class Node {
    Integer data;
    Node next;

    public Node(Integer data) {
        this.data = data;
        this.next = null;
    }
}

public class Queue {
    private Node head;
    private Node tail;


    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public Integer dequeue(){
        if (!isEmpty()) {
            Node result = head;
            head = head.next;
            return result.data;
        } 
        return null;
    }

    public int peek() {
        return isEmpty() ? null : head.data;
    }

    public void display(){
        for(Node i = head; i != null; i = i.next){
            System.out.println(i.data);
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(5);
        queue.enqueue(1);
        queue.enqueue(8);
        queue.enqueue(2);
        queue.enqueue(9);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(10);
        queue.display();
    }
}
