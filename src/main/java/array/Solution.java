package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    /**
     * 5. Longest Palindromic Substring
     */

    public String longestPalindrome(String s) {
        if(s==null||s.length()<2)
            return s;
        String res="";
        int maxlen=0;
        char[] chs=s.toCharArray();

        //判断奇数个
        for(int i=1;i<=chs.length-2;i++){
            int j=1;
            for(;i-j>=0&&i+j<chs.length&&chs[i+j]==chs[i-j];j++);
            j--;//j退到正确值
            if(maxlen<(j*2+1)){
                maxlen=j*2+1;
                res=s.substring(i-j,i+j+1);
            }
        }

        //判断偶数个
        for(int i=0;i<=chs.length-2;i++){
            int j=1;
            for(;i-j+1>=0&&i+j<chs.length&&chs[i+j]==chs[i-j+1];j++);
            j--;//j退到正确值
            if(maxlen<(j*2)){
                maxlen=j*2;
                res=s.substring(i-j+1,i+j+1);
            }
        }

        return maxlen==0?s.charAt(0)+"":res;
    }

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

    public static class Interval {
      int end;
      int start;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size()==0) {
            return intervals;
        }else{
            intervals.sort(new Comparator<Interval>() {
                @Override
                public int compare(Interval o1, Interval o2) {
                    return o1.start-o2.start;
                }
            });
            Interval cur=intervals.get(0);
            int pos=0;
            while(pos!=intervals.size()-1){
                Interval next=intervals.get(pos+1);
                if(cur.end>=next.start) {
                    cur.end = Integer.max(cur.end,next.end);
                    intervals.remove(next);
                }else {
                    pos++;
                    cur=next;
                }

            }
            return intervals;
        }
    }

    /**
     * 顺时针打印矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res=new ArrayList<>();
        if(matrix==null||matrix.length==0)
            return res;
        int NR=matrix.length;
        int NC=matrix[0].length;
        boolean[][] visited=new boolean[NR+2][NC+2];
        Arrays.fill(visited[0],true);
        Arrays.fill(visited[NR+1],true);
        for(int i=0;i<NR+2;i++)
            visited[i][0]=true;
        for(int i=0;i<NR+2;i++)
            visited[i][NC+1]=true;


        int[] direcR={0,1,0,-1};
        int[] direcC={1,0,-1,0};

        int curX=1,curY=1;
        int di=0;
        for(int i=0;i<NR*NC;i++){
            res.add(matrix[curX-1][curY-1]);
            visited[curX][curY]=true;
            int nextX=curX+direcR[di];
            int nextY=curY+direcC[di];

            if(visited[nextX][nextY]){
                di=(di+1)%4;
                nextX=curX+direcR[di];
                nextY=curY+direcC[di];
            }
            curX=nextX;
            curY=nextY;
        }
        return res;
    }
}
