import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        for(int z=0;z<t;z++){
            int a=scanner.nextInt();
            int b=scanner.nextInt();
            int c=scanner.nextInt();
            int d=scanner.nextInt();
            int e=scanner.nextInt();
            int black=a%c==0?a/c:(a/c)+1;
            int red=b%d==0?b/d:(b/d)+1;
            if(black+red<=e){
                System.out.println(black+" "+red);
            }else
                System.out.println("-1");
        }
    }
}
