package tracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    /**
     * 22生成括号对
     *
     * @param n
     * @return
     */
    static List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        res.clear();
        char[] cur = new char[2 * n];
        generateParenthesis(cur, 0);
        return res;
    }

    private void generateParenthesis(char[] cur, int i) {
        if (i == cur.length) {
            if (check(cur))
                res.add(new String(cur));
            return;
        }
        cur[i] = '(';
        generateParenthesis(cur, i + 1);
        cur[i] = ')';
        generateParenthesis(cur, i + 1);
    }

    public boolean check(char[] chs) {
        int balance = 0;
        for (char c : chs) {
            if (c == '(') balance += 1;
            else
                balance -= 1;
            if (balance < 0)
                return false;
        }
        return balance == 0;
    }

    /**
     * 22生成括号对,回溯减zhi
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesisII(int n) {
        res.clear();
        char[] chs = new char[n * 2];
        generateParenthesis1(chs, 0, n, 0);
        return res;
    }

    private void generateParenthesis1(char[] cur, int i, int n, int ln) {
        if (i == cur.length) {
            res.add(new String(cur));
            return;
        }

        if (ln < n) {
            cur[i] = '(';
            generateParenthesis1(cur, i + 1, n, ln + 1);
        }
        if (i - ln < ln) {
            cur[i] = ')';
            generateParenthesis1(cur, i + 1, n, ln);
        }
    }

    /***
     * 60. Permutation Sequence
     */

    public String getPermutation(int n, int k) {
        List<String> res=permutation(n);
        Collections.sort(res);
        return res.get(k-1);
    }

    public List<String> permutation(int n){
        char[] nums=new char[n];
        for(int i=1;i<=n;i++) nums[i-1]=(char) (i+48);
        List<String> res=new ArrayList<>();
        permutation1(0,nums,res);
        return res;
    }


    public List<String> permutationII(int n){
        char[] nums=new char[n];
        for(int i=1;i<=n;i++) nums[i-1]=(char) (i+48);

        List<String> res=new ArrayList<>();
        char[] chs=new char[n];
        boolean[] used=new boolean[n];
        permutation2(0,nums,chs,used,res);
        return res;
    }

    private void permutation1(int i, char[] nums,List<String> res) {
        if(i==nums.length) {
            res.add(new String(nums));
            return;
        }
        for(int j=i;j<nums.length;j++){
            swap(nums,i,j);
            permutation1(i+1,nums,res);
            swap(nums,j,i);
        }
    }

    private void permutation2(int k,char[] ele,char[] chs,boolean[] used,List<String> res){
        if(k==chs.length){
            res.add(new String(chs));
            return;
        }
        for(int i=0;i<ele.length;i++){
            if(!used[i]){
                chs[k]=ele[i];
                used[i]=true;
                permutation2(k+1,ele,chs,used,res);
                used[i]=false;
            }
        }
    }
    private void swap(char[] chs,int i,int j){
        char temp=chs[i];
        chs[i]=chs[j];
        chs[j]=temp;
    }

    /**
     * 10. Regular Expression Matching
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        boolean first_match=
            (s.length()>0&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.'));
        if(p.length()>1&&p.charAt(1)=='*')
            return (isMatch(s,p.substring(2))||(first_match&&isMatch(s.substring(1),p)));
        else
            return first_match&&isMatch(s.substring(1),p.substring(1));
    }

    public boolean isMatchII(String s, String p) {
        byte[][] memo=new byte[s.length()+1][p.length()+1];
        return isMatch(s,p,0,0,memo);
    }

    private boolean isMatch(String s, String p, int i, int j, byte[][] memo) {
        if (memo[i][j] == 0) {
            boolean res;
            if(j == p.length())
                res= i == s.length();
            else{
                boolean first_match =
                        (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
                if (j + 1 < p.length() && p.charAt(j + 1) == '*')
                    res= isMatch(s, p, i, j + 2, memo)
                            || (first_match && isMatch(s, p, i + 1, j, memo));
                else
                    res=first_match
                            && isMatch(s, p, i + 1, j + 1, memo);
            }
            memo[i][j]= (byte) (res?1:2);
        }
        return memo[i][j]==1;
    }


}
