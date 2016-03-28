import java.util.*;

public class Spectrum
{
  int noTrainingCases,noVar,noLines;

  int[][][] spec; // [variable][line][trainingCase]
  int[] target; // [trainingCase]
  int targetInState; //which variable in state is the target

  public Spectrum(Program prog, DataSet ds)
  {
    /** the main spectrum **/

    noTrainingCases = ds.getNoInst();
    noVar = prog.getNoVar();
    noLines = prog.getNoLines();
    spec = new int[noVar][noLines][noTrainingCases];

    for (int tc=0;tc<noTrainingCases;tc++)
      {
	List<Integer> inputs = ds.getTrainingCase(tc);
	List<List<Integer>> results = prog.eval(inputs);
	for (int line=0;line<noLines;line++)
	  {
	    List<Integer> state = results.get(line);
	    for (int var=0;var<noVar;var++)
	      {
		spec[var][line][tc] = state.get(var);
	      }
	  }
      }

    /** the target **/
    
    targetInState = prog.getArgOutput();
  }

}
