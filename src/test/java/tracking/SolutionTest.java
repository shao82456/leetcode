package tracking;

import org.junit.Test;

import java.util.Arrays;
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

    @Test
    public void solveSudoku() {
        char[][] board={
            {'5','3','.', '.','7','.',  '.','.','.'},
            {'6','.','.', '1','9','5',  '.','.','.'},
            {'.','9','8', '.','.','.',  '.','6','.'},
            {'8','.','.', '.','6','.',  '.','.','3'},
            {'4','.','.', '8','.','3',  '.','.','1'},
            {'7','.','.', '.','2','.',  '.','.','6'},
            {'.','6','.', '.','.','.',  '2','8','.'},
            {'.','.','.', '4','1','9',  '.','.','5'},
            {'.','.','.', '.','8','.',  '.','7','9'}
        };
        sol.solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }

    @Test
    public void isMatch44() {
        String s="babbbbaabababaabbababaababaabbaabababbaaababbababaaaaaabbabaaaabababbabbababbbaaaababbbabbbbbbbbbbaabbb";
        String p="b**bb**a**bba*b**a*bbb**aba***babbb*aa****aabb*bbb***a";
        System.out.println(sol.isMatch44(s,p));
    }

    @Test
    public void solveNQueens() {
        int input=4;
        List<List<String>> res=sol.solveNQueens(input);
        for(List<String> solution:res){
            System.out.println("solution");
            for(String line:solution)
                System.out.println(line);
        }
    }

    @Test
    public void jiecheng() {
        int res=4;
        char c= (char) (res+48);
        System.out.println(c);
    }

    @Test
    public void getPermutation() {
        String res=sol.getPermutation(4,23);
        System.out.println(res);
    }

    @Test
    public void getUnknownNeighbor() {
        int node=0;
        for(int i=0;i<3;i++){
            int neighbor=node^(1<<i);
            System.out.println(Integer.toBinaryString(neighbor));
        }
    }

    @Test
    public void grayCode() {
        int n=0;
        List<Integer> res=sol.grayCode(n);
        System.out.println(res);
    }

    @Test
    public void subsetsWithDup() {
        List<List<Integer>> res=sol.subsetsWithDup(new int[]{});
        System.out.println(res);
    }
}