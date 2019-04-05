package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Baibao {
    static int[] cost={30,20,10};
    static int[] val={5,3,3};

    static int total=30;
    public static void main(String[] args) {
        System.out.println(bestChooseIII(0,total));
        System.out.println(resolveMemo());
    }

    /**
     * 递归解法，复杂度2^n，
     * 获得取得最大值的方案，还没解决，目前考虑的就是把所有递归树中的结点的选取方案进行保留
     */
    static int bestChoose(int i,int curAvail){
        if(i==cost.length){
            return 0;
        }
        int res=bestChoose(i+1,curAvail);
        if(curAvail>=cost[i]){
            int res1=bestChoose(i+1,curAvail-cost[i])+val[i];
            if(res1>res) {
                res = res1;
            }
        }
        return res;
    }

    /**
     * 经典数组解法，但是会产生稀疏矩阵
     * @return
     */
    static int bestChooseII(){
        int N=cost.length;
        int[][] data=new int[N][total+1];
        for(int c=0;c<=total;c++){
            if(c>=cost[N-1])
                data[N-1][c]=val[N-1];
        }
        for(int k=N-2;k>=0;k--) {
            for(int c = total; c >= 0; c--) {
                data[k][c]=data[k+1][c];
                if(c>=cost[k])
                    data[k][c]=Integer.max(data[k][c],data[k+1][c-cost[k]]+val[k]);
            }
        }

        //寻找得到最大值的一个解
        List<Integer> sol=new ArrayList<>();
        int j=total;
        for(int i=0;i<N;i++){
            if(i==N-1&&data[i][j]!=0)
                sol.add(i);
            else if(data[i][j]!=data[i+1][j]){
                sol.add(i);
                j-=cost[i];
            }
        }
        System.out.println(sol);
        return data[1][total];
    }

    /**
     * HashMap版
     */


    static HashMap<Integer,HashMap<Integer,Integer>> memo=new HashMap<>();
    static int bestChooseIII(int i,int curAvail){

        if(i==cost.length){
            return 0;
        }
        if(memo.containsKey(i)&&memo.get(i).containsKey(curAvail))
            return memo.get(i).get(curAvail);

        int res=bestChooseIII(i+1,curAvail);
        if(curAvail>=cost[i]){
            int res1=bestChooseIII(i+1,curAvail-cost[i])+val[i];
            if(res1>res) {
                res = res1;
            }
        }
        HashMap<Integer,Integer> memo1=memo.get(i);
        if(memo1==null)
            memo1=new HashMap<>();
        memo1.put(curAvail,res);
        memo.put(i,memo1);
        return res;
    }

    //根据备忘录找到一组解
    static List<Integer> resolveMemo(){
        List<Integer> sol=new ArrayList<>();
        int N=cost.length;

        int j=total;
        for(int i=0;i<N;i++){
            if(i==N-1&&memo.get(i).get(j)!=0)
                sol.add(i);
            else if(memo.get(i).get(j)!=memo.get(i+1).get(j)){
                sol.add(i);
                j-=cost[i];
            }
        }
        return sol;
    }
}
