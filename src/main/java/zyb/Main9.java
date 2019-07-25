package zyb;

import java.math.BigDecimal;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main9 {
    static class Offer implements Comparable<Offer>{
        int val;
        double p;

        public Offer(int val, double p) {
            this.val = val;
            this.p = p;
        }

        @Override
        public int compareTo(Offer o) {
            if(o.p/o.val<p/val){
                return -1;
            }else if(o.p/o.val==p/val){
                return 0;
            }else return 1;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if(n==0&&m==0) break;
            Queue<Offer> os=new PriorityQueue<>();
            for(int i=0;i<m;i++){
                os.add(new Offer(scanner.nextInt(),scanner.nextDouble()));
            }
            double d=1;
            while (!os.isEmpty()&&n>=os.element().val){
                Offer o=os.poll();
                n-=o.val;
                d*=(1-o.p);
//                System.out.println(n);
            }
            double res=(1-d);
            BigDecimal bd=new BigDecimal(res*100);
            BigDecimal bd1=bd.setScale(1,BigDecimal.ROUND_HALF_UP);
            System.out.println(bd1.toString()+"%");
            //dijkstra
        }
    }
}
