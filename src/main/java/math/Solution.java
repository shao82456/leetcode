package math;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    /**
     * 
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        return false;
    }
    /**
    12. Integer to Roman
     **/
    public String intToRoman(int num) {
        String[] mapg={"I","II","III","IV","V","VI","VII","VIII","IX"};
        String[] maps={"X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] mapb={"C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String[] mapq={"M","MM","MMM"};

        if(num<1||num>3999)
            throw new IllegalArgumentException();

        int val=num;
        int q=val/1000;
        val-=q*1000;
        int b=val/100;
        val-=b*100;
        int s=val/10;
        val-=s*10;
        int g=val;

        StringBuilder res=new StringBuilder();
        if(q!=0)
            res.append(mapq[q-1]);
        if(b!=0)
            res.append(mapb[b-1]);
        if(s!=0)
            res.append(maps[s-1]);
        if(g!=0)
            res.append(mapg[g-1]);

        return res.toString();
    }

    /**
     * 202. Happy Number
     * 关键在环检测，采用Floyd环检测算法
     */
    public boolean isHappy(int n) {
        int slow=n,fast=n;
        do{
            slow=calculate(slow);
            if(slow==1) return true;
            fast=calculate(fast);
            fast=calculate(fast);
        }while (fast!=slow);
        return false;
    }
    public int calculate(int num){
        int res=0;
        while(num>0){
            res+=(num%10)*(num%10);
            num/=10;
        }
        return res;
    }

    /**
     * 求比n小的质数的个数
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if(n<3) return 0;
        boolean[] isPrime=new boolean[n];
        Arrays.fill(isPrime,true);
        int res=0;
        for(int i=2;i<n;i++){
            if(isPrime[i]){
                res++;
                for(int k=1;k*i<n;k++)
                    isPrime[k*i]=false;
            }
        }
        return res;
    }
        public int countPrimes1(int n) {
        int maxFactor= (int) Math.ceil(Math.sqrt(n));
        HashSet<Integer> res=new HashSet<>();
        for(int factor=2;factor<=maxFactor;factor+=1){
            while (n%factor==0){
                n/=factor;
                res.add(factor);
            }
        }
        return res.size();
    }
}
