import java.util.*;

public class Test1App
{
  public static void main(String[] args)
    throws java.io.FileNotFoundException
  {
    Program pp = new Program("example2.prg");
    List<Double> input = new ArrayList<>();
    input.add(2.0);
    input.add(3.0);
    input.add(4.0);
    input.add(5.0);
    pp.eval(input);
    
    Target tt = new TargetExample2();
    System.out.println(tt.calculate(input));
  }
}
