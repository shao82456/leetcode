import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    static class Pair implements Comparable<Pair>{
        long a;
        long b;

        public Pair(long a, long b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            return a<o.a?-1:a==o.a?0:1;
        }
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        List<Pair> cs=new ArrayList<>();
        for(int i=0;i<n;i++){
            long st=scanner.nextLong();
            long to=scanner.nextLong();
            cs.add(new Pair(st,to));
        }
        Collections.sort(cs);
        long stA=0,edA=-1;
        long stB=0,edB=-1;
        int total=0;
        for(Pair p:cs){
            if(edA<p.a){
                edA=p.b;
                total+=1;
                continue;
            }
            if(edB<p.a){
                edB=p.b;
                total+=1;
                continue;
            }
        }

        if(total==cs.size()){
            System.out.println("YES");
        }else{
            System.out.println("No");
        }
    }
}
