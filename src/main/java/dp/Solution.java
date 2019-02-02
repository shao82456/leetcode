package dp;

import offer.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

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


    /**
     * 980. Unique Paths III
     * dfs版本
     */

    int res980=0;
    int left=0;
    final int[][] direct={{1,0},{-1,0},{0,-1},{0,1}};
    int sti=-1,stj=-1;
    int edi=-1,edj=-1;
    public int uniquePathsIII(int[][] grid) {
        if(null==grid||grid.length==0||grid[0].length==0)
            return 0;

        boolean[][] canVisit=new boolean[grid.length][grid[0].length];

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]!=-1) {
                    canVisit[i][j] = true;
                    left++;
                }
                if(grid[i][j]==1){
                    sti=i;
                    stj=j;
                }
                if(grid[i][j]==2){
                    edi=i;
                    edj=j;
                }
            }
        }
        res980=0;
        dfs(grid,direct,canVisit,sti,stj);
        return res980;
    }

    private void dfs(int[][] grid, int[][] direct, boolean[][] canVisit, int curi, int curj) {
        System.out.println(curi+","+curj+":"+left);
        if(curi==edi&&curj==edj) {
            if(left==1) res980++;
            return;
        }
        for(int d=0;d<4;d++){
            int nexti=curi+direct[d][0];
            int nextj=curj+direct[d][1];
            if(nexti>=0&&nexti<grid.length&&nextj>=0&&nextj<grid[0].length&&
                    canVisit[nexti][nextj]) {

                canVisit[curi][curj]=false;
                left--;
                dfs(grid, direct, canVisit, nexti, nextj);
                canVisit[curi][curj]=true;
                left++;
            }
        }
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

    /**
     * 32 最长有效括号
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if(s.length()<2) return 0;
        LinkedList<Integer> stk=new LinkedList<>();
        stk.add(-1);
        int res=0,cur=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stk.push(i);
            }else if(!stk.isEmpty()){
                if(stk.peek()==-1)
                    res=Math.max(res,i+1);
                else {
                    int border = stk.pop();
                    res = Math.max(res, i - border);
                }
            }
        }
        return res;
    }

    /**
     * 198. House Robber
     * @param nums
     * @return
     */
    int robtest=0;
    public int rob(int[] nums) {
        int[] memo=new int[nums.length+1];
        Arrays.fill(memo,-1);
        memo[memo.length-1]=0;
        memo[memo.length-2]=nums[nums.length-1];

        int res=rob1(nums,0,memo);
        System.out.println("total "+robtest);
        return res;
    }

    /***
     * 从此分析得出结论，采用记忆化搜索是可以将2^N的复杂度将为N,但是实际将降到多少还是看题目
     * @param nums
     * @param i
     * @param memo
     * @return
     */
    private int rob1(int[] nums, int i,int[] memo) {
        robtest++;
        System.out.println(i);
        if(memo[i]==-1){
            int rob=nums[i];
            if(memo[i+2]!=-1) rob+=memo[i+2];
            else rob+=rob1(nums,i+2,memo);
            int nrob;
            if(memo[i+1]!=-1) nrob=memo[i+1];
            else nrob=rob1(nums,i+1,memo);
            memo[i]= Math.max(rob,nrob);
        }
        return memo[i];
    }

    /**
     * dp解决
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if(null==nums||nums.length==0)
            return 0;

        int[] dp=new int[nums.length+1];
        dp[dp.length-1]=0;
        dp[dp.length-2]=nums[nums.length-1];

        for(int i=dp.length-3;i>=0;i--)
            dp[i]=Math.max(dp[i+1],dp[i+2]+nums[i]);
        return dp[0];
    }

    /**
     * 213. House Robber II
     * 这个题一开始我是想取出除了首位的部分，进行普通dp,求得dp[1]~dp[n-2]的值，然后考虑三种情况
     * 1 不选头和尾
     * 2 选首，res=dp[0+2]+nums[i]
     * 3 选尾, res=....，此时突然发现这个想法不行，除非再计算一个dp[1]~dp[n-1]的值
     * 后来突然想到，直接分别求去首的普通dp和去尾的普通dp，然后取最大的即可
     * 去尾的普通dp包括了抢第一个房子和不抢第一个房子的情况，但是肯定没有抢最后一个
     * 去首的普通dp包括了抢最后一个和不抢最后一个，但是肯定不会抢第一个
     * 于是二者的最大值就可能是
     * 1.抢了第一个，不抢最后一个
     * 2.不抢一个，但抢最后一个
     * 3.由于房子排列的原因，两个都没抢。
     * @param nums
     * @return
     */
    public int robII(int[] nums) {
        if(null==nums||nums.length==0)
            return 0;
        if(nums.length==1)
            return nums[0];

        int[] num1=Arrays.copyOfRange(nums,0,nums.length-1);
        int[] num2=Arrays.copyOfRange(nums,1,nums.length);
        return Math.max(rob2(num1),rob2(num2));

    }

    /***
     * 337. House Robber III
     * @param root
     * @return
     */
    public int robIII(TreeNode root){
        if(root==null) return 0;
        HashMap<TreeNode,Integer> memo=new HashMap<>();
        return rob1(root,memo);
    }

    public int rob1(TreeNode root, HashMap<TreeNode,Integer> memo) {
        if(root==null) return 0;
        if(!memo.containsKey(root)){
            int go = root.val;
            if (null != root.left) {
                go += rob1(root.left.left, memo);
                go += rob1(root.left.right, memo);
            }
            if (null != root.right) {
                go += rob1(root.right.left, memo);
                go += rob1(root.right.right, memo);
            }

            int notgo = 0;
            notgo += rob1(root.left, memo);
            notgo += rob1(root.right, memo);

            memo.put(root,Math.max(go,notgo));
        }
        return memo.get(root);
    }
}

