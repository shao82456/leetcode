package tracking;

import org.junit.Test;

import java.util.Collections;
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

    @Test
    public void permutation() {
        int input=9;
        List<String> res=sol.permutation(input);
//        Collections.sort(res);
        System.out.println(res);
    }

    @Test
    public void permutationII() {
        int input=4;
        List<String> res=sol.permutationII(input);
//        Collections.sort(res);
        System.out.println(res);
    }

    @Test
    public void isMatch() {
        String input="aab";
        String p="c*a*b";
        System.out.println(sol.isMatchII(input,p));
    }
}