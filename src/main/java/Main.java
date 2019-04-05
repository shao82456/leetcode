
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String line=scanner.nextLine();
        scanner.close();
        String[] fields=line.split(",");
        int[] arr=new int[fields.length];
        for(int i=0;i<arr.length;i++)
            arr[i]=Integer.parseInt(fields[i]);
        res=0;
        f(arr,0,0,0,2,2);
//        System.out.println(res);
        System.out.println(b1+" "+s1+" "+b2+" "+s2);
    }

    static int res=0;
    static int b1=0,b2=0,s1=0,s2=0;
    public static void f(int[] arr,int i,int curM,int curS,int bt,int st){
        if(i==arr.length||bt==0||st==0)
            res=Integer.max(res,curM);
        if(bt>0) {
            f(arr, i + 1, curM - arr[i], curS + 1, bt - 1, st);
            if(bt==2) b1=i+1;
            else b2=i+1;
        }
        if(st>0&&curS>0) {
            f(arr, i + 1, curM + arr[i], curS - 1, bt, st - 1);
            if (st == 2) s1 = i + 1;
            else s2 = i + 2;
        }
    }
}
