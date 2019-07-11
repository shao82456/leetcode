package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import array.Solution.Interval;

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

    public void merge() {
        List<Interval> in = new ArrayList<>();
        in.add(new Interval(2, 3));
        in.add(new Interval(2, 2));
        in.add(new Interval(3, 3));
        in.add(new Interval(1, 3));
        in.add(new Interval(5, 7));
        in.add(new Interval(2, 2));
        in.add(new Interval(4, 6));

        List<Interval> res = sol.merge(in);
        for (Interval interval : in) {
            System.out.println(interval.start + " " + interval.end);
        }
    }

    @Test
    public void spiralOrder() {
        int[][] map={
                { 1, 2, 3,4 },
                { 5, 6, 7,8 },
                { 8, 9,10,11 }};
        System.out.println(sol.spiralOrder(map));
    }

    @Test
    public void convert() {
        String input1="PAYPALISHIRING";
        String res1=sol.convert(input1,3);
        String res2=sol.convert(input1,4);
        assertEquals("PAHNAPLSIIGYIR",res1);
        assertEquals("PINALSIGYAHRPI",res2);
    }
}