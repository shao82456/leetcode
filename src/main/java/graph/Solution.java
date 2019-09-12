package graph;

public class Solution {
    /*997. Find the Town Judge
      only the judge get in-degree - out-degree= N-1
     */
    public int findJudge(int N, int[][] trust) {
        int[] ind = new int[N + 1];
        int[] outd = new int[N + 1];
        for (int i = 0; i < trust.length; i++) {
            outd[trust[i][0]] += 1;
            ind[trust[i][1]] += 1;
        }
        for(int i=1;i<=N;i++){
            if(ind[i]-outd[i]==N-1) return i;
        }
        return -1;
    }
}
