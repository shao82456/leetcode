package zyb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main3 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextInt()) {
      int x = scanner.nextInt();
      int y = scanner.nextInt();

      if (x == 0 && y == 0)
        break;
      if(x>y){
        System.out.println("Sorry");
        continue;
      }
      if(f(x,y))
        System.out.println("OK");
      else
        System.out.println("Sorry");
    }
  }
  static boolean f(int x,int y){
    for(int num=x;num<=y;num++){
      int val=num*num+num+41;
      if(!isPrime(val)){
        return false;
      }
    }
    return true;
  }
  static boolean isPrime(int x){
    if(x==1) return false;
    for(int i=2;i*i<=x;i++){
      if(x%i==0) return false;
    }
    return true;
  }


}
