import java.util.*;

public class DataSet
{
  int noInst; //number of instances
  int noInputVar;//number of input variables
  int[][] data; //[var][inst]

  String[] varNames;
  String name; //human readable name

  /***************************************************************/
  public DataSet(int noInputVarp, String namep)
  {
    noInputVar = noInputVarp;
    varNames = new String[noInputVarp];
    name = namep;;
    name += "DataSet with "+noInputVar+" variables, ";
  }

  /***************************************************************/
  public void initialiseComplete(int min, int max)
  {
    //min and max are the inclusive ends of the range
    int nn = max-min+1;
    noInst = (int)Math.pow(nn,noInputVar);
    data = new int[noInputVar][noInst];

    /** initialise the zeroth instance **/
    for (int var=0;var<noInputVar;var++)
      {
	data[var][0] = min;
      }

    /** now fill in the rest **/
    for (int inst=1;inst<noInst;inst++)
      {
	boolean carry = true;
	for (int var=0;var<noInputVar;var++)
	  {
	    if (carry)
	      {
		int newVal = data[var][inst-1]+1;
		if (newVal>max) { data[var][inst] = min; carry = true; }
		else { data[var][inst] = newVal; carry = false; }
	      }
	    else
	      {
		data[var][inst] = data[var][inst-1];
		carry = false;
	      }
	  }
      }
    for (int var=0;var<noInputVar;var++)
      {
	varNames[var] = "v"+var;
      }
    name += "initialised completely in range ["+min+","+max+"]";
  }

  /***************************************************************/
  public List<Integer> getTrainingCase(int inst)
  {
    List<Integer> ans = new ArrayList<>();
    for (int var=0;var<noInputVar;var++)
      {
	ans.add(data[var][inst]);
      }
    return ans;
  }

  /***************************************************************/
  public int[][] getData() { return data; }

  /***************************************************************/
  public int getNoInst() { return noInst; }

  /***************************************************************/
  public int getNoInputVar() { return noInputVar; }

  /***************************************************************/
  public String theSize() { return "var: "+noInputVar+" inst: "+noInst; };
  
  /***************************************************************/
  @Override
  public String toString()
  {
    String ans = new String();
    for (int var=0;var<noInputVar;var++)
      {
	ans += varNames[var]+"\t";
      }
    ans += "\n";
    for (int inst=0;inst<noInst;inst++)
      {
	for (int var=0;var<noInputVar;var++)
	  {
	    ans += data[var][inst]+"\t";
	  }
	ans += "\n";
      }
    return ans;
  }
  
}//end of class
