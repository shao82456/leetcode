
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("jh");
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
            arr.remove(i);
        }
    }

    /**
     * 按位取反，末尾+1是个可逆过程，补码中，正数=>负数，负数=>正数
     * -2147483647的-还是自己
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        System.out.println(Integer.toBinaryString(n));
        return (n & (-n)) == n;
    }

    /**
     * 342. Power of Four 先判断是否是2的power，再判断为1的位是否落入偶数位
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        if (num == 0) return false;
        if ((num & (-num)) != num) return false;
        if ((num & 0x55555555) == 0) return false;
        return true;
    }
}
