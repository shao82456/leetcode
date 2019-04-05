package tree;

import datastruct.ArrayList;
import offer.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.NoSuchElementException;

public class BSTIterator {
    TreeNode root;
    ArrayList<TreeNode> stk=new ArrayList<>();
    TreeNode last;

    public BSTIterator(TreeNode root) {
        this.root=root;
        TreeNode cur=root;
        while (cur!=null){
            stk.add(cur);
            cur=cur.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        if(last==null){
            if(stk.isEmpty()) throw new NoSuchElementException();
            else{
                last=stk.remove(stk.size()-1);
                return last.val;
            }
        }
        TreeNode cur=last.left;
        if(stk.isEmpty()||last!=stk.get(stk.size()-1)){
            cur=last.right;
        }
        return 0;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(last==null)
            return stk.size()!=0;
        return !(stk.size()==0&&last.right==null);
    }
}