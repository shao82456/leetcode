package offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Solution {
    /**
     * #1 二维数组的查找
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int [][] array) {
        if(null==array||array.length==0)
            return false;
        int curI=0,curJ=array[0].length-1;
        while(curI<array.length&&curJ>=0){
            int cur=array[curI][curJ];
            if(cur==target)
                return true;
            else if (cur>target)
                curJ--;
            else
                curI++;
        }
        return false;
    }

    /**
     * #2 字符串替换
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        if(null==str||str.length()==0) return "";
        StringBuffer newStrBuf=new StringBuffer();
        int j=0;
        while(j<str.length()){
            if(str.charAt(j)!=' ')
                newStrBuf.append(str.charAt(j));
            else
                newStrBuf.append("%20");
            j+=1;
        }
        return newStrBuf.toString();
    }

    /**
     * #3 倒序打印链表
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res=new ArrayList<>();
        printList(res, listNode);
        return res;
    }

    private void printList(ArrayList<Integer> res,ListNode root){
        if(root==null) return;
        else{
            printList(res,root.next);
            res.add(root.val);
        }
    }


    //例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    int k=0;
    HashMap<Integer,Integer> inmap=new HashMap<>();

    /**
     * 4重建二叉树
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(null==pre||pre.length==0)
            return null;
        k=0;
        inmap.clear();
        for(int i=0;i<in.length;i++) inmap.put(in[i],i);

        return reConstructBinaryTree(pre,in,0,in.length-1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int[] in, int p, int r) {
        TreeNode root=new TreeNode(pre[k++]);
        Integer rootPos=inmap.get(root.val);
        if(rootPos==null) throw new RuntimeException();

        if(rootPos>p)
            root.left=reConstructBinaryTree(pre,in,p,rootPos-1);
        if(rootPos<r)
            root.right=reConstructBinaryTree(pre,in,rootPos+1,r);
        return root;
    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 5用两个栈实现队列
     * @param node
     */
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.isEmpty()){
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    /**
     * 6旋转有序数组寻找最小值
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int [] array) {
        return _minNumberInRotateArray(array,0,array.length-1);
    }

    /***
     * p=q,q=r　遍历
     * p=q,q>r (q+1,r)
     * p=q,q<r q
     * ---------
     * p>q,q<r (p,q)
     * p>q,q=r (p,q)
     * p>q,q>r 不可能
     * --------
     * p<q,q<r q
     * p<q,q=r q
     * p<q,q>r (q+1,r)
     * ---------
     * @param arr
     * @param p
     * @param r
     * @return
     */
    public int _minNumberInRotateArray(int[] arr,int p,int r){
        if(r-p<=1) return Math.min(arr[p],arr[r]);
        int q=(p+r)/2;

        int pq=arr[p]<arr[q]?-1:(arr[p]==arr[q]?0:1);
        int qr=arr[q]<arr[r]?-1:(arr[q]==arr[r]?0:1);

        if(pq==0){
            if(qr==0){
                int min=arr[p];
                for(int i=p+1;i<=r;i++) min=Math.min(min,arr[i]);
                return min;
            }
            if(qr==1)
                return _minNumberInRotateArray(arr,q+1,r);
            else
                return arr[p];
        }

        if(pq==1)
            return _minNumberInRotateArray(arr,p,q);

        if(qr==-1||qr==0)
            return arr[q];
        else
            return _minNumberInRotateArray(arr,q+1,r);
    }

    /***
     * 6.清晰简明版
     */

    public int _minNumberInRotateArray1(int[] arr,int p,int r){
        if(r-p<=1) return Math.min(arr[p],arr[r]);
        int q=(p+r)/2;

        int pq=arr[p]<arr[q]?-1:(arr[p]==arr[q]?0:1);
        int qr=arr[q]<arr[r]?-1:(arr[q]==arr[r]?0:1);

        if(pq==0&&qr==0){
            int min=arr[p];
            for(int i=p+1;i<=r;i++) min=Math.min(min,arr[i]);
            return min;

        }
        if((pq==-1||pq==0)&&(qr==-1||qr==0))
            return arr[p]; //p<=q,q<=r

        if(pq==-1||pq==0) //p<=q,q>r
            return _minNumberInRotateArray1(arr,q+1,r);

        return _minNumberInRotateArray1(arr,p,q);//p>q
    }
}
