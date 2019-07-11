package list;

import java.util.List;

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
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

}
