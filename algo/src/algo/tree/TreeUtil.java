package algo.tree;

import java.util.List;

public abstract class TreeUtil {

    public static TreeNode preCreate(List<Integer> treeNodes) {
        if (treeNodes == null || treeNodes.isEmpty()) {
            return null;
        }

        Integer data = treeNodes.remove(0);
        if (data == null) {
            return null;
        }

        TreeNode left = preCreate(treeNodes);
        TreeNode right = preCreate(treeNodes);
        return new TreeNode(left, right, data);
    }
}
