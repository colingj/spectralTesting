import java.util.*;

public class StatementVar extends Statement
{
  String varRef;
  String out;

  public StatementVar(String n1, String outp)
  {
    varRef = n1;
    out  = outp;
  }

  public double eval(List<Double> inputs, List<Double> state)
  {
    return inputs.get(Integer.parseInt(varRef));
  }

  public int whereTo()
  {
    return Integer.parseInt(out.substring(1));
  }
}
