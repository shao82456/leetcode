package zyb;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            TreeMap<String, TreeMap<String, Integer>> data = new TreeMap<>();
            int m = Integer.valueOf(scanner.nextLine());
            for (int j = 0; j < m; j++) {
                String[] fs = scanner.nextLine().split(" ");
                if (!data.containsKey(fs[1])) {
                    data.put(fs[1], new TreeMap<>());
                }
                Map<String, Integer> map = data.get(fs[1]);
                map.put(fs[0], map.getOrDefault(fs[0], 0) + Integer.valueOf(fs[2]));
            }
            while (!data.isEmpty()) {
                Map.Entry e = data.pollFirstEntry();
                System.out.println(e.getKey());
                TreeMap<String, Integer> map = (TreeMap<String, Integer>) e.getValue();
                while (!map.isEmpty()) {
                    Map.Entry e1 = map.pollFirstEntry();
                    System.out.println("   |----" + e1.getKey() + "(" + e1.getValue() + ")");
                }
            }
            if (i != n - 1)
                System.out.println();
        }
    }

}
