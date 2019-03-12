package tracking;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution sol=new Solution();
    @Test
    public void generateParenthesis() {
        int n=3;
        List<String> res=sol.generateParenthesisII(n);
        System.out.println(res);
    }


    @Test
    public void check() {
        String str="(((((";
        System.out.println(sol.check(str.toCharArray()));
    }
}