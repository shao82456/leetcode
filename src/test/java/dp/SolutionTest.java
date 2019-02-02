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
    public void uniquePaths(){
        int m=1,n=9;
        System.out.println(solution.uniquePaths2(m,n));
    }

    @Test
    public void uniquePathsII(){
        int[][] obstacleGrid={{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(solution.uniquePathsII(obstacleGrid));
    }

    @Test
    public void minPathSum(){
        int[][] grid={{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(solution.minPathSum(grid));
    }
}