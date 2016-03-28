import java.util.*;

public class Test1App
{
  public static void main(String[] args)
    throws java.io.FileNotFoundException
  {
    Program pp = new Program("example1.prg");
    List<Integer> input = new ArrayList<>();
    input.add(2);
    input.add(3);
    input.add(4);
    input.add(5);
    pp.eval(input);
  }
}
