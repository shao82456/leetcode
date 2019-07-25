package zyb;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    TreeMap<Integer, Integer> data = new TreeMap<>();
    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();
      if(n==0) break;
      for (int i = 0; i < n; i++) {
        int num = scanner.nextInt();
        data.put(Math.abs(num), num);
      }
      List<String> list=new ArrayList<>();
      while (!data.isEmpty()) {
        list.add(data.pollLastEntry().getValue()+"");
      }
      String res=String.join(" ",list);
      System.out.println(res);
    }
  }

}
