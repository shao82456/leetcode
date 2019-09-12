package graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution sol=new Solution();
    @Test
    public void findJudge() {
        int N=0;
        int[][] trust=new int[][]{};
        System.out.println(sol.findJudge(N, trust));
    }
}