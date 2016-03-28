import java.util.*;

public class DataSet
{
  int noInst; //number of instances
  int noVar; //number of variables
  List<List<Integer>> data; //(var)(inst)
  List<String> varNames;
  String name; //human readable name

  /***************************************************************/
  public DataSet(int noVarp)
  {
    noVar = noVarp;
    varNames = new ArrayList<>(noVar);
    name = new String();
    name += "DataSet with "+noVar+" variables, ";
  }

  /***************************************************************/
  public DataSet(DataSet datasetp) //copy constructor
  {
    noInst = datasetp.noInst;
    noVar = datasetp.noVar;
    data = new ArrayList<>();
    for (int var=0;var<noVar;var++)
      {
	List<Integer> ll = new ArrayList<>();
	for (int inst=0;inst<noInst;inst++)
	  {
	    ll.add(datasetp.data.get(var).get(inst));
	  }
	data.add(ll);
      }
    varNames = new ArrayList<>();
    for (int var=0;var<noVar;var++)
      {
	varNames.add(datasetp.varNames.get(var));
      }
    name = datasetp.name;
  }

  /***************************************************************/
  public void initialiseComplete(int min, int max)
  {
    //min and max are the inclusive ends of the range
    int nn = max-min+1;
    noInst = (int)Math.pow(nn,noVar);
    data = new ArrayList<>(noVar);
    /** initialise the zeroth instance **/
    for (int var=0;var<noVar;var++)
      {
	data.add(new ArrayList<Integer>(noInst));
	data.get(var).add(min);
      }
    /** now fill in the rest **/
    for (int inst=1;inst<noInst;inst++)
      {
	boolean carry = true;
	for (int var=0;var<noVar;var++)
	  {
	    if (carry)
	      {
		int newVal = data.get(var).get(inst-1)+1;
		if (newVal>max) { data.get(var).add(min); carry = true; }
		else { data.get(var).add(newVal); carry = false; }
	      }
	    else
	      {
		data.get(var).add(data.get(var).get(inst-1));
		carry = false;
	      }
	  }
      }
    for (int var=0;var<noVar;var++)
      {
	varNames.add("v"+var);
      }
    name += "initialised completely in range ["+min+","+max+"]";
  }

  /***************************************************************/
  public DataSet initialiseSample(int min, int max, double prob)
  //this initialises the current object to a sample of the objects
  // with probability prob, with the remaining being copied into
  // another DataSet object, which is returned
  //min and max are the inclusive ends of the range
  {
    /** set up the testSample **/
    DataSet testSample = new DataSet(noVar);
    testSample.data = new ArrayList<>(noVar);
    for (int var=0;var<noVar;var++)
      {
	testSample.data.add(new ArrayList<Integer>(noInst));
      }

    /** set up the current object, the training sample **/
    this.initialiseComplete(min,max); //initialise first, then remove

    /** copy over random instances from the dataset **/
    List<Integer> transferArgs = new ArrayList<>();
    for (int inst=0;inst<noInst;inst++)
      {
	if (Math.random()>=prob)
	  {
	    for (int var=0;var<noVar;var++)
	      {
		testSample.data.get(var).add(data.get(var).get(inst));
	      }
	    transferArgs.add(inst); 
	  }
      }
    
    /** remove transferred items from list **/
    Collections.reverse(transferArgs);
    for (int ta: transferArgs)
      {
	for (int var=0;var<noVar;var++)
	  {
	    data.get(var).remove(ta);
	  }
      }

    /** set other values: noInst, varNames, human-readable name **/
    this.noInst = data.get(0).size();
    testSample.noInst = testSample.data.get(0).size();
    for (int var=0;var<noVar;var++)
      {
	this.varNames.add("v"+var);
	testSample.varNames.add("v"+var);
      }
    name = "DataSet with "+noVar+" variables, "
      +"initialised training sample with prob "+prob
      +" in range ["+min+","+max+"]";
    testSample.name =
      "DataSet with "+noVar+" variables, "
      +"initialised testing sample with prob "+prob
      +" in range ["+min+","+max+"]";

    return testSample;
  }
  
/***************************************************************/
  List<Integer> columnToList(int var)
  {
    return data.get(var);
  }

  /***************************************************************/
  public List<String> getVarNamesAsList()
  {
    return varNames;
  }

  /***************************************************************/
  public List<List<Integer>> getData() { return data; }

  /***************************************************************/
  public String getName() { return name; }
  
  /***************************************************************/
  public String getVarName(int var) { return varNames.get(var); }
  
  /***************************************************************/
  public int getNoVar() { return noVar; }

  /***************************************************************/
  public String size() { return "var: "+noVar+" inst: "+noInst; };
  
  /***************************************************************/
  @Override
  public String toString()
  {
    String ans = new String();
    for (int var=0;var<noVar;var++)
      {
	ans += varNames.get(var)+"\t";
      }
    ans += "\n";
    for (int inst=0;inst<noInst;inst++)
      {
	for (int var=0;var<noVar;var++)
	  {
	    ans += data.get(var).get(inst)+"\t";
	  }
	ans += "\n";
      }
    return ans;
  }
  
}//end of class
