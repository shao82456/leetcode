package list;

import org.junit.Test;

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
}