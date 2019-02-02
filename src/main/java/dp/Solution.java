package dp;

import java.util.Arrays;

public class Solution {
    /***
     * #300 dp经典解法
     * 初始状态dp[i]=1，表示每个数都是一个子序列
     * dp[i]表示从首元素到当前元素i，存在的最长上升子序列的长度
     * 对于j=[0,i-1],如果nums[i]>nums[j];则dp[i]=dp[j]+1，dp[i]在遍历过程中变化，保留最大的
     * 最后从i=[0,n)中，选dp[i]最大的
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if(nums.length<2) return nums.length;
        int[] dp=new int[nums.length];
        for(int i=0;i<dp.length;i++)
            dp[i]=1;

        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j])
                    dp[i]=Integer.max(dp[i],dp[j]+1);
            }
        }

        int res=1;
        for(int len:dp)
            res=Integer.max(res,len);
        return res;
    }

    /***
     * #300 暴力回溯
     */
    public int lengthOfLIS1(int[] nums){
        return lengthOfLIS1(nums,Integer.MIN_VALUE,0);
    }

    /***
     * 求curpos开始，上一个元素是prev，到数组的最后为止，其中的最长上升子序列的长度
     * 递归树接近于完全二叉树，但是由于nums[curpos]不一定大于prev，故在某些节点只有一个分支
     * @param nums
     * @param prev
     * @param curpos
     * @return
     */
    private int lengthOfLIS1(int[] nums,int prev,int curpos){
        if(curpos==nums.length) return 0;

        int taken=-1,notaken;
        if(nums[curpos]>prev)
            taken=1+lengthOfLIS1(nums,nums[curpos],curpos+1);
        notaken=lengthOfLIS1(nums,prev,curpos+1);
        return Integer.max(taken,notaken);
    }

    /***
     * #300 记忆化搜索优化回溯
     */
    public int lengthOfLIS2(int[] nums){
        int[][] memo=new int[nums.length+1][nums.length];
        for(int[] l:memo)
            Arrays.fill(l,-1);
        return lengthOfLIS2(nums,-1,0,memo);
    }

    private int lengthOfLIS2(int[] nums, int prepos, int curpos, int[][] memo) {
        if(curpos==nums.length) return 0;
        if(memo[prepos+1][curpos]==-1){
            int taken=-1,notaken;
            if(prepos<0||nums[curpos]>nums[prepos])
                taken=1+lengthOfLIS1(nums,nums[curpos],curpos+1);

            notaken=lengthOfLIS1(nums,prepos,curpos+1);
            memo[prepos+1][curpos]=Integer.max(taken,notaken);
        }
        return memo[prepos+1][curpos];
    }

    /**
     * 62. Unique Paths
     */
    public int uniquePaths(int m, int n) {
        return uniquePaths(m,n,0,0);
    }

    private int uniquePaths(int m, int n, int i, int j) {
        if(i==m-1&&j==n-1) return 1;
        int all=0;
        if(i+1<m)
            all+=uniquePaths(m,n,i+1,j);
        if(j+1<n)
            all+=uniquePaths(m,n,i,j+1);
        return all;
    }

    /**
     * memory search
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths1(int m, int n) {
        int[][] memo=new int[m][n];
        for(int[] l:memo)
            Arrays.fill(l,-1);
        memo[m-1][n-1]=1;

        return uniquePaths1(m,n,0,0,memo);
    }

    private int uniquePaths1(int m, int n, int i, int j,int[][] memo) {
        if(memo[i][j]==-1) {
            int all=0;
            if(i+1<m){
                if(memo[i+1][j]==-1)
                    memo[i+1][j]=uniquePaths1(m,n,i+1,j,memo);
                all+=memo[i+1][j];
            }
            if(j+1<n){
                if(memo[i][j+1]==-1)
                    memo[i][j+1]=uniquePaths1(m,n,i,j+1,memo);
                all+=memo[i][j+1];
            }
            memo[i][j]=all;
        }
        return memo[i][j];
    }

    public int uniquePaths2(int m, int n) {
        if(m==0||n==0) return 0;
        int[][] dp=new int[m][n];
        Arrays.fill(dp[m-1],1);
        for(int i=0;i<m;i++)
            dp[i][n-1]=1;

        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--)
                dp[i][j]=dp[i+1][j]+dp[i][j+1];
        }

        return dp[0][0];
    }

    /***
     * 63. Unique Paths II
     * ruguo local[i][j] has obstacle,then we get from local[i][j] we have no path to arrive final place
     */

    public int uniquePathsII(int[][] obstacleGrid) {
        int m=obstacleGrid.length,n=obstacleGrid[0].length;
        int[][] dp=new int[m][n];
        dp[m-1][n-1]=obstacleGrid[m-1][n-1]==1?0:1;

        for(int j=n-2;j>=0;j--) {
            if(obstacleGrid[m-1][j]==0)
                dp[m-1][j] = dp[m-1][j+1];
        }

        for(int i=m-2;i>=0;i--){
            if(obstacleGrid[i][n-1]==0)
                dp[i][n-1]=dp[i+1][n-1];
        }

        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--)
                if(obstacleGrid[i][j]==0)
                    dp[i][j]=dp[i+1][j]+dp[i][j+1];
        }

        return dp[0][0];
    }

    /***
     * 64 Minimum path sum
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int[][] dp=new int[m][n];
        dp[m-1][n-1]=grid[m-1][n-1];

        for(int j=n-2;j>=0;j--) {
            dp[m-1][j] = grid[m-1][j]+dp[m-1][j+1];
        }

        for(int i=m-2;i>=0;i--){
            dp[i][n-1]=grid[i][n-1]+dp[i+1][n-1];
        }

        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--)
                dp[i][j]=grid[i][j]+Math.min(dp[i+1][j],dp[i][j+1]);
        }

        return dp[0][0];
    }
}

