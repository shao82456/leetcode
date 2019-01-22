package slideWindow;

public class Solution {
    //zhong
    public int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0) return 0;
        if(s.length()==1) return 1;
        int res=0;
        int[] times=new int[128];
        int p=0,r=p-1;
        while(r+1<s.length()){
            if(times[s.charAt(r+1)]==0) times[s.charAt(++r)]=1;
            else times[s.charAt(p++)]--;
            res=Math.max(res,r-p+1);
        }
        return res;
    }
}
