import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        int x=scanner.nextInt();
        int incre=Math.abs(x);
        int i=1;
        int c=(t/x)*x;
        while(c<=t){
            i++;
            c+=incre;
        }
        System.out.println(c);
    }
}
