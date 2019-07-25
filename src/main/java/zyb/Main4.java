package zyb;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n == 0) break;
            System.out.println(nQueen(n));
        }
    }

    static int res;

    static int nQueen(int N) {
        if (N == 1) return 1;
        int[][] map = new int[N][N];
        res = 0;
        f(N, map, 0);
        return res;
    }

    static void f(int left, int[][] map, int s) {
        int y = s % map.length;
        int x = s / map.length;
        if (left == 0) {
            res += 1;
            return;
        }
        if (x == map.length || y == map[0].length) {
            return;
        }
        if (canPut(map, x, y)) {
            map[x][y] = 1;
            f(left - 1, map, s + 1);
            map[x][y] = 0;
        }
        f(left, map, s + 1);
    }

    static boolean canPut(int[][] map, int x, int y) {
        //判断行
        for (int j = 0; j < map[0].length; j++) {
            if (map[x][j] != 0) return false;
        }
        //判断列
        for (int i = 0; i < map.length; i++) {
            if (map[i][y] != 0) return false;
        }
        //判断对角线
        int j = y - 1;
        for (int i = x - 1; i >= 0 && j >= 0; i--) {
            if (map[i][j--] != 0) return false;
        }
        //判断反对角线
        j = y + 1;
        for (int i = x - 1; i >= 0 && j < map[0].length; i--) {
            if (map[i][j++] != 0) return false;
        }
        return true;
    }


}
