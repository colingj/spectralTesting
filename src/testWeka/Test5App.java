import java.util.*;
import weka.core.*;
import weka.classifiers.*;
import weka.classifiers.functions.MultilayerPerceptron;

public class Test5App
{
  public static void main(String[] args)
    throws Exception
  {
    System.out.println("MultiLayer Perceptron Learning using Weka.");
    
    /** describe the data set **/

    Attribute attribute1 = new Attribute("firstNumeric");
    Attribute attribute2 = new Attribute("secondNumeric");
    Attribute attribute3 = new Attribute("thirdNumeric");
    Attribute classAttribute = new Attribute("theClass");

    ArrayList<Attribute> featureVector = new ArrayList<>();
    featureVector.add(attribute1);
    featureVector.add(attribute2);
    featureVector.add(attribute3);
    featureVector.add(classAttribute);

    Instances trainingSet = new Instances("Rel", featureVector, 10);
    trainingSet.setClass(classAttribute);

    Instance i0 = new DenseInstance(1.0, new double[]{4,3,2,9});
    // i0.setValue(attribute1, 4);
    // i0.setValue(attribute2, 3);
    // i0.setValue(attribute3, 2);
    // i0.setValue(classAttribute, 9);
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 1);
    i0.setValue(attribute2, 6);
    i0.setValue(attribute3, 3);
    i0.setValue(classAttribute, 10);
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 2);
    i0.setValue(attribute2, 5);
    i0.setValue(attribute3, 7);
    i0.setValue(classAttribute, 14);
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 1);
    i0.setValue(attribute2, 3);
    i0.setValue(attribute3, 2);
    i0.setValue(classAttribute, 6);
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 1);
    i0.setValue(attribute2, 5);
    i0.setValue(attribute3, 1);
    i0.setValue(classAttribute, 7);
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 7);
    i0.setValue(attribute2, 5);
    i0.setValue(attribute3, 6);
    i0.setValue(classAttribute, 18);
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 4);
    i0.setValue(attribute2, 3);
    i0.setValue(attribute3, 5);
    i0.setValue(classAttribute, 12);
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 1);
    i0.setValue(attribute2, 3);
    i0.setValue(attribute3, 7);
    i0.setValue(classAttribute, 11);
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 4);
    i0.setValue(attribute2, 1);
    i0.setValue(attribute3, 1);
    i0.setValue(classAttribute, 6);
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 5);
    i0.setValue(attribute2, 6);
    i0.setValue(attribute3, 2);
    i0.setValue(classAttribute, 13);
    trainingSet.add(i0);

    /** now the testing set **/

    // wibble
    
    /** build and run the classifier **/

    AbstractClassifier cModel = new MultilayerPerceptron();
    cModel.buildClassifier(trainingSet);

    Evaluation eTest = new Evaluation(trainingSet);
    eTest.evaluateModel(cModel, trainingSet); //wibble: should be testing set
    String strSummary = eTest.toSummaryString();
    System.out.println(strSummary);
  }
}
