package zyb;
import java.util.*;

public class Main2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String[] words=scanner.nextLine().split("5");
//      for(String word:words)
//        System.out.println(word);
      List<Integer> nums=new ArrayList<>();
      for (String word:words) {
        if(word.length()!=0)
          nums.add(Integer.parseInt(word));
      }
      Collections.sort(nums);
      for(int i=0;i<nums.size();i++){
        System.out.print(nums.get(i));
        if(i!=nums.size()-1)
          System.out.print(" ");
      }
      System.out.println();
    }
  }

}
