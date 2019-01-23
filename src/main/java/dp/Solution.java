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

}
