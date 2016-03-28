import java.util.*;

public class VarStatement extends Statement
{
  String varRef;
  String out;

  public VarStatement(String n1, String outp)
  {
    varRef = n1;
    out  = outp;
  }

  public int eval(List<Integer> inputs, List<Integer> state)
  {
    return inputs.get(Integer.parseInt(varRef));
  }

  public int whereTo()
  {
    return Integer.parseInt(out.substring(1));
  }
}
