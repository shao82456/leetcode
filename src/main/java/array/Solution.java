package array;

public class Solution {

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

    public int findMin(int[] nums){
        return _findMin(nums,0,nums.length-1);
    }

    public int findMin2(int[] nums) {
        int p=0,r=nums.length - 1;
        while (true) {
            int q = (r - p) / 2 + p;
            if (nums[p] <= nums[q] && nums[ q]<=nums[r])
                return nums[p];
            else if (nums[ p]<nums[q])
                p = q + 1;
            else
                r = q;
        }
    }
}
