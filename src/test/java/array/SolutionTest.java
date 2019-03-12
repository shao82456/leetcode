package array;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution sol=new Solution();
    TestCaseGenerater tcg=new TestCaseGenerater();


    @Test
    public void findMin() {
        long begin=System.currentTimeMillis();
        for(int j=0;j<1000;j++) {
            List<List<Integer>> data = tcg.getRotatedArrays();
            for (List<Integer> nums : data) {
                int[] a = new int[nums.size()];
                for (int i = 0; i < a.length; i++)
                    a[i] = nums.get(i);
                sol.findMin(a);
            }
        }
        long end=System.currentTimeMillis();
        System.out.println((end-begin)/1000.0);
    }

    @Test
    public void longestPalindrome() {
        String str="ac";
        String res=sol.longestPalindrome(str);
        System.out.println(res);
    }
}