import java.util.*;
import java.io.*;

public class Program
{
  int noVar, noInputVar;
  int argOutput;
  List<Statement> prog;

  /********************************************************************/
  public Program(String filename)
    throws java.io.FileNotFoundException
  {
    prog = new ArrayList<>();
    Scanner sc = new Scanner(new File(filename));
    String firstLine = sc.nextLine();
    Scanner lineSc = new Scanner(firstLine);//split into tokens by whitespace
    String type = lineSc.next();
    if (!type.equals("novar")) { System.err.println("Error"); System.exit(1); }
    noInputVar = lineSc.nextInt();
    noVar = lineSc.nextInt();
    String secondLine = sc.nextLine();
    lineSc = new Scanner(secondLine);//split into tokens by whitespace
    type = lineSc.next();
    if (!type.equals("output")) { System.err.println("Error"); System.exit(1); }
    argOutput = Integer.parseInt(lineSc.next().substring(1));

    while (sc.hasNextLine())
    {
      lineSc = new Scanner(sc.nextLine());//split into tokens by whitespace
      String out, index, first, second;
      type = lineSc.next();
      switch (type)
	{
	case "var": 
	  index = lineSc.next();
	  lineSc.next();//ignore "into"
	  out = lineSc.next();
	  prog.add(new VarStatement(index,out));
	  break;
	case "add":
	  first = lineSc.next();
	  second = lineSc.next();
	  lineSc.next();//ignore "into"
	  out = lineSc.next();
	  prog.add(new AddStatement(first,second,out));
	  break;
	case "sub":
	  first = lineSc.next();
	  second = lineSc.next();
	  lineSc.next();//ignore "into"
	  out = lineSc.next();
	  prog.add(new SubStatement(first,second,out));
	  break;
	default:
	  System.err.println("Error");
	  System.exit(1);
	}
    }
  }

  /********************************************************************/
  public List<List<Integer>> eval(List<Integer> inputs)
  {
    List<List<Integer>> evalByStep = new ArrayList<>(); 
    //this is a list by line of state-lists
    List<Integer> state = new ArrayList<>();
    for (int v=0;v<noVar;v++)
      {
	state.add(0);
      }
    for (int i=0;i<prog.size();i++)
      {
	state.add(prog.get(i).whereTo(),prog.get(i).eval(inputs,state));
	/** beginning of temp print **/
	System.out.print("State:\t");
	for (int v=0;v<noVar;v++)
	  {
	    System.out.print(state.get(v)+"\t");
	  }
	System.out.println();
	/** end of temp print **/
	evalByStep.add(new ArrayList<Integer>(state));
      }
    System.out.println();
    return evalByStep;
  }

  /********************************************************************/
  /*  public int[][] multiEval(List<List<Integer>> inputsList)
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
    }*/

  /********************************************************************/
  public int getNoInputVar() { return noInputVar;} 

  /********************************************************************/
  public int getNoVar() { return noVar;} 

  /********************************************************************/
  public int getNoLines() { return prog.size(); }

  /********************************************************************/
  public int getArgOutput() { return argOutput; }
}
