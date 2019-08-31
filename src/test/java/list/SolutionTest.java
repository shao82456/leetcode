package list;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static list.Solution.*;
public class SolutionTest {
    Solution solution=new Solution();
    @Test
    public void swapPairs() {
        ListNode input=makeFromArr(new int[]{1,2,3,4,5});
        System.out.println(makeString(input));
        ListNode res=solution.swapPairs(input);
        System.out.println(makeString(res));
    }

    @Test
    public void addSameLong() {
        ListNode l1=makeFromArr(new int[]{2,4,3});
        ListNode l2=makeFromArr(new int[]{5,6,4});

        ListNode res=solution.addSameLong(l1,l2);
        System.out.println(makeString(res));
    }

    @Test
    public void addTwoNumbers() {
        ListNode l1=makeFromArr(new int[]{7,2,4,3});
        ListNode l2=makeFromArr(new int[]{5,6,4});
        ListNode res=solution.addTwoNumbers(l1,l2);
        String resStr=makeString(res);
        System.out.println(resStr);
    }

    @Test
    public void splitListToParts() {
        int[] arr=new int[]{1,2,3};
        ListNode root=makeFromArr(arr);
        ListNode[] res=solution.splitListToParts(root,5);
        for(ListNode node:res){
            System.out.println(node);
        }
    }

    @Test
    public void flatten1() {
        Node head1=new Node(1,null,null,null);
        Node last=head1;
        for(int i=2;i<=6;i++){
            last.next=new Node(i,last,null,null);
            last=last.next;
        }
        Node head2=new Node(7,null,null,null);
        last=head2;
        for(int i=8;i<=10;i++){
            last.next=new Node(i,last,null,null);
            last=last.next;
        }
        Node head3=new Node(11,null,null,null);
        last=head3;
        for(int i=12;i<=12;i++){
            last.next=new Node(i,last,null,null);
            last=last.next;
        }
        head1.next.next.child=head2;
        head2.next.child=head3;

        solution.flatten1(head1);
        while(head1!=null){
            if(head1.prev==null)
                System.out.print("null->");
            else
                System.out.print(head1.prev.val+"->");
            System.out.println(head1.val);
            head1=head1.next;
        }
    }

    @Test
    public void flatten2(){
        Node head1=new Node(1,null,null,null);

        Node head2=new Node(7,null,null,null);

        Node head3=new Node(11,null,null,null);
        head1.child=head2;
        head2.child=head3;

        solution.flatten1(head1);
        while(head1!=null){
            System.out.println(head1.val);
            head1=head1.next;
        }
    }

}