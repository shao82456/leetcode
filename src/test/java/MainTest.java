import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    Main sol=new Main();
    @Test
    public void getSum() {
        System.out.println(sol.getSum(-7,1));
        System.out.println(sol.isPowerOfTwo(0));
    }


    @Test
    public void isPowerOfFour() {
        System.out.println(sol.isPowerOfFour(5));
    }
}