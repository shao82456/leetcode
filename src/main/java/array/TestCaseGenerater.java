package array;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class TestCaseGenerater {

    List<List<Integer>> getRotatedArrays(){
        List<List<Integer>> data=new ArrayList<>();
        for (int k=0;k<100;k++){
            int n=0;
            switch (k/10){
                case 0:case 1:
                    n=10;
                    break;
                case 8:
                    n=1000;
                    break;
                case 9:
                    n=10000;
                    break;
                default:
                    n=100;
            }
            ArrayList<Integer> one=new ArrayList<>();
            for (int i=0;i<n;i++) one.add(i);
            data.add(one);
        }
        return data;
    }

}
