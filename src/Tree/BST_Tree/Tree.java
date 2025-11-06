package Tree.BST_Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Source này hiện tại đang dùng kiểu dữ liệu đói tượng Student, mọi người copy
 * paste hãy thay đổi Student thành kiểu dữ liệu mà đề bài yêu cầu
 * Tìm hiểu thêm cách cài đặt hàm compareTo() vì ở đây compareTo() theo mẫu là
 * so sánh gpa là kiểu double, tìm hiểu thêm cách so sánh theo Integer hay
 * String
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
    public String toString() {
        return "Student [ID=" + ID + ", name=" + name + ", gpa=" + gpa + "]";
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}

class Node {
    Student data;
    Node left;
    Node right;

    public Node(Student data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BST {
    Node root;

    public boolean isEmpty() {
        return root == null;
    }

    /* ============== INSERT ============== */
    public void insert(Student value) {
        root = insert(root, value);
    }

    public Node insert(Node r, Student value) {
        if (r == null) {
            return new Node(value);
        }
        int cmp = r.data.compareTo(value);
        if (cmp > 0) {
            r.left = insert(r.left, value);
        } else if (cmp < 0) {
            r.right = insert(r.right, value);
        }
        return r;
    }

    /* ============== DUYỆT CÂY ============== */
    // InOrder
    public void inOrder() {
        inOrderRec(root);
    }

    public void inOrderRec(Node r) {
        if (r != null) {
            inOrderRec(r.left);
            System.out.println(r.data);
            inOrderRec(r.right);
        }
    }

    // PreOrder
    public void preOrder() {
        preOrderRec(root);
    }

    public void preOrderRec(Node r) {
        if (r != null) {
            System.out.print(r.data + " ");
            preOrderRec(r.left);
            preOrderRec(r.right);
        }
    }

    // PostOrder
    public void postOrder() {
        postOrderRec(root);
    }

    public void postOrderRec(Node r) {
        if (r != null) {
            postOrderRec(r.left);
            postOrderRec(r.right);
            System.out.print(r.data + " ");
        }
    }

    // BFS -- Cách duyệt này cần import Queue vs LinkedList, nếu đề bài ko cho phép
    // dùng hàm có sẵn có thể dùng Queue vs LinkedList trong Source
    public void BFS() {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            System.out.println(curNode.data);

            if (curNode.left != null)
                queue.add(curNode.left);
            if (curNode.right != null)
                queue.add(curNode.right);
        }
    }

    /* ================ SEARCH ================ */
    /*
     * Các hàm search bằng 3 cách duyệt, tùy đề bài mà sử dụng, đề bài có thể nói
     * tìm kiếm theo ID hoặc name hoặc ...
     */
    /* Search Theo InOrder */
    public Node searchByNameInOrder(String name) {
        return searchByNameInOrder(root, name);
    }

    private Node searchByNameInOrder(Node node, String name) {
        if (node == null)
            return null;

        Node left = searchByNameInOrder(node.left, name);
        if (left != null)
            return left;

        if (node.data.getName().equalsIgnoreCase(name)) {
            return node;
        }

        return searchByNameInOrder(node.right, name);
    }

    /* Search Theo PreOrder */
    public Node searchByNamePreOrder(String name) {
        return searchByNamePreOrder(root, name);
    }

    private Node searchByNamePreOrder(Node node, String name) {
        if (node == null)
            return null;

        if (node.data.getName().equalsIgnoreCase(name)) {
            return node;
        }

        Node left = searchByNamePreOrder(node.left, name);
        if (left != null)
            return left;

        return searchByNamePreOrder(node.right, name);
    }

    /* Search Theo PostOrder */
    public Node searchByNamePostOrder(String name) {
        return searchByNamePostOrder(root, name);
    }

    private Node searchByNamePostOrder(Node node, String name) {
        if (node == null)
            return null;

        Node left = searchByNamePostOrder(node.left, name);
        if (left != null)
            return left;

        Node right = searchByNamePostOrder(node.right, name);
        if (right != null)
            return right;

        if (node.data.getName().equalsIgnoreCase(name)) {
            return node;
        }

        return null;
    }

    /* ============== REMOVE ============== */

    public void remove(Student value) {
        root = remove(root, value);
    }

    public Node remove(Node root, Student value) {
        if (root == null)
            return null;

        int cmp = root.data.compareTo(value);
        if (cmp > 0) {
            root.left = remove(root.left, value);
        } else if (cmp < 0) {
            root.right = remove(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                root.data = findMinNode(root.right);
                root.right = remove(root.right, root.data);
                return root;
            }
        }
        return root;
    }

    public Student findMinNode(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    /* ============== ĐẾM SỐ NODE ============== */
    public int countNode() {
        return countNode(root);
    }

    public int countNode(Node root) {
        if (root == null)
            return 0;
        return 1 + countNode(root.left) + countNode(root.right);
    }

    /* ============== ĐẾM SỐ LÁ ============== */
    public int countLeafNodes() {
        return countLeafNodes(root);
    }

    public int countLeafNodes(Node root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        return countLeafNodes(root.left) + countLeafNodes(root.right);
    }
}

public class Tree {
    public static void main(String[] args) {
        // Ví dụ dùng:
        
    }
}
