import java.util.*;

public class OutputStatement extends Statement
{
  String varRef;

  public OutputStatement(String n1)
  {
    varRef = n1;
  }

  public int eval(List<Integer> inputs, List<Integer> state)
  {
    return state.get(Integer.parseInt(varRef.substring(1)));
  }

  public int whereTo()
  {
    return Integer.parseInt(varRef.substring(1));
  }
}
