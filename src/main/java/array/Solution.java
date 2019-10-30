package array;

import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode mkList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode dh = new ListNode(-1);
        ListNode cur = dh;
        for (int num : arr) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dh.next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int val = this.val;
        ListNode next = this.next;
        while (next != null) {
            sb.append(val + "->");
            val = next.val;
            next = next.next;
        }
        sb.append(val);
        return sb.toString();
    }
}

public class Solution {

    /**
     * 1. Two Sum
     * 给定一个数组和目标数，找出两个和为目标数的数的index
     * 普通方法两层for循环，O(n^2)
     * 使用hash快速查找，从i=0开始，先在集合中查找另一半（这样避免用了两次自己，如4+4=8),如果没有，则加入集合，当遍历到这个数的另一半时，就找到了一对
     * 此题假定这个组只有一对数符合条件，如果不止一对要找出所有解，依然可以用此方法，因为对一个数，最多只存在一个另一半的数
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (data.containsKey((target - nums[i]))) {
                int j = data.get(target - nums[i]);
                return i < j ? new int[]{i, j} : new int[]{j, i};
            } else {
                data.put(nums[i], i);
            }
        }
        return null;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dh = new ListNode(-1);
        ListNode cur = dh;
        int whelm = 0;
        for (ListNode p1 = l1, p2 = l2; p1 != null || p2 != null; ) {
            int sum = whelm;

            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }
            whelm = sum / 10;
            sum = sum % 10;

            cur.next = new ListNode(sum);
            cur = cur.next;
        }
        if (whelm != 0) cur.next = new ListNode(whelm);
        return dh.next;
    }

    /**
     * 6. ZigZag Conversion
     * 按zigzag顺序显示字符串时，需要记录横纵坐标信息，但是结果返回的是按行串起来的信息
     * ，故不需要维护每个字符具体是哪一列的，只需要保证先后顺序即可
     * 当对输入逐字符判断行信息时，将字符放入对应的行列表中即可，行列表内的元素顺序就是该行的元素的列顺序
     */
    public String convert(String s, int numRows) {
        //准备行列表
        List<List<Character>> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++)
            rows.add(new ArrayList<>());
        //放入对应行
        int idx = 0;
        while (idx < s.length()) {
            for (int i = 0; i < numRows && idx < s.length(); i++)
                rows.get(i).add(s.charAt(idx++));
            for (int i = numRows - 2; i >= 1 && idx < s.length(); i--)
                rows.get(i).add(s.charAt(idx++));
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++)
            rows.get(i).forEach(character -> res.append(character));
        return res.toString();
    }

    /**
     * 5. Longest Palindromic Substring
     */

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2)
            return s;
        String res = "";
        int maxlen = 0;
        char[] chs = s.toCharArray();

        //判断奇数个
        for (int i = 1; i <= chs.length - 2; i++) {
            int j = 1;
            for (; i - j >= 0 && i + j < chs.length && chs[i + j] == chs[i - j]; j++) ;
            j--;//j退到正确值
            if (maxlen < (j * 2 + 1)) {
                maxlen = j * 2 + 1;
                res = s.substring(i - j, i + j + 1);
            }
        }

        //判断偶数个
        for (int i = 0; i <= chs.length - 2; i++) {
            int j = 1;
            for (; i - j + 1 >= 0 && i + j < chs.length && chs[i + j] == chs[i - j + 1]; j++) ;
            j--;//j退到正确值
            if (maxlen < (j * 2)) {
                maxlen = j * 2;
                res = s.substring(i - j + 1, i + j + 1);
            }
        }

        return maxlen == 0 ? s.charAt(0) + "" : res;
    }

    /**
     * 153 Find Minimum in Rotated Sorted Array
     * 元素无重复，思路简单
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        return _findMin(nums, 0, nums.length - 1);
    }


    private int _findMin(int[] nums, int p, int r) {
//        如果两个或不足两个
        if ((r - p) < 2)
            return Math.min(nums[p], nums[r]);
        int q = (r - p) / 2 + p;
        if (nums[p] < nums[q])
            return nums[q] < nums[r] ? nums[p] : _findMin(nums, q + 1, r);
        else
            return _findMin(nums, p, q);
    }


    /**
     * 154. Find Minimum in Rotated Sorted Array II
     * 元素有重复，分情况讨论
     */

    public int findMinII(int[] nums) {
        return findMinII(nums, 0, nums.length - 1);
    }

    private int findMinII(int[] nums, int p, int r) {
        if ((r - p) < 2) return Math.min(nums[p], nums[r]);
        int q = (p + r) / 2;
        if (nums[p] == nums[q] && nums[q] == nums[r]) {
            int tmpMin = nums[p];
            for (int i = p + 1; i <= r; i++) tmpMin = Math.min(tmpMin, nums[i]);
            return tmpMin;
        } else {
            if (nums[p] <= nums[q]) {
                if (nums[q] <= nums[r]) return nums[p];
                else return findMinII(nums, q + 1, r);
            } else
                return findMinII(nums, p, q);
        }
    }

    public static class Interval {
        int end;
        int start;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() == 0) {
            return intervals;
        } else {
            intervals.sort(new Comparator<Interval>() {
                @Override
                public int compare(Interval o1, Interval o2) {
                    return o1.start - o2.start;
                }
            });
            Interval cur = intervals.get(0);
            int pos = 0;
            while (pos != intervals.size() - 1) {
                Interval next = intervals.get(pos + 1);
                if (cur.end >= next.start) {
                    cur.end = Integer.max(cur.end, next.end);
                    intervals.remove(next);
                } else {
                    pos++;
                    cur = next;
                }

            }
            return intervals;
        }
    }

    /**
     * 顺时针打印矩阵
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return res;
        int NR = matrix.length;
        int NC = matrix[0].length;
        boolean[][] visited = new boolean[NR + 2][NC + 2];
        Arrays.fill(visited[0], true);
        Arrays.fill(visited[NR + 1], true);
        for (int i = 0; i < NR + 2; i++)
            visited[i][0] = true;
        for (int i = 0; i < NR + 2; i++)
            visited[i][NC + 1] = true;


        int[] direcR = {0, 1, 0, -1};
        int[] direcC = {1, 0, -1, 0};

        int curX = 1, curY = 1;
        int di = 0;
        for (int i = 0; i < NR * NC; i++) {
            res.add(matrix[curX - 1][curY - 1]);
            visited[curX][curY] = true;
            int nextX = curX + direcR[di];
            int nextY = curY + direcC[di];

            if (visited[nextX][nextY]) {
                di = (di + 1) % 4;
                nextX = curX + direcR[di];
                nextY = curY + direcC[di];
            }
            curX = nextX;
            curY = nextY;
        }
        return res;
    }

    /**
     * 726. Number of Atoms
     * 和使用栈计算表达式一样，使用栈去嵌套的括号
     *
     * @param formula
     * @return
     */
    public String countOfAtoms(String formula) {
        List<String> stk = new ArrayList<>();
        List<String> tmpArr = new ArrayList<>();
        while (!formula.isEmpty()) {
            switch (formula.charAt(0)) {
                case '(':
                    stk.add("(");
                    formula = formula.substring(1);
                    break;
                case ')':
                    formula = formula.substring(1);
                    /*获取当前formula的倍数*/
                    int pos1 = getNumStrPos(formula);
                    int time = (pos1 >= 0) ? Integer.parseInt(formula.substring(0, pos1 + 1)) : 1;
                    /*更新formula*/
                    formula = formula.substring(pos1 + 1);
                    /*获取当前formula*/
                    while (!stk.get(stk.size() - 1).equals("(")) {
                        int n = time * Integer.parseInt(stk.remove(stk.size() - 1));
                        String atom = stk.remove(stk.size() - 1);
                        tmpArr.add(atom);
                        tmpArr.add(n + "");
                    }
                    /*删除"("*/
                    stk.remove(stk.size() - 1);
                    /*将去括号的结果压回栈*/
                    stk.addAll(tmpArr);
                    tmpArr.clear();
                    break;
                default:
                    /* 添加原子到栈中*/
                    int j = 1;
                    while (j < formula.length() && Character.isLowerCase(formula.charAt(j))) {
                        j++;
                    }
                    stk.add(formula.substring(0, j));
                    formula = formula.substring(j);
                    /*添加原子数到栈中*/
                    int pos2 = getNumStrPos(formula);
                    int time2 = (pos2 >= 0) ? Integer.parseInt(formula.substring(0, pos2 + 1)) : 1;
                    /*添加*/
                    stk.add(Integer.toString(time2));
                    formula = formula.substring(pos2 + 1);
                    break;
            }
        }
        return formatRes(stk);
    }

    private String formatRes(List<String> stk) {
        TreeMap<String, Integer> table = new TreeMap<>();
        while (!stk.isEmpty()) {
            int n = Integer.parseInt(stk.remove(stk.size() - 1));
            String atom = stk.remove(stk.size() - 1);
            table.put(atom, table.getOrDefault(atom, 0) + n);
        }
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Integer> entry : table.entrySet()) {
            res.append(entry.getKey());
            if (entry.getValue() != 1) {
                res.append(entry.getValue());
            }
        }
        return res.toString();
    }

    private int getNumStrPos(String s) {
        int j = -1;//j表示最后一个数字的下标,初始值为i
        while (j + 1 < s.length() && Character.isDigit(s.charAt(j + 1))) {
            j++;
        }
        return j;
    }

    /*42. Trapping Rain Water,暴力搜索*/
    public int trap(int[] height) {
        int res=0;
        for(int i=0;i<height.length;i++){
            int max_left=0;
            int max_right=0;
            for(int j=0;j<i;j++){
                max_left=Math.max(height[j],max_left);
            }
            for(int j=i+1;j<height.length;j++){
                max_right=Math.max(height[j],max_right);
            }
            res+=Math.max(Math.min(max_left,max_right)-height[i],0);
        }
        return res;
    }

    /*42. Trapping Rain Water,动态规划避免第二层循环*/
    public int trap1(int[] height) {
        int res=0;
        /*max_left[i]表示i最高的左墙,即max(height[0,i-1])*/
        int[] max_left=new int[height.length];
        if(max_left.length>0)
            max_left[0]=0;
        for(int j=1;j<max_left.length;j++){
            max_left[j]=Math.max(height[j-1],max_left[j-1]);
        }
        int[] max_right=new int[height.length];
        if(max_right.length>0)
            max_right[max_right.length-1]=0;
        for(int j=max_right.length-2;j>=0;j--){
            max_right[j]=Math.max(height[j+1],max_right[j+1]);
        }
        for(int i=0;i<height.length;i++){
            res+=Math.max(Math.min(max_left[i],max_right[i])-height[i],0);
        }
        return res;
    }
}
