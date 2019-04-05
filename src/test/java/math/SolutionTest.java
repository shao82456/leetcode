package math;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution solution=new Solution();

    @Test
    public void intToRoman() {
        int[] ts={3,4,9,58,1994};
        for(int input:ts)
            System.out.println(solution.intToRoman(input));
    }

    @Test
    public void isHappy() {
        int in=19;
        boolean res=solution.isHappy(in);
        System.out.println(res);
    }

    @Test
    public void countPrimes() {
        int in=10;
        int res=solution.countPrimes(in);
        System.out.println(res);
    }
}