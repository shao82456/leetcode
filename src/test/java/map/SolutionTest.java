package map;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution solution=new Solution();
    @Test
    public void isNStraightHand() {
        int[] input={1,2,3,4,5};
        boolean res=solution.isNStraightHand(input,4);
        System.out.println(res);
    }
}