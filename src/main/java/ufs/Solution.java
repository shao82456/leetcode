package ufs;

public class Solution {
}

class unionfind2 {

    int[] parent;
    int[] rank;

    public unionfind2(int n) {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<parent.length;i++) {
            parent[i]=i;
            rank[i]=1;
        }
    }

    private int find(int i) {
        while(i!=parent[i]) {
            parent[i]=parent[parent[i]];
            i=parent[i];
        }
        return i;
    }

    public boolean isConnected(int p, int q) {
        // TODO Auto-generated method stub
        return find(p)==find(q);
    }

    public void  union(int p,int q) {
        int proot=find(p);
        int qroot=find(q);
        if(proot==qroot) return;
        else {
            parent[proot]=parent[qroot];
        }
    }

    public void  unionWithRank(int p, int q) {
        // TODO Auto-generated method stub
        int proot=find(p);
        int qroot=find(q);
        if(proot==qroot) return;

        if(rank[proot]<rank[qroot]) {
            parent[proot]=parent[qroot];
        }else if(rank[proot]>rank[qroot]){
            parent[qroot]=parent[proot];
        }else {
            parent[qroot]=parent[proot];
            rank[proot]+=1;
        }
    }

    public int getSize() {
        // TODO Auto-generated method stub
        return parent.length;
    }

}