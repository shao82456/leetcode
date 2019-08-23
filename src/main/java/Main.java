
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File[] files = new File(".").listFiles();
        for (File file : files) {
            System.out.println(file);
        }
        Scanner scanner = new Scanner(ClassLoader.getSystemResourceAsStream("1.txt"));
        while (scanner.hasNextLine())
            System.out.println(scanner.nextLine());
    }

    public int getSum(int a, int b) {
        int extra=0;
        while((extra=(a&b))!=0){
            a=a^b;
            b=extra<<1;
        }
        return a^b;
    }

    /**
     * 按位取反，末尾+1是个可逆过程，补码中，正数=>负数，负数=>正数
     * -2147483647的-还是自己
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        System.out.println(Integer.toBinaryString(n));
        return (n&(-n))==n;
    }

    /**
     * 342. Power of Four 先判断是否是2的power，再判断为1的位是否落入偶数位
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        if(num==0) return false;
        if((num&(-num))!=num) return false;
        if((num&0x55555555)==0) return false;
        return true;
    }
}
