package tracking;

import java.util.ArrayList;
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
}
