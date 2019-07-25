package zyb;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            double c = scanner.nextDouble();

            if (f(a, b, c))
                System.out.println("YES");
            else
                System.out.println("NO");
        }

    }

    static boolean f(double a, double b, double c) {
        boolean b1 = a + b > c;
        boolean b2 = a + c > b;
        boolean b3 = b + c > a;
        return b1 && b2 && b3;
    }

}
