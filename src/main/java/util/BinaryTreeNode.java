package util;

public class BinaryTreeNode<T> {
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;
    public T value;

    public BinaryTreeNode(){ }

    public BinaryTreeNode(T value) {
        this.value = value;
    }

    public BinaryTreeNode(BinaryTreeNode left, BinaryTreeNode right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
