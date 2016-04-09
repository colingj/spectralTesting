import java.util.*;

abstract public class Statement
{
  //abstract public int eval(List<Integer> inputs, List<Integer> state);
  abstract public double eval(List<Double> inputs, List<Double> state);
   
  abstract public int whereTo();//reference to a position. 
}
