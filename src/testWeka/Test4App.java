package testWeka;

import java.util.*;
import weka.core.*;
import weka.classifiers.*;
import weka.classifiers.bayes.NaiveBayes;

public class Test4App
{
  public static void main(String[] args)
    throws Exception
  {
    /** describe the data set **/

    Attribute attribute1 = new Attribute("firstNumeric");
    Attribute attribute2 = new Attribute("secondNumeric");
    Attribute attribute3 = new Attribute("thirdNumeric");
    List<String> classValues = new ArrayList<>();
    classValues.add("positive");
    classValues.add("negative");
    Attribute classAttribute = new Attribute("theClass",classValues);

    ArrayList<Attribute> featureVector = new ArrayList<>();
    featureVector.add(attribute1);
    featureVector.add(attribute2);
    featureVector.add(attribute3);
    featureVector.add(classAttribute);

    Instances trainingSet = new Instances("Rel", featureVector, 10);
    trainingSet.setClass(classAttribute);

    Instance i0 = new DenseInstance(4);
    i0.setValue(attribute1, 4);
    i0.setValue(attribute2, 3);
    i0.setValue(attribute3, 2);
    i0.setValue(classAttribute, "positive");
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 1);
    i0.setValue(attribute2, 6);
    i0.setValue(attribute3, 3);
    i0.setValue(classAttribute, "positive");
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 2);
    i0.setValue(attribute2, 5);
    i0.setValue(attribute3, 7);
    i0.setValue(classAttribute, "positive");
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 1);
    i0.setValue(attribute2, 3);
    i0.setValue(attribute3, 2);
    i0.setValue(classAttribute, "positive");
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, 1);
    i0.setValue(attribute2, 5);
    i0.setValue(attribute3, 1);
    i0.setValue(classAttribute, "positive");
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, -7);
    i0.setValue(attribute2, -5);
    i0.setValue(attribute3, 6);
    i0.setValue(classAttribute, "negative");
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, -4);
    i0.setValue(attribute2, -3);
    i0.setValue(attribute3, 5);
    i0.setValue(classAttribute, "negative");
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, -1);
    i0.setValue(attribute2, -3);
    i0.setValue(attribute3, 7);
    i0.setValue(classAttribute, "negative");
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, -4);
    i0.setValue(attribute2, -1);
    i0.setValue(attribute3, 1);
    i0.setValue(classAttribute, "negative");
    trainingSet.add(i0);

    i0 = new DenseInstance(4);
    i0.setValue(attribute1, -5);
    i0.setValue(attribute2, -6);
    i0.setValue(attribute3, 2);
    i0.setValue(classAttribute, "negative");
    trainingSet.add(i0);

    /** now the testing set **/
  
    
    /** build and run the classifier **/

    Classifier cModel = (Classifier)new NaiveBayes();
    cModel.buildClassifier(trainingSet);

    Evaluation eTest = new Evaluation(trainingSet);
    eTest.evaluateModel(cModel, trainingSet); //wibble: should be testing set
    String strSummary = eTest.toSummaryString();
    System.out.println(strSummary);
  }
}
