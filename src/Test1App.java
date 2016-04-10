import java.util.*;

public class Test1App
{
  public static void main(String[] args)
    throws java.io.FileNotFoundException
  {
    Program pp = new Program("exampleQuad.prg");
    List<Double> input = new ArrayList<>();
    input.add(1.0);
    input.add(4.0);
    input.add(2.0);
    pp.eval(input);
    
    Target tt = new TargetExampleQuad();
    System.out.println("Target is: "+tt.calculate(input));

  }
}
