package Tree.AVL_Tree;

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

class AVLTree {
    Node root;

    public boolean isEmpty() {
        return root == null;
    }

    public int getHeight(Node root) {
        if (root == null)
            return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public boolean isBalance(Node root) {
        if (root == null)
            return true;
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1)
            return false;
        return isBalance(root.left) && isBalance(root.right);
    }

    public boolean isBalanceTree() {
        return isBalance(root);
    }

    public Node leftRotation(Node root) {
        if (root == null)
            return null;
        Node returnNode = root.right;
        root.right = returnNode.left;
        returnNode.left = root;
        return returnNode;
    }

    public Node rightRotation(Node root) {
        if (root == null)
            return null;
        Node returnNode = root.left;
        root.left = returnNode.right;
        returnNode.right = root;
        return returnNode;
    }

    public void insert(Student value) {
        root = insert(root, value);
    }

    private Node insert(Node root, Student value) {
        if (root == null)
            return new Node(value);

        int cmp = root.data.compareTo(value);
        if (cmp > 0) {
            root.left = insert(root.left, value);
        } else if (cmp < 0) {
            root.right = insert(root.right, value);
        } else {
            return root;
        }

        int balance = getHeight(root.left) - getHeight(root.right);

        if (balance > 1) {
            if (value.compareTo(root.left.data) < 0) {
                return rightRotation(root);
            } else {
                root.left = leftRotation(root.left);
                return rightRotation(root);
            }
        }

        if (balance < -1) {
            if (value.compareTo(root.right.data) > 0) {
                return leftRotation(root);
            } else {
                root.right = rightRotation(root.right);
                return leftRotation(root);
            }
        }

        return root;
    }

    /* Ở hàm remove đầu tiên sẻ phải tìm kiếm Student theo tiêu tí nào đó bằng 3 cách search ở bên dưới 
     * Với ví dụ này là tìm kiếm theo name nên truyền vào là String name, tùy đề bài mà sử dụng
    */
    public void remove(String name) {
        Node studentTarget = searchByNameInOrder(name);
        root = remove(root, studentTarget.data);
    }

    private Node remove(Node root, Student value) {
        if (root == null)
            return null;

        int cmp = root.data.compareTo(value);
        if (cmp > 0) {
            root.left = remove(root.left, value);
        } else if (cmp < 0) {
            root.right = remove(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.data = findMinValue(root.right);
            root.right = remove(root.right, root.data);
        }

        int balance = getHeight(root.left) - getHeight(root.right);

        if (balance > 1) {
            if (getHeight(root.left.left) < getHeight(root.left.right)) {
                root.left = leftRotation(root.left);
            }
            return rightRotation(root);
        }

        if (balance < -1) {
            if (getHeight(root.right.right) < getHeight(root.right.left)) {
                root.right = rightRotation(root.right);
            }
            return leftRotation(root);
        }
        return root;
    }

    public Student findMinValue(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    // ======= Các hàm search  bằng 3 cách duyệt, tùy đề bài mà sử dụng, đề bài có thể nói tìm kiếm theo ID hoặc name hoặc ... =======
    
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

    /* Duyệt cây để test, tùy đề bài bắt duyệt theo kiểu gì, ở phần BST_Tree có đầy đủ 3 cách duyệt, ở đây lấy ví dụ cách duyệt inOrder */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
    }
}

public class Tree {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(new Student("1", "A", 3.2));
        tree.insert(new Student("2", "B", 2.5));
        tree.insert(new Student("3", "C", 3.9));
        tree.insert(new Student("4", "D", 3.6));
        tree.insert(new Student("5", "E", 2.0));
        tree.insert(new Student("5", "E", 1.0));
        tree.insert(new Student("5", "E", 0.5));
        tree.remove("E");
        tree.inOrder();
        System.out.println(tree.isBalanceTree());
    }
}
