import java.util.*;

abstract public class Statement
{
  abstract public int eval(List<Integer> inputs, List<Integer> state);
  abstract public int whereTo();
}
