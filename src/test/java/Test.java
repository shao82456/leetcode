import java.lang.reflect.Array;
import java.util.*;

public class Test {
    private static final int MAXIMUM_CAPACITY =1<<30 ;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        List<List<Integer>> res=generate(4);
        for(List list:res)
            System.out.println(list);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<Integer> cur=new ArrayList<Integer>();
        List<List<Integer>> res=new ArrayList<>();
        if(numRows==0) return res;
        for(int i=1;i<=numRows;i++){
            for(int j=1;j<=i;j++){
                if(j==1||j==i)
                    cur.add(1);
                else {
                    List<Integer> last=res.get(i-2);
                    cur.add(last.get(j - 1) + last.get(j - 2));
                }
            }
            res.add((List)((ArrayList) cur).clone());
            cur.clear();
        }
        return res;
    }
}
