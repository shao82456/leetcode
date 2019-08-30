package tree;

import org.junit.Test;

import static org.junit.Assert.*;
import static tree.TreeNode.buildByPre;
import static tree.TreeNode.inview;

public class SolutionTest {
    Solution sol=new Solution();


    /*
              5
             / \
            4   5
           / \   \
          1   1   5
     */
    @Test
    public void longestUnivaluePath() {
        TreeNode root1=buildByPre(new int[]{5,4,1,-1,-1,1,-1,-1,5,-1,5,-1,-1});
        System.out.println(sol.longestUnivaluePath(root1));
    }

    @Test
    public void minDiffInBST() {
        TreeNode root=buildByPre(new int[]{8,2,1,-1,-1,4,-1,-1,9,-1,11,-1,-1});
        System.out.println(sol.minDiffInBST(root));
    }
}