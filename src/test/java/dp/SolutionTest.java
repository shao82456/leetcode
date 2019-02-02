package dp;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution solution=new Solution();
    @Test
    public void lengthOfLIS1() {
        int[] input={10,9,2,5,3,7,101,18};
        int res=solution.lengthOfLIS1(input);
        System.out.println(res);
    }

    @Test
    public void lengthOfLIS2() {
        int[] input={10,9,2,5,3,7,101,18};
        int[] input1={2,1,4,3};
        int res=solution.lengthOfLIS2(input1);
        System.out.println(res);
    }

    @Test
    public void rob() {
        int[] input1={1,2,3,1,12,3,3,4};
        int[] input2={1,2,3,1};
        int res=solution.rob(input1);
        System.out.println(res);
    }

    @Test
    public void robII() {
        int[] input1={2,3,2};
        int[] input2={1,2,3,1};
        int res=solution.robII(input2);
        System.out.println(res);
    }

    @Test
    public void uniquePathsIII() {
        int[][] grid=
                {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        int res=solution.uniquePathsIII(grid);
        System.out.println(res);
    }
}