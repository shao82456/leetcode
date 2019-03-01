package offer;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution solution=new Solution();

    @Test
    public void replaceSpace() {
        String ori="We Are Happy";
        String res=solution.replaceSpace(new StringBuffer(ori));
        System.out.println(res);
    }

    @Test
    public void printListFromTailToHead() {
        ListNode h=new ListNode(1);
        h.next=new ListNode(2);
        h.next.next=new ListNode(3);

        ArrayList<Integer> res=solution.printListFromTailToHead(h);
        System.out.println(res);
    }

    @Test
    public void reConstructBinaryTree() {
        int[] pre={1,2,4,7,3,5,6,8};
        int[] in={4,7,2,1,5,3,8,6};

        TreeNode res=solution.reConstructBinaryTree(pre,in);
        TestTree.preorder(res);
        TestTree.inorder(res);
    }
}