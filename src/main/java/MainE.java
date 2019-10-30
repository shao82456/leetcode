import java.util.Arrays;
import java.util.Scanner;

public class MainE {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        int[][] nums=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                nums[i][j]=scanner.nextInt();
            }
        }
//        System.out.println(cal(nums));
        for(int i=n-1;i>=0;i--) {
            for (int j = m - 1; j >= 0; j--) {
                if(nums[i][j]==0) {
                    put(i,j,nums);
                }
            }
        }

//        System.out.println(Arrays.deepToString(nums));
        int res=checkAndSum(nums);
        System.out.println(res);
    }

    private static int checkAndSum(int[][] nums) {
        int n=nums.length;
        int m=nums[0].length;
//        int last=Integer.MAX_VALUE;
        int sum=0;
        for(int i=0;i<n;i++){
            int last=nums[i][0];
            for(int j=1;j<m;j++){
                if(nums[i][j]==-1||nums[i][j]<=last)
                    return -1;
                else
                    last=nums[i][j];
            }
        }

        for(int j=0;j<m;j++){
            int last=nums[0][j];
            sum+=last;
            for(int i=1;i<n;i++){
                if(nums[i][j]<=last)
                    return -1;
                else {
                    last = nums[i][j];
                    sum+=last;
                }
            }
        }
        return sum;
    }

    static boolean put(int i,int j ,int[][] data){
        int st=Math.min(data[i+1][j],data[i][j+1]);
        for(int num=st-1;num>=1;num--){
            if(num>data[i-1][j]&&num>data[i][j-1]){
                data[i][j]=num;
                return true;
            }else{
                data[i][j]=-1;
            }
        }
        return false;
    }

}
