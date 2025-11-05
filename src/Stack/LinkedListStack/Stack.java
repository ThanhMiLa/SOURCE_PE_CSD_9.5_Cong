package Stack.LinkedListStack;

/*
 * Phần này dữ liệu đang dùng tạm là Integer, Hãy sửa lại theo như đề bài bằng các đối tượng như Student, Animal, Car, ... 
 */

class Node{
    Integer data;
    Node next;
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Stack {
    Node head;
    Node tail;

    public boolean isEmpty(){
        return head == null;
    }

    public void push(int value){
        Node newNode = new Node(value);
        if(head == null){
            head = tail = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
    }
  
    public Integer pop(){
        if(isEmpty()){
            System.out.println("Failed");
            return null;
        }else{
            int result = head.data;
            if(size() == 1){
                head = tail = null;
            }else{
                head = head.next;
            }
            return result;
        }
    }

    public Integer peek(){
        return isEmpty() ? null : head.data;
    }

    public int size(){
        Node currentNode = head;
        int size = 0;
        while(currentNode != null)
        {
            size ++;
            currentNode = currentNode.next;
        }
        return size;
    }

     public void display(){
        Node currentNode = head;
        if(isEmpty()){
            System.out.println("List is empty");
            return;
        }

        while(currentNode != null)
        {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
    }


}

