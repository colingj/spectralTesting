import java.util.*;

public class StatementSub extends Statement
{
  String varRef1;
  String varRef2;
  String out;

  public StatementSub(String n1, String n2, String outp)
  {
    varRef1 = n1;
    varRef2 = n2;
    out  = outp;
  }

  public double eval(List<Double> inputs, List<Double> state)
  {
    return state.get(Integer.parseInt(varRef1.substring(1)))
      -state.get(Integer.parseInt(varRef2.substring(1)));
  }

  public int whereTo()
  {
    return Integer.parseInt(out.substring(1));
  }
  
  public String toString() {
		return "sub "+  varRef1+" "+ varRef2+" "+ out;

	}
}
