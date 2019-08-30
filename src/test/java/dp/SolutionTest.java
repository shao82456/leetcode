package dp;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void lengthOfLIS1() {
        int[] input = {10, 9, 2, 5, 3, 7, 101, 18};
        int res = solution.lengthOfLIS1(input);
        System.out.println(res);
    }

    @Test
    public void lengthOfLIS2() {
        int[] input = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] input1 = {2, 1, 4, 3};
        int res = solution.lengthOfLIS2(input1);
        System.out.println(res);
    }

    @Test
    public void uniquePaths() {
        int m = 1, n = 9;
        System.out.println(solution.uniquePaths2(m, n));
    }

    @Test
    public void uniquePathsII() {
        int[][] obstacleGrid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(solution.uniquePathsII(obstacleGrid));
    }

    @Test
    public void uniquePathsIII() {
        int[][] grid =
                {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        int res = solution.uniquePathsIII(grid);
        System.out.println(res);
    }

    @Test
    public void minPathSum() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(solution.minPathSum(grid));
    }

    @Test
    public void rob() {
        int[] input1 = {1, 2, 3, 1, 12, 3, 3, 4};
        int[] input2 = {1, 2, 3, 1};
        int res = solution.rob(input1);
        System.out.println(res);
    }

    @Test
    public void robII() {
        int[] input1 = {2, 3, 2};
        int[] input2 = {1, 2, 3, 1};
        int res = solution.robII(input2);
        System.out.println(res);
    }

    @Test
    public void numSquares() {
        int n = 6;
        int res = solution.numSquares(n);
        System.out.println(res);
    }

    @Test
    public void mincostTickets(){
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        int res=solution.mincostTickets2(days,costs);
        System.out.println(res);
    }

    @Test
    public void canPartitionKSubsets() {
        int[] nums=new int[]{5,2,5,5,5,5,5,5,5,5,5,5,5,5,5,3};
        int k=4;
        System.out.println(solution.canPartitionKSubsets(nums, k));
    }
}