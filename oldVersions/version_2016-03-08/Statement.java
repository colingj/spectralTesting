import java.util.*;

public class Statement
{
  int val,ref1,ref2;
  String type;

  /************************************************************/
  public Statement(String typep, int n1, int n2)
  {
    type = typep;
    System.out.println("Type: "+type);
    if (type.equals("var")) { val = n1; }
    else { ref1 = n1; ref2 = n2; }
  }

  /************************************************************/
  public int eval(List<Integer> inputs, List<Integer> soFar)
  {
    if (type.equals("var"))
      { return inputs.get(val); }
    else if (type.equals("add"))
      { return soFar.get(ref1) + soFar.get(ref2); }
    else if (soFar.equals("sub"))
      { return soFar.get(ref1) - soFar.get(ref2); }
    else if (soFar.equals("mul"))
      { return soFar.get(ref1) * soFar.get(ref2); }
    return -999;//unreachable
  }
  
}
