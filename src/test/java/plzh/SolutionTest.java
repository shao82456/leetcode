package plzh;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
  Solution sol=new Solution();
  @Test
  public void f3() {
    assertEquals(6,sol.f3(5,6));
  }
}
