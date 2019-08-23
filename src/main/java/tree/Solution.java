package tree;

public class Solution {


    int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        longestUnivaluePath1(root);
        return res;
    }

    public int longestUnivaluePath1(TreeNode root) {
        if (root == null) return 0;
        int left = longestUnivaluePath1(root.left);
        int right = longestUnivaluePath1(root.right);

        int fromLeft=0;
        int fromRight=0;
        if (root.left != null && root.left.val == root.val) {
            fromLeft+=left+1;
        }
        if (root.right != null && root.right.val == root.val) {
            fromRight+=right+1;
        }
        res = Math.max(res, fromLeft+fromRight);
        return Math.max(fromLeft, fromRight);
    }
}
