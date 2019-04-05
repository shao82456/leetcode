package tree;

import offer.TreeNode;

import static org.junit.Assert.*;

public class BSTIteratorTest {
    public static void main(String[] args) {
        int[] arr={7,3,-1,-1,15,9,-1,-1,20,-1,-1};
        int[] arr1={7,3,15,-1,-1,9,20};
        int[] arr2={3,1,4,-1,2};

        TreeNode root=TreeNode.buildTreeByLevel(arr2);
        System.out.println(TreeNode.preview(root));
        BSTIterator bstIterator=new BSTIterator(root);
        while (bstIterator.hasNext()) {
            int a = bstIterator.next();
            System.out.println(a);
        }
    }

}