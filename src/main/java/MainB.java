import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] edgs = new int[n + 1];
        List<List<Integer>> ls = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            ls.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            edgs[a] += 1;
            edgs[b] += 1;
            ls.get(a).add(b);
            ls.get(b).add(a);
        }
        boolean ov = false;
        int total = 0;
        while (!ov) {
            List<Integer> toremove = new ArrayList<>();
            for (int i = 0; i < edgs.length; i++) {
                if (edgs[i] == 1) {
                    toremove.add(i);
                }
            }
            if (toremove.size() == 0) {
                break;
            } else {
                for (int tr : toremove) {
                    edgs[tr] = -1;
                    for (int ne : ls.get(tr)) {
                        edgs[ne] -= 1;
                    }
                }
                total += 1;
            }
        }
        System.out.println(total);
    }
}
