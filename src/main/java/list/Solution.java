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


}
