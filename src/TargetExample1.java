import java.util.*;

public class TargetExample1 implements Target
{
  public int calculate(List<Integer> inputs) 
  {
    int ans = 0;
    for (Integer i: inputs) { ans += i; }
    return ans;
  }
}
