package map;

import java.util.Map;
import java.util.TreeMap;

public class Solution {
    /**
     * 846. Hand of Straights
     * 用hash table记录可用的数字
     * @param hand
     * @param W
     * @return
     */
    public boolean isNStraightHand(int[] hand, int W) {
        if(hand.length%W!=0) return false;
        TreeMap<Integer,Integer> data=new TreeMap<>();
        for(int num:hand){
            int cur=data.getOrDefault(num,0);
            data.put(num,cur+1);
        }

        for(int k=0;k<hand.length/W;k++){
            int start=data.firstKey();
            deincreOrRemove(data,start);
            for(int j=1;j<W;j++){
                if(!data.containsKey(start+j))
                    return false;
                else
                    deincreOrRemove(data,start+j);
            }
        }
        return true;
    }

    private void deincreOrRemove(Map<Integer,Integer> map,int key){
        int val=map.get(key);
        if(val==1)
            map.remove(key);
        else
            map.put(key,val-1);
    }
}
