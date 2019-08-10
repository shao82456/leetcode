package tracking;

import java.util.*;

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


    public List<String> permutation(int n) {
        char[] nums = new char[n];
        for (int i = 1; i <= n; i++) nums[i - 1] = (char) (i + 48);
        List<String> res = new ArrayList<>();
        permutation1(0, nums, res);
        return res;
    }


    public List<String> permutationII(int n) {
        char[] nums = new char[n];
        for (int i = 1; i <= n; i++) nums[i - 1] = (char) (i + 48);

        List<String> res = new ArrayList<>();
        char[] chs = new char[n];
        boolean[] used = new boolean[n];
        permutation2(0, nums, chs, used, res);
        return res;
    }

    private void permutation1(int i, char[] nums, List<String> res) {
        if (i == nums.length) {
            res.add(new String(nums));
            return;
        }
        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            permutation1(i + 1, nums, res);
            swap(nums, j, i);
        }
    }

    private void permutation2(int k, char[] ele, char[] chs, boolean[] used, List<String> res) {
        if (k == chs.length) {
            res.add(new String(chs));
            return;
        }
        for (int i = 0; i < ele.length; i++) {
            if (!used[i]) {
                chs[k] = ele[i];
                used[i] = true;
                permutation2(k + 1, ele, chs, used, res);
                used[i] = false;
            }
        }
    }

    private void swap(char[] chs, int i, int j) {
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }

    /**
     * 10. Regular Expression Matching
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match =
                (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
        if (p.length() > 1 && p.charAt(1) == '*')
            return (isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p)));
        else
            return first_match && isMatch(s.substring(1), p.substring(1));
    }

    public boolean isMatchII(String s, String p) {
        byte[][] memo = new byte[s.length() + 1][p.length() + 1];
        return isMatch(s, p, 0, 0, memo);
    }

    private boolean isMatch(String s, String p, int i, int j, byte[][] memo) {
        if (memo[i][j] == 0) {
            boolean res;
            if (j == p.length())
                res = i == s.length();
            else {
                boolean first_match =
                        (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
                if (j + 1 < p.length() && p.charAt(j + 1) == '*')
                    res = isMatch(s, p, i, j + 2, memo)
                            || (first_match && isMatch(s, p, i + 1, j, memo));
                else
                    res = first_match
                            && isMatch(s, p, i + 1, j + 1, memo);
            }
            memo[i][j] = (byte) (res ? 1 : 2);
        }
        return memo[i][j] == 1;
    }

    /***
     * 37. Sudoku Solver
     * 采用空间换时间的思路，申请9(row)+9(column)+9(sub-boxes)个Set
     * @param board
     */
    public void solveSudoku(char[][] board) {
        List<Set<Integer>> puted = new ArrayList<>();
        for (int i = 0; i < 27; i++) puted.add(new HashSet<>());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - 48;
                    puted.get(j).add(num);
                    puted.get(9 + i).add(num);
                    int pos = (i / 3) + (j / 3) * 3;
                    puted.get(18 + pos).add(num);
                }
            }
        }
        char[][] res = new char[board.length][board[0].length];
        solveSudoku(board, 0, 0, puted, res);
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                board[i][j] = res[i][j];
    }

    private void solveSudoku(char[][] board, int x, int y, List<Set<Integer>> puted, char[][] res) {
        //按照先右后下的顺序
        if (x == 9) {
            x = 0;
            y += 1;
        }
        //所有格子放置完成
        if (y == 9) {
            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++)
                    res[i][j] = board[i][j];
            return;
        }
        if (board[x][y] != '.')
            solveSudoku(board, x + 1, y, puted, res);
        else {
            //计算小方格位置
            int pos = (x / 3) + (y / 3) * 3;
            //尝试从1-9选择数字放入
            int numToPut;
            for (int num = 1; num <= 9; num++) {
                //判断行
                if (puted.get(y).contains(num))
                    continue;
                //判断列
                if (puted.get(9 + x).contains(num))
                    continue;
                //判断小方格
                if (puted.get(18 + pos).contains(num))
                    continue;
                numToPut = num;
                //放入
                board[x][y] = (char) (48 + numToPut);
                puted.get(y).add(numToPut);
                puted.get(9 + x).add(numToPut);
                puted.get(18 + pos).add(numToPut);
                solveSudoku(board, x + 1, y, puted, res);
                //返回
                board[x][y] = '.';
                puted.get(y).remove(numToPut);
                puted.get(9 + x).remove(numToPut);
                puted.get(18 + pos).remove(numToPut);
            }

        }
    }


    /**
     * 44. Wildcard Matching
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch44(String s, String p) {
        //预先处理连续p中连续的*
        StringBuilder preHandledP = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && (i + 1) < p.length() && p.charAt(i + 1) == '*') {
                continue;
            } else {
                preHandledP.append(p.charAt(i));
            }
        }
//        System.out.println(s+":"+preHandledP.toString());
        return matchWild(s, preHandledP.toString());
//        return false;
    }


    private boolean matchWild(String s, String p) {
        //两个均匹配完时匹配成功
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (p.length() == 0) {
            return false;
        }
        //如果p的首字符为*,选择继续匹配或结束匹配，如果s为空，则*只能结束匹配
        if (p.charAt(0) == '*') {
            if (s.length() == 0)
                return matchWild(s, p.substring(1));
            else {
                return matchWild(s.substring(1), p) || matchWild(s, p.substring(1));
            }
        }
        //s为空，匹配失败 （执行到这里则p当前字符不是*）
        if (s.length() == 0) {
            return false;
        }
        //此时只剩下普通字符匹配或问号
        if (p.charAt(0) == '?' || p.charAt(0) == s.charAt(0)) {
            return matchWild(s.substring(1), p.substring(1));
        } else {
            return false;
        }
    }

    /**
     * 51. N-Queens
     */
    List<List<String>> res51 = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        boolean[][] located = new boolean[n][n];
        res.clear();
        if (n == 0) return res51;

        put(n, located, 0);
        return res51;
    }

    private void put(int n, boolean[][] located, int sti) {
        if (sti == n) {
            addOne(located);
        }
        // TODO Auto-generated method stub
        for (int j = 0; j < n; j++) {
            if (availColumn(located, sti, j) && availX(located, sti, j)) {
                located[sti][j] = true;
                put(n, located, sti + 1);
                located[sti][j] = false;
            }
        }
    }

    private void addOne(boolean[][] located) {
        // TODO Auto-generated method stub
        List<String> one = new ArrayList<>();
        for (int i = 0; i < located.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < located[0].length; j++)
                if (located[i][j]) sb.append('Q');
                else sb.append('.');
            one.add(sb.toString());
        }
        res51.add(one);
    }

    private boolean availX(boolean[][] located, int sti, int j) {
        // TODO Auto-generated method stub
        int leftj = j - 1;
        for (int i = sti - 1; i >= 0; i--) {
            if (leftj < 0) break;
            if (located[i][leftj--]) return false;
        }

        int rightj = j + 1;
        for (int i = sti - 1; i >= 0; i--) {
            if (rightj >= located[0].length) break;
            if (located[i][rightj++]) return false;
        }
        return true;
    }

    private boolean availColumn(boolean[][] located, int sti, int j) {
        // TODO Auto-generated method stub
        for (int i = 0; i < sti; i++)
            if (located[i][j]) return false;
        return true;
    }


    /**
     * 60. Permutation Sequence
     * 求n个元素全排列的第k个排列，如1,2,3的第3个排列是213
     * 关键在于分组
     */
    public String getPermutation(int n, int k) {
        //准备
        int[] jc = jiecheng(n);
        List<Integer> items = new ArrayList<>();
        for (int i = 1; i <= n; i++) items.add(i);

        //计算k在每个分组的序号，直到最后一轮分组长度为1
        int tmpK = -1;//当前k值
        int tmpL = -1;//分组长度
        int tmpA = -1;//所在组的序号
        char[] res = new char[n];//保存结果
        //循环n-1次，选出n-1个数
        for (int i = 1; i < n; i++) {
            tmpK = i == 1 ? k - 1 : tmpK - tmpL * tmpA;
            tmpL = jc[n - i];
            tmpA = tmpK / tmpL;
            res[i - 1] = (char) (items.remove(tmpA).intValue() + 48);
        }
        //选最后一个数,此时items中也应该只剩一个数
        res[n - 1] = (char) (items.remove(0).intValue() + 48);
        return new String(res);
    }

    //给定n，返回arr,其中arr[i]表示(n-i)!
    public int[] jiecheng(int n) {
        int[] res = new int[n + 1];
        res[1] = 1;
        for (int i = 2; i <= n; i++)
            res[i] = res[i - 1] * i;
        return res;
    }

    /**
     * 89. Gray Code
     * 每个数到视为一个节点，则每个点有n个路径，2^n个节点组成一个图
     * dfs搜索出一条路径即可
     */
    public List<Integer> grayCode(int n) {
        Set<Integer> knownNeighbors = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        knownNeighbors.add(0);
        res.add(0);
        int curNode = 0;
        while ((curNode = getUnknownNeighbor(curNode, n, knownNeighbors)) != -1) {
            System.out.println(curNode);
            knownNeighbors.add(curNode);
            res.add(curNode);
        }
        return res;
    }

    public int getUnknownNeighbor(int node, int n, Set<Integer> knownNeighbors) {
        //第i位不同
        for (int i = 0; i < n; i++) {
            int neighbor = node ^ (1 << i);
            if (!knownNeighbors.contains(neighbor))
                return neighbor;
        }
        return -1;
    }

    /**
     * 90. Subsets II
     * 求子集，额外记录每个元素的重复数目即可
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        HashMap<Integer, Integer> duplicatedTime = new HashMap<>();
        for (int num : nums) {
            duplicatedTime.put(num, duplicatedTime.getOrDefault(num, 0) + 1);
        }
        List<Integer> items = new ArrayList(duplicatedTime.keySet());
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> one = new ArrayList<>();
        subsetsWithDup(0, items, duplicatedTime, one, res);
        return res;
    }

    private void subsetsWithDup(int i, List<Integer> items, HashMap<Integer, Integer> duplicatedTime, ArrayList<Integer> one, List<List<Integer>> res) {
        if (i == items.size()) {
            res.add((List<Integer>) one.clone());
            return;
        }
        int item = items.get(i);
        //j表示选item选几次，j=0表示不选，最多选完所有重复的item
        for (int j = 0; j <= duplicatedTime.get(item); j++) {
            //添加item
            for (int k = 0; k < j; k++)
                one.add(item);
            subsetsWithDup(i + 1, items, duplicatedTime, one, res);
            //回溯
            for (int k = 0; k < j; k++)
                one.remove(one.size() - 1);
        }
    }

    /*
     * 77. Combinations
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        search(n, 1, k, arr, res);
        return res;
    }

    private void search(int n, int i, int k, ArrayList<Integer> arr, List<List<Integer>> res) {
        if (k == 0) {
            res.add((List<Integer>) arr.clone());
            return;
        }
        //如果剩下的元素不够选了，返回
        if ((n - i + 1) < k)
            return;

        search(n, i + 1, k, arr, res);
        arr.add(i);
        search(n, i + 1, k - 1, arr, res);
        arr.remove(arr.size() - 1);
    }

    public boolean isAdditiveNumber(String num) {
        int l = num.length();
        int maxl = num.length() / 2;
        for (int n1 = 1; n1 <= maxl; n1++) {
            //如果选的一个数的后一位是0，由于不能有前导0，只尝试第二个数字为0的情况
            if (num.charAt(n1) == '0') {
                if (isAdditiveStr(num.substring(0, n1), "0", num.substring(n1 + 1))) {
                    return true;
                }
                continue;
            }
            for (int n2 = 1; n2 <= maxl && (n1 + n2) < l; n2++) {
                //如果第二个数字后面是0，由于不能有前导0，故只可能是0+0=0,输入只能为"000"
                if (num.charAt(n1 + n2) == '0') {
                    if (num.equals("000"))
                        return true;
                    continue;
                }
                if (isAdditiveStr(num.substring(0, n1), num.substring(n1, n1 + n2), num.substring(n1 + n2))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAdditiveStr(String num1, String num2, String str) {
        String num3 = addStr(num1, num2);
        while (str.startsWith(num3)) {
            str = str.substring(num3.length());
            if (str.length() == 0)
                return true;
            num1 = num2;
            num2 = num3;
            num3 = addStr(num1, num2);
        }
        return false;
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> one = new ArrayList<>();
        combinationSum3(1, k, n, res, one);
        return res;
    }

    private void combinationSum3(int i, int k, int left, List<List<Integer>> res, ArrayList<Integer> one) {
        if (k == 0) {
            if (left == 0) res.add((List<Integer>) one.clone());
            return;
        }
        if (i == 10) {
            return;
        } else {
            one.add(i);
            combinationSum3(i + 1, k - 1, left - i, res, one);
            one.remove(one.size() - 1);
            combinationSum3(i + 1, k, left, res, one);
        }
    }

    public String addStr(String s1, String s2) {
        char[] res = new char[Integer.max(s1.length(), s2.length()) + 1];
        int extra = 0;
        int p1 = s1.length() - 1;
        int p2 = s2.length() - 1;
        int i = res.length - 1;
        while (p1 >= 0 || p2 >= 0) {
            int a = p1 >= 0 ? s1.charAt(p1) - '0' : 0;
            int b = p2 >= 0 ? s2.charAt(p2) - '0' : 0;
            int c = (extra + a + b) % 10;
            extra = (extra + a + b) / 10;
            res[i] = (char) (c + 48);
            i -= 1;
            p1 -= 1;
            p2 -= 1;
        }
        if (extra == 0)
            return new String(res, 1, res.length - 1);
        else {
            res[0] = (char) (extra + 48);
            return new String(res);
        }
    }


    public int countNumbersWithUniqueDigits(int n) {
        return count(0, n, 0);
    }

    private int count(int i, int n, int used) {
        if (i == n) {
            return 1;
        }
        int levelSum = 0;

        for (int num = 0; num <= 9; num++) {
            if (num == 0 && used == 0) {
                levelSum += count(i + 1, n, used);
            } else if (!isUsed(num, used)) {
                int tempUsed = use(num, used);
                levelSum += count(i + 1, n, tempUsed);
            }
        }
        return levelSum;
    }

    public boolean isUsed(int i, int used) {
        if ((used & (1 << i)) == 0)
            return false;
        else
            return true;
    }

    public int use(int i, int used) {
        used = used | (1 << i);
        return used;
    }

    public int unUse(int i, int used) {
        return used & (~(1 << i));
    }

    public boolean judge(List<String> seq) {
        int a = Integer.valueOf(seq.get(0));
        int b = Integer.valueOf(seq.get(1));
        for (int i = 2; i < seq.size(); i++) {
            int c = Integer.valueOf(seq.get(i));
            if ((a + b) != c)
                return false;
            a = b;
            b = c;
        }
        return true;
    }

    /**
     * 401. Binary Watch
     * 回溯寻找所有解，然后对每个解进行翻译
     */
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        readBinaryWatch(0, num, res, one);
        return res;
    }

    public void readBinaryWatch(int i, int num, List<String> res, List<Integer> one) {
        if (num == 0) {
            attempAdd(one, res);
            return;
        }
        if (i == 10)
            return;
        one.add(i);
        readBinaryWatch(i + 1, num - 1, res, one);
        one.remove(one.size() - 1);
        readBinaryWatch(i + 1, num, res, one);
    }

    public void attempAdd(List<Integer> one, List<String> res) {
        int h = 0;
        int m = 0;
        for (Integer num : one) {
            if (num < 4)
                h += Math.pow(2, num);
            else
                m += Math.pow(2, num - 4);
        }
        if (h < 12 && m < 60) {
            String timeStr = h + ":" + String.format("%02d", m);
            res.add(timeStr);
        }
    }

    /**
     * 784. Letter Case Permutation
     *
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        char[] cs = S.toCharArray();
        letterCasePermutation(0, cs, res);
        return res;
    }

    private void letterCasePermutation(int i, char[] cs, List<String> res) {
        if (i == cs.length) {
            res.add(new String(cs));
            return;
        }
        if (Character.isDigit(cs[i])) {
            letterCasePermutation(i + 1, cs, res);
            return;
        }
        char origin = cs[i];
        cs[i] = Character.toLowerCase(origin);
        letterCasePermutation(i + 1, cs, res);
        cs[i] = Character.toUpperCase(origin);
        letterCasePermutation(i + 1, cs, res);
    }

    public int countArrangement(int N) {
        List<Integer> one = new ArrayList<>();
        int res = countArrangement(1, N, 0, one);
        return res;
    }

    private int countArrangement(int i, int n, int numUsed, List<Integer> one) {
        if (i == n + 1) {
            System.out.println(one);
            return 1;
        }
        int total = 0;
        for (int j = 1; j <= n; j++) {
            if (!isUsed(j, numUsed) && canUse(i, j)) {
                int numUsed1 = use(j, numUsed);
                one.add(j);
                total += countArrangement(i + 1, n, numUsed1, one);
                one.remove(one.size() - 1);
            }
        }
        return total;
    }

    public boolean canUse(int i, int j) {
        return i % j == 0 || j % i == 0;
    }

    public List<Integer> splitIntoFibonacci(String S) {
        int maxl = Integer.min(S.length() / 2,10);
        String s1, s2;
        List<Integer> one;
        for (int n1 = 1; n1 <= maxl; n1++) {
            s1 = S.substring(0, n1);
            if (S.charAt(n1) == '0') {
                if ((one = isFibonacci(s1, "0", S.substring(n1 + 1))) != null)
                    return one;
            } else {
                for (int n2 = 1; n2 <= maxl&&(S.length()-n1-n2)>=Integer.max(n1,n2); n2++) {
                    if (S.charAt(n1 + n2) == '0' && S.equals("000"))
                        return Arrays.asList(0, 0, 0);
                    else {
                        s2 = S.substring(n1, n1 + n2);
                        if ((one = isFibonacci(s1, s2, S.substring(n1 + n2))) != null)
                            return one;
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    public List<Integer> isFibonacci(String s1, String s2, String left) {
        if(s1.length()==10&&s1.compareTo("2147483647")>=0)
            return null;
        if(s2.length()==10&&s2.compareTo("2147483647")>=0)
            return null;
        List<Integer> res = new ArrayList<>();
        int a = Integer.valueOf(s1);
        int b = Integer.valueOf(s2);
        res.add(a);
        res.add(b);
        long sum = a + b;
        String sumStr = Long.toString(sum);
        while (left.startsWith(sumStr)) {
            if(sum>0x7fffffff)
                return null;
            a = b;
            b = (int) sum;
            left = left.substring(sumStr.length());
            res.add((int) sum);
            sum = a + b;
            sumStr = Long.toString(sum);
        }
        if (left.length() == 0) {
            return res;
        } else {
            return null;
        }
    }
}
