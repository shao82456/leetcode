package list;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

public class Solution {

    public static ListNode makeFromArr(int[] arr){
        ListNode dummy=new ListNode(-1);
        ListNode cur=dummy;
        for(int a:arr){
            cur.next=new ListNode(a);
            cur=cur.next;
        }
        return dummy.next;
    }

    public static String makeString(ListNode head){
        StringBuilder res=new StringBuilder();
        if(head==null) return "";
        while(head.next!=null){
            res.append(head.val+"->");
            head=head.next;
        }
        res.append(head.val);
        return res.toString();
    }
    /**
     * 删除链表倒数第n个元素
     * 关键思路：1.dummyhead应对删除首节点，2.双指针满足一遍遍历
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode first=dummy,second=dummy;
        for(int i=1;i<=n+1;i++){
            if(first==null)
                return null;
            else
                first=first.next;
        }
        while(first!=null){
            first=first.next;
            second=second.next;
        }
        second.next=second.next.next;
        return dummy.next;
    }

    /**
     * 24. Swap Nodes in Pairs
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode pairNext=swapPairs(head.next.next);
        ListNode next=head.next;
        head.next=pairNext;
        next.next=head;
        return next;
    }

    static class Container<T>{
        public T data;
        public Container(T data) {
            this.data = data;
        }
    }

    /**
     * 添加两个一样长的链表
     * @param p1
     * @param p2
     * @return
     */
    ListNode addSameLong(ListNode p1,ListNode p2){
        if(p1==null||p2==null)
            return p1==null?p2:p1;

        ListNode first=null;
        Container<ListNode> firstNode=new Container<>(first);
        int sig=addSameLong(p1,p2,firstNode);

        if(sig==0)  //无进位
            return firstNode.data;
        else{   //进位
            ListNode head=new ListNode(1);
            head.next=firstNode.data;
            return head;
        }
    }

    private int addSameLong(ListNode p1, ListNode p2, Container<ListNode> firstNode) {
        int a=0;
        if(p1.next!=null&&p2.next!=null){
            a=addSameLong(p1.next,p2.next,firstNode);
        }
        int sum=a+p1.val+p2.val;
        ListNode t=new ListNode(sum%10);
        t.next=firstNode.data;
        firstNode.data=t;
        return sum/10;
    }

    public ListNode add(ListNode p1,ListNode p2){
        if(p1==null||p2==null)
            return p1==null?p2:p1;

        ListNode cur1=p1,cur2=p2;
        while(p1!=null&&p2!=null){
            cur1=cur1.next;
            cur2=cur2.next;
        }

        ListNode s,l,extraP; //短链表头，长链表头，长链表多出部分
        if(cur1==null){
            s=p1;
            l=p2;
            extraP=cur2;
        }else{
            s=p2;
            l=p1;
            extraP=cur1;
        }

        // 下面内容不对，对于长出的部分，不能直接使用，需要进行拷贝
//        ListNode beforePartLast=null;
//        while(extraP!=null){
//            extraP=extraP.next;
//            beforePartLast=l;
//            l=l.next;
//        }
//
//        ListNode first=null;
//        Container<ListNode> firstNode=new Container<>(first);
//        int sig=addSameLong(s,l,firstNode);
//
//        if(sig==0){ //无进位
//            if(beforePartLast==null) //链表一样长
//                return firstNode.data;
//            else{
//                beforePartLast.
//            }
//        }
//        else{   //进位
//            ListNode head=new ListNode(1);
//            head.next=firstNode.data;
//            return head;
//        }
        return null;
    }

    /**
     * 445. Add Two Numbers II
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ArrayList<ListNode> stk1=new ArrayList<>();
        while (l1!=null){
            stk1.add(l1);
            l1=l1.next;
        }
        ArrayList<ListNode> stk2=new ArrayList<>();
        while (l2!=null){
            stk2.add(l2);
            l2=l2.next;
        }
        int extra=0;
        ListNode last=null;
        while(!stk1.isEmpty()||!stk2.isEmpty()){
            int sum=extra;
            if(!stk1.isEmpty()){
                sum+=stk1.remove(stk1.size()-1).val;
            }
            if(!stk2.isEmpty()){
                sum+=stk2.remove(stk2.size()-1).val;
            }
            extra=sum/10;
            ListNode node=new ListNode(sum%10);
            node.next=last;
            last=node;
        }
        if(extra!=0) {
            ListNode node=new ListNode(extra);
            node.next=last;
            last=node;
        }
        return last;
    }

    /**
     * 725. Split Linked List in Parts
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] heads=new ListNode[k];
        int total=0;
        ListNode cur=root;
        while(cur!=null){
            total+=1;
            cur=cur.next;
        }
        int extra=total%k;
        int nodes=total/k;
        cur=root;
        ListNode last=null;
        for(int j=0;j<k;j++){
            heads[j]=cur;
            for(int t=0;t<nodes;t++) {
                last=cur;
                cur = cur.next;
            }
            if(extra!=0){
                last=cur;
                cur=cur.next;
                extra-=1;
            }
            if(last!=null) last.next=null;
        }
        return heads;
    }

    /**
     *430. Flatten a Multilevel Doubly Linked List
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        if(head==null) return null;
        flatten1(head);
        return head;
    }

    /*将链表head转换为双链表，返回最后一个转换成的双链表的最后一个节点*/
    public Node flatten1(@NotNull Node head) {
        if (head.child != null) {
            Node child=head.child;
            head.child=null;

            Node next=head.next;
            head.next=child;
            child.prev=head;
            Node childLast=flatten1(child);

            childLast.next=next;
            if(next!=null)
                next.prev=childLast;

            return childLast.next==null?childLast:flatten1(childLast.next)k;
        }else if(head.next!=null){
            return flatten1(head.next);
        }else
            return head;
    }
}
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};