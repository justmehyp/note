package algo.tree.travel;

import algo.tree.TreeNode;
import algo.tree.TreeUtil;

import java.util.*;

/**
 * 二叉树的前序遍历
 */
public class Travel {


    /**
     *                 1
     *            2         3
     *          4   7         5
     *                      8
     *                       9
     */
    public static void main(String[] args) {
        List<Integer> treeNodes = new LinkedList<>(Arrays.asList(1, 2, 4, null, null, 7, null, null, 3, null, 5, 8, null, 9, null, null, null));
        TreeNode root = TreeUtil.preCreate(treeNodes);

        System.out.print("pre: ");
        preOrderTravel(root);
        System.out.println();

        System.out.print("mid: ");
        midOrderTravel(root);
        System.out.println();

        System.out.print("post: ");
        postOrderTravel(root);
        System.out.println();

        System.out.print("level: ");
        levelOrderTravel(root);
        System.out.println();

        System.out.print("stack pre: ");
        preOrderTravelWithStack(root);
        System.out.println();

        System.out.print("stack mid: ");
        midOrderWithStackTravle(root);
        System.out.println();

        System.out.print("stack post: ");
        postOrderWithStackTravel(root);
        System.out.println();

        System.out.println("rotate left travel: ");
        rotateLeftTravel(root);
        System.out.println();
    }

    /**
     * 逆时针旋转90度
     */
    private static void rotateLeftTravel(TreeNode root) {
        doRotateLeftTravel(root, 0);
    }

    private static void doRotateLeftTravel(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        doRotateLeftTravel(root.getRight(), depth + 1);

        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
        System.out.println(root.getData());

        doRotateLeftTravel(root.getLeft(), depth + 1);
    }

    private static void postOrderWithStackTravel(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            if (!stack.isEmpty()) {
                node = stack.peek().getRight(); // 如果有右孩子，还不能出栈
                if (node == null) { // 没有右孩子，才可以出栈并访问

                    TreeNode pre = stack.pop();
                    System.out.print(pre.getData());

                    while (!stack.isEmpty() && stack.peek().getRight() == pre) {
                        pre = stack.pop();
                        System.out.print(pre.getData());
                    }

                    if (!stack.isEmpty()) {
                        node = stack.peek().getRight();
                    }
                }
            }
        }
    }

    private static void midOrderWithStackTravle(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.getData());
                node = node.getRight();
            }
        }
    }

    /**
     * 函数递归实际上利用了函数栈，我们可以自己弄一个栈
     * 根节点，   访问，入栈，获取左孩子，   访问，入栈，获取左孩子，    访问，入栈，如果没有左孩子，出栈，获取右孩子
     *   根节点出栈并获取右孩子入栈，获取栈顶右孩子并访问，获取其左孩子为空，右孩子出栈并获取其右孩子为空
     */
    private static void preOrderTravelWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.empty()) {
            while (node != null) {
                System.out.print(node.getData());
                stack.push(node);
                node = node.getLeft();
            }
            if(!stack.empty()) {
                node = stack.pop();
                node = node.getRight();
            }
        }
    }

    /**
     * 层序遍历，从上到下，从左到右，可以用队列实现。
     * 假定一棵二叉树只有：3个节点：一个根节点和左右两个孩子，那么根先入队列，左孩子入队列，右孩子入队列，依次出队列，这样可以实现层序遍历。
     * 假定一棵二叉树只有：4个节点：一个根节点和左右两个孩子以及一个左孩子的左孩子，那么根先入队列，左孩子入队列，右孩子入队列，
     *  接下来，应该是左孩子的左孩子入队列，但是首先得取得左孩子，怎么取的左孩子呢，左孩子前面是根节点，那么根节点出队列并访问，
     *  然后队头就是左孩子了，取左孩子的左孩子和右孩子（这里没有右孩子）入队列，然后左孩子出队列并访问，这时队头就是右孩子了，取右孩子的
     *  左右孩子入队列（这里没有），然后右孩子出队并访问，这时对手就是左孩子的左孩子了，，，直到队列为空
     *
     *  修正：
     *  假定一棵二叉树只有：3个节点：一个根节点和左右两个孩子，那么根节点入队，接下来应该是根节点的左右孩子依次入队，为了取得根节点的左右孩子，
     *    需要让根节点出队并访问，然后获取其左右孩子并入队。接着继续出队，出队的是左孩子并访问，再出队的是右孩子并访问。
     */
    private static void levelOrderTravel(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.getData());
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
    }

    private static void postOrderTravel(TreeNode root) {
        if (root != null) {
            postOrderTravel(root.getLeft());
            postOrderTravel(root.getRight());
            System.out.print(root.getData());
        }
    }

    private static void midOrderTravel(TreeNode root) {
        if (root != null) {
            midOrderTravel(root.getLeft());
            System.out.print(root.getData());
            midOrderTravel(root.getRight());
        }
    }

    private static void preOrderTravel(TreeNode root) {
        if (root != null) {
            System.out.print(root.getData());
            preOrderTravel(root.getLeft());
            preOrderTravel(root.getRight());
        }
    }
}
