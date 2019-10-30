package greedy;

public class Solution {
    public int balancedStringSplit(String s) {
        int res = 0;
        int curb = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'L') curb += 1;
            else curb -= 1;
            if (curb == 0) res += 1;
        }
        return res;
    }

    public static void main(String[] args) {

    }

    /*1217. Play with Chips
     * 数学,奇偶性parity的妙用*/
    public int minCostToMoveChips(int[] chips) {
        int c1 = 0;
        int c2 = 0;
        for (int num : chips) {
            if (num % 2 == 0) {
                c2 += 1;
            } else {
                c1 += 1;
            }
        }
        return Math.min(c1, c2);
    }

    /*贪心,回溯*/
    public boolean isMatch(String s, String p) {
        int[][] memo = new int[s.length() + 1][p.length() + 1];
        return isMatch1(s, p, 0, 0);
    }

    /*备忘录优化*/
    private boolean isMatch1(String s, String p, int si, int pi, int[][] memo) {
        if (pi == p.length() && si == s.length()) {
            memo[si][pi] = 1;
        } else if (pi == p.length()) {
            memo[si][pi] = 2;
        } else if (memo[si][pi] == 0) {
            char pc = p.charAt(pi);
            if (pc == '?') {
                if(si < s.length()&&isMatch1(s, p, si + 1, pi + 1, memo)){
                    memo[si][pi]=1;
                }else{
                    memo[si][pi]=2;
                }
            } else if (pc == '*') {
                /*结束匹配或继续匹配下一个*/
                if(isMatch1(s, p, si, pi + 1, memo) ||
                        (si < s.length() && isMatch1(s, p, si + 1, pi, memo))){
                    memo[si][pi]=1;
                }else{
                    memo[si][pi]=2;
                }
            } else {
                if (si < s.length() && pc == s.charAt(si)) {
                    if(isMatch1(s, p, si + 1, pi + 1, memo)){
                        memo[si][pi]=1;
                    }else{
                        memo[si][pi]=2;
                    }
                } else
                    memo[si][pi] = 2;
            }
        }
        return memo[si][pi] == 1;
    }

    /*普通回溯,超时*/
    private boolean isMatch1(String s,String p,int si,int pi){
        if(pi==p.length()&&si==s.length()){
            return true;
        }
        if(pi==p.length()){
            return false;
        }
        char pc=p.charAt(pi);
        if(pc=='?'){
            return si<s.length()&&isMatch1(s,p,si+1,pi+1);
        }else if(pc=='*'){
            /*结束匹配或继续匹配下一个*/
            return isMatch1(s,p,si,pi+1)||(si<s.length()&& isMatch1(s,p,si+1,pi));
        }else{
            if(si<s.length()&&pc==s.charAt(si)){
                return isMatch1(s,p,si+1,pi+1);
            }else
                return false;
        }
    }

    /*动态规划,自底向上去解决*/
    public boolean isMatch2(String s, String p) {
        boolean[][] data=new boolean[s.length()+1][p.length()+1];
        /*初始化边界*/
        data[s.length()][p.length()]=true;
        for(int i=0;i<s.length();i++){
            data[i][p.length()]=false;
        }
        for(int j=p.length()-1;j>=0;j--){
            data[s.length()][j]=p.charAt(j)=='*'&&data[s.length()][j+1];
        }
        for(int i=s.length()-1;i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                char pc=p.charAt(j);
                char sc=s.charAt(i);
                if(pc=='?'){
                    data[i][j]=data[i+1][j+1];
                }else if(pc=='*'){
                    data[i][j]=data[i+1][j]||data[i][j+1];
                }else
                    data[i][j]=pc==sc&&data[i+1][j+1];
            }
        }
        return data[0][0];
    }
}
