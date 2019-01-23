package pdd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maxtrim1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input1.txt"));

        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] input = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                input[i][j] = scanner.nextInt();
        }

        int i = 0, j = n - 1;
        while (i < m) {
            while (j >= 0 && input[i][j] == 1) j--;
            while (i < m && input[i][j] == 0) i++;
        }

        j+=1; //最左的1的纵坐标
        List<String> res = new ArrayList<>();
        for (int k = 0; k < m; k++) {
            if (input[k][j] == 1)
                res.add("[" + (k+1) + "," + (n - j) + "]");
        }

        System.out.println(res);
    }

}