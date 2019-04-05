package hashtable;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution solution=new Solution();
    @Test
    public void isIsomorphic() {
        String s="ab";
        String t="aa";
        boolean res=solution.isIsomorphic(s,t);
        System.out.println(res);
    }

    @Test
    public void containsDuplicateII() {
        int[] nums = {1,2,3,4,1};
        int  k = 3;
        boolean res=solution.containsDuplicateII(nums,k );
        System.out.println(res);
    }
}