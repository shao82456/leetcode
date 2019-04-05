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
}