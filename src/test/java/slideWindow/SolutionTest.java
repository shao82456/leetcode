package slideWindow;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    Solution solution=new Solution();
    @Before
    public void setUp() throws Exception {
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void lengthOfLongestSubstring() {
        int res=solution.lengthOfLongestSubstring("abcabcbb");
        Assert.assertEquals(3,res);
        System.out.println("这是中文");
    }
}