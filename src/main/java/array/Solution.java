package array;

public class Solution {
    /**
     * 153 Find Minimum in Rotated Sorted Array
     * 元素无重复，思路简单
     * @param nums
     * @return
     */
    public int findMin(int[] nums){
        return _findMin(nums,0,nums.length-1);
    }


    private int _findMin(int[] nums,int p,int r){
//        如果两个或不足两个
        if( (r - p) < 2)
            return Math.min(nums[p], nums[r]);
        int q = (r - p)/2 + p;
        if (nums[p] < nums[q])
            return nums[q] < nums[r]?nums[p]:_findMin(nums,q + 1, r);
        else
            return _findMin(nums,p, q);
    }

    /**
     * 154. Find Minimum in Rotated Sorted Array II
     * 元素有重复，分情况讨论
     */

    public int findMinII(int[] nums){
        return findMinII(nums,0,nums.length-1);
    }

    private int findMinII(int[] nums, int p, int r) {
        if((r-p)<2) return Math.min(nums[p],nums[r]);
        int q=(p+r)/2;
        if(nums[p]==nums[q]&&nums[q]==nums[r]){
            int tmpMin=nums[p];
            for(int i=p+1;i<=r;i++) tmpMin=Math.min(tmpMin,nums[i]);
            return tmpMin;
        }else{
            if(nums[p]<=nums[q]){
                if(nums[q]<=nums[r]) return nums[p];
                else return findMinII(nums,q+1,r);
            }else
                return findMinII(nums,p,q);
        }
    }

}
