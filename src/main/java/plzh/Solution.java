package plzh;

public class Solution {
//  public List<List<Integer>> permute(int[] nums) {
//    List<List<Integer>> res=new ArrayList<>();
//    //边界检查
//    if(nums==null) return res;
//    //flag[i]=0表示nums[i]未参与排列
//    int[] flag=new int[nums.length];
//
//    //
//    ArrayList<Integer> list=new ArrayList<>();
//
//    for(int i=0;i<nums.length;i++){
//      //选第i个位置
//      for(int j=0;j<nums.length;j++){
//        if
//      }
//      if(flag[i]==0)
//    }
////    ArrayList<Integer> list=new ArrayList<>();
//    f(nums,0,list);
//    return res;
//  }

  int f3(int m, int n) {
    if (n < m) return 0;
    if (n == m) return 1;
    if (m==1) return n;
    return f3(m, n - 1) + f3(m - 1, n - 1);
  }

  int f(int n, int k, int goal) {
    if (goal == 1) return n - k + 1;
    if (n - k + 1 < goal) return 0;
    return f(n, k + 1, goal) + f(n, k + 1, goal - 1);
  }
}
