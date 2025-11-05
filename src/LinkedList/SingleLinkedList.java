package LinkedList;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    private Node head;
    private Node tail;

    public boolean isEmpty(){
        return head == null;
    }

    public int size(){
        Node temp = head;
        int count = 0;
        while(temp != null)
        {
            count ++;
            temp = temp.next;
        }
        return count;
    }

    public void addFirst(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void addLast(int value) {
        Node newNode = new Node(value);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void addMiddle(int value, int position) {
        if (position == 1) {
            addFirst(value);
            return;
        } else if (position == size() + 1) {
            addLast(value);
            return;
        }

        Node newNode = new Node(value);
        Node temp = head;
        int index = 1;
        while (index < position - 1) {
            temp = temp.next;
            index++;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void display(){
        for(Node node = head; node != null; node = node.next)
        {
            System.out.println(node.data);
        }
    }

    public void deleteFirst(){
        if(isEmpty()){
            System.out.println("Failed");
        }else{
            if(size() == 1){
                head = tail = null;
            }else{
                head = head.next;
            }
        }
    }

    public void deleteLast(){
        if(isEmpty()){
            System.out.println("Failed");
        }else{
            if(size() == 1){
                head = tail = null;
            }else{
                Node temp = head;
                while(temp.next != tail)
                {
                    temp = temp.next;
                }
                temp.next = null;
                tail = temp;
            }
        }
    }

    public void deleteMiddle(int pos){
        if(pos < 0 || pos >= size() || isEmpty()){
            System.out.println("Failed");
            return;
        }

        if(pos == 0){
            deleteFirst();
        }else if(pos == size() - 1){
            deleteLast();
        }else{
            Node temp = head;
            int index = 0;
            while(index < pos - 1)
            {
                temp = temp.next;
                index ++;
            }
            temp.next = temp.next.next;
        }
    }

    public void sort(){
        for(Node i = head; i != null; i = i.next)
        {
            for(Node j = head; j.next != null; j = j.next)
            {
                if(j.data > j.next.data){
                    int temp = j.data;
                    j.data = j.next.data;
                    j.next.data = temp;
                }
            }
        }
    }


}

public class SingleLinkedList {
    public static void main(String[] args) {
        
    }
}
