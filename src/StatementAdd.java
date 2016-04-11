import java.util.*;

public class StatementAdd extends Statement
{
  String varRef1;
  String varRef2;
  String out;
  
  public static void main(String[] args) {
	  StatementAdd add = new StatementAdd("v1", "v2", "v3"); 
	  System.out.println(add.out); 
  }

  public StatementAdd(String n1, String n2, String outp)
  {
    varRef1 = n1;
    varRef2 = n2;
    out  = outp;
  }

  public double eval(List<Double> inputs, List<Double> state)
  {
    return state.get(Integer.parseInt(varRef1.substring(1)))
      +state.get(Integer.parseInt(varRef2.substring(1)));
  }

  public int whereTo()
  {
    return Integer.parseInt(out.substring(1));
  }
  
  public String toString() {
		return "add "+  varRef1+" "+ varRef2+" "+ out;

	}
}
