package greedy;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    Solution sol = new Solution();

    @Test
    public void balancedStringSplit() {
        String s = "L";
        int res = sol.balancedStringSplit(s);
        Assert.assertEquals(1,res);
    }

    @Test
    public void minCostToMoveChips() {
        int[] chips={2,2,2,3,3};
        int res=sol.minCostToMoveChips(chips);
        System.out.println(res);
    }

    @Test
    public void isMatch() {
       String s= "acdcb";
        String p="a*c?b";
        System.out.println(sol.isMatch(s,p));
    }

    @Test
    public void isMatch2() {
        String s="adceb";
        String p="*a*b";
        System.out.println(sol.isMatch2(s,p));
    }
}