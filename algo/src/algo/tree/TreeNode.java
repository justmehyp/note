package algo.tree;

public class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private Integer data;

    public TreeNode(TreeNode left, TreeNode right, Integer data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
