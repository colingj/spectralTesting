import java.util.*;
import weka.core.*;
import weka.classifiers.*;

public class Analyst
{
  int noOfTestCases, noOfLines;
  int[][] trace;
  int[] target;

  public Analyst(int[][] tracep, int[] targetp)
  {
    noOfTestCases = tracep.length;
    noOfLines = tracep[0].length;
    trace = new int[noOfLines][noOfTestCases];
    target = new int[noOfTestCases];

    for (int li=0;li<noOfLines;li++)
      {
	for (int tc=0;tc<noOfTestCases;tc++)
	  {
	    trace[li][tc] = tracep[li][tc];
	  }
      }
    for (int tc=0;tc<noOfTestCases;tc++)
      {
	target[tc] = targetp[tc];
      }
  }

  public void runTest()
  {
    Attribute[] attributes = new Attribute[noOfLines];
    for (int li=0;li<noOfLines;li++)
      {
	attributes[li] = new Attribute("Line "+li);
      }
    Attribute targetAtt = new Attribute("Target");

    /** loop around the program, taking one more line each time **/
    for (int currentLine=0;currentLine<noOfLines;currentLine++)
      {
	FastVector<Integer> training = new FastVector<>(currentLine+2);
	for (int li=0;li<currentLine;li++)
	  {
	    training.addElement(attributes[li]);
	  }
	training.addElement(targetAtt);
	Instances trainingSet = new Instances("rel", training, noOfTestCases);
	trainingSet.setClassIndex(currentLine+1);	
	for (int tc=0;tc<noOfTestCases;tc++)
	  {	
	    Instance newInstance = new DenseInstance(currentLine+2);
	    for (int li=0;li<currentLine;li++)
		{
		  newInstance.setValue(attributes[li], trace[li][tc]);
		}
	    newInstance.setValue(targetAtt, target[tc]);
	    trainingSet.add(newInstance);
	  }
	Classifier cModel = (Classifier)new NaiveBayes();
	cModel.buildClassifier(trainingSet);	
      }
  }

}
