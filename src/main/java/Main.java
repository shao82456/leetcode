import com.sun.xml.internal.ws.api.ResourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File[] files = new File(".").listFiles();
        for (File file : files) {
            System.out.println(file);
        }
        Scanner scanner = new Scanner(ClassLoader.getSystemResourceAsStream("1.txt"));
        while (scanner.hasNextLine())
            System.out.println(scanner.nextLine());
    }

    static int res = 0;
    static int b1 = 0, b2 = 0, s1 = 0, s2 = 0;

    public static void f(int[] arr, int i, int curM, int curS, int bt, int st) {
        if (i == arr.length || bt == 0 || st == 0)
            res = Integer.max(res, curM);
        if (bt > 0) {
            f(arr, i + 1, curM - arr[i], curS + 1, bt - 1, st);
            if (bt == 2) b1 = i + 1;
            else b2 = i + 1;
        }
        if (st > 0 && curS > 0) {
            f(arr, i + 1, curM + arr[i], curS - 1, bt, st - 1);
            if (st == 2) s1 = i + 1;
            else s2 = i + 2;
        }
    }
}
