package Lab05;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Avl {

	NodeAvl root;

    int height(NodeAvl node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int max(int a, int b) {
        return Math.max(a, b);
    }

    NodeAvl rightRotate(NodeAvl y) {
    	NodeAvl x = y.left;
    	NodeAvl T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    NodeAvl leftRotate(NodeAvl x) {
    	NodeAvl y = x.right;
    	NodeAvl T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(NodeAvl node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    NodeAvl insert(NodeAvl node, int key) {
        if (node == null)
            return (new NodeAvl(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    NodeAvl minValueNode(NodeAvl node) {
    	NodeAvl current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    NodeAvl deleteNode(NodeAvl root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
            	NodeAvl temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
            	NodeAvl temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    NodeAvl search(NodeAvl root, int key) {
        if (root == null || root.key == key)
            return root;

        if (root.key < key)
            return search(root.right, key);

        return search(root.left, key);
    }

    int getMin(NodeAvl root) {
        if (root == null)
            throw new NoSuchElementException("Tree is empty");
        while (root.left != null)
            root = root.left;
        return root.key;
    }

    int getMax(NodeAvl root) {
        if (root == null)
            throw new NoSuchElementException("Tree is empty");
        while (root.right != null)
            root = root.right;
        return root.key;
    }

    NodeAvl parent(NodeAvl root, int key) {
        if (root == null || root.key == key)
            return null;

        if ((root.left != null && root.left.key == key) || (root.right != null && root.right.key == key))
            return root;

        if (root.key < key)
            return parent(root.right, key);

        return parent(root.left, key);
    }

    boolean son(NodeAvl root, int key) {
    	NodeAvl node = search(root, key);
        return node != null && (node.left != null || node.right != null);
    }

    void preOrden(NodeAvl node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrden(node.left);
            preOrden(node.right);
        }
    }

    void printTree(NodeAvl root) {
        if (root == null)
            return;

        Queue<NodeAvl> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
        	NodeAvl current = queue.poll();
            System.out.print(current.key + " ");

            if (current.left != null)
                queue.add(current.left);

            if (current.right != null)
                queue.add(current.right);
        }
    }
}
