import java.util.*;
import java.io.*;

public class Program
{
  int noVar;
  List<Statement> prog;

  /********************************************************************/
  public Program(String filename)
    throws java.io.FileNotFoundException
  {
    prog = new ArrayList<>();
    Scanner sc = new Scanner(new File(filename));
    String firstLine = sc.nextLine();
    Scanner lineSc = new Scanner(firstLine);
    lineSc.next();//ignore
    lineSc.next();//ignore
    noVar = lineSc.nextInt();

    while (sc.hasNextLine())
    {
      lineSc = new Scanner(sc.nextLine());
      lineSc.next();//ignore line number
      String type = lineSc.next();
      if (type.equals("var"))
	{ prog.add(new Statement(type,lineSc.nextInt(),0)); }
      else
	{ prog.add(new Statement(type,lineSc.nextInt(),lineSc.nextInt())); }
    }
  }

  /********************************************************************/  public List<Integer> eval(List<Integer> inputs)
  {
    List<Integer> evalByStep = new ArrayList<>();
    for (int i=0;i<prog.size();i++)
      {
	evalByStep.add(prog.get(i).eval(inputs,evalByStep));
      }

    /** temp for testing **/
    for (int i: evalByStep)
      {
	System.out.println(i);
      }
    /** end of temp for testing **/
    return evalByStep;
  }

  /********************************************************************/
  public int[][] multiEval(List<List<Integer>> inputsList)
  {
    //each "eval" is a test case
    List<List<Integer>> evals = new ArrayList<>();
    for (List<Integer> input: inputsList)
      {
	List<Integer> thisEval = this.eval(input);
	evals.add(thisEval);
      }
    int noEvals = inputsList.size();
    int noLines = evals.get(0).size();
    int[][] ans = new int[noLines][noEvals];
    for (int li=0;li<noLines;li++)
      {    
	for (int ev=0;ev<noEvals;ev++)
	  {
	    ans[li][ev] = evals.get(ev).get(li);
	  }
      }
    return ans;
  }
}
