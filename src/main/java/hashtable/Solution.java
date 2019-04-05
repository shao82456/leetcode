package hashtable;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length())
            return false;
        byte[] map=new byte[128];
        byte[] rmap=new byte[128];

        for(int i=0;i<s.length();i++){
            byte sch= (byte) s.charAt(i);
            byte tch= (byte) t.charAt(i);
            if(map[sch]==0&&rmap[tch]==0){
                map[sch]= (byte) tch;
                rmap[tch]= (byte) sch;
            }else{
                if(tch!=map[sch])
                    return false;
            }
        }
        return true;
    }

    public boolean containsDuplicate(int[] nums) {

        HashSet<Integer> set=new HashSet<>();
        for(int num:nums)
            set.add(num);
        return nums.length==set.size();
    }

    public boolean containsDuplicateII(int[] nums,int k) {

        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            if(map.containsKey(nums[i])&&(i-map.get(nums[i]))<=k)
                return true;
            map.put(nums[i], i);
        }
        return false;
    }
}
