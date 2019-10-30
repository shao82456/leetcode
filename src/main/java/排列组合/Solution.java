package 排列组合;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<Integer>> res = sol.combinaDup(new int[]{1,2,2,3,3},3);
        System.out.println(res);
    }

    /*无重复元素的全排列*/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        f1(0, nums, res);
        return res;
    }

    private void f1(int i, final int[] nums, List<List<Integer>> res) {
        if (i == nums.length) {
            //记录当前解
            List<Integer> one = new ArrayList<>();
            for (int num : nums) {
                one.add(num);
            }
            res.add(one);
        } else {
            /*当前元素和它后面的每个元素交换，
             *交换后进入下一个元素的交换，返回后再换回来*/
            for (int j = i; j < nums.length; j++) {
                swap(nums, i, j);
                f1(i + 1, nums, res);
                swap(nums, i, j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        // TODO Auto-generated method stub
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }

    /*有重复元素的全排列*/

    /*无重复元素的组合问题,从n个物品选m个*/
    int f3(int m, int n) {
        if (n < m) return 0;
        if (n == m) return 1;
        if (m == 1) return n;
        return f3(m, n - 1) + f3(m - 1, n - 1);
    }

    /*多值组合问题,从n个物品选m个,n中有重复*/
    List<List<Integer>> combinaDup(int[] nums, int m) {
        HashMap<Integer, Integer> data = new HashMap<>();
        for (int num : nums) {
            data.put(num, data.getOrDefault(num,0)+1);
        }
        Integer[] items=new Integer[0];
        Integer[] count=new Integer[0];

        items=data.keySet().toArray(items);
        count=data.values().toArray(count);

        List<List<Integer>> res=new ArrayList<>();
        ArrayList<Integer> one=new ArrayList<>();
        f4(items,count,0,m,one,res);
        return res;
    }

    void f4(Integer[] items,Integer[] count,int k,int goal,ArrayList<Integer> one,List<List<Integer>> res){
        if(goal==0){
            res.add((List<Integer>) one.clone());
            return;
        }
        int left=0;
        for(int i=k;i<count.length;i++){
            left+=count[i];
        }
        System.out.println(left);
        if(goal<=left){
            for(int j=0;j<=count[k]&&j<=goal;j++){
                for(int i=0;i<j;i++) {
                    one.add(items[k]);
                }
                count[k]-=j;
                f4(items,count,k+1,goal-j,one,res);
                for(int i=0;i<j;i++) {
                    one.remove(one.size()-1);
                }
                count[k]+=j;
            }
        }
    }



    int f(int n, int k, int goal) {
        if (goal == 1) return n - k + 1;
        if (n - k + 1 < goal) return 0;
        return f(n, k + 1, goal) + f(n, k + 1, goal - 1);
    }


}
