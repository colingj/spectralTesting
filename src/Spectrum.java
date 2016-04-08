import java.util.*;
import java.util.logging.Logger;

import weka.core.*;
import weka.classifiers.*;
import weka.classifiers.functions.*;

public class Spectrum {
	private final static Logger LOGGER = Logger.getLogger(Spectrum.class.getName());
	int noTrainingCases, noVar, noLines;

	int[][][] spec; // [variable][line][trainingCase]
	int[] target; // [trainingCase]
	int targetInState; // which variable in state is the target

	public Spectrum(Program prog, DataSet ds, Target trg) {
		/** the main spectrum **/

		noTrainingCases = ds.getNoInst();
		noVar = prog.getNoVar();
		noLines = prog.getNoLines();
		spec = new int[noVar][noLines][noTrainingCases];

		for (int tc = 0; tc < noTrainingCases; tc++) {
			List<Integer> inputs = ds.getTrainingCase(tc);
			List<List<Integer>> results = prog.eval(inputs);
			for (int line = 0; line < noLines; line++) {
				List<Integer> state = results.get(line);
				for (int var = 0; var < noVar; var++) {
					spec[var][line][tc] = state.get(var);
				}
			}
		}

		/** the target **/

		target = new int[noTrainingCases];
		for (int tc = 0; tc < noTrainingCases; tc++) {
			target[tc] = trg.calculate(ds.getTrainingCase(tc));
		}

		targetInState = prog.getArgOutput();
	}

	public void analyse() {
		/* set up the feature vector */
		ArrayList<Attribute> featureVector = new ArrayList<>();
		for (int var = 0; var < noVar; var++) {
			featureVector.add(new Attribute("v" + var));
		}
		Attribute targetAtt = new Attribute("target");
		featureVector.add(targetAtt);

		/** build a model for each line **/
		for (int line = 0; line < noLines; line++) {
			Instances trainingSet = new Instances("ts" + line, featureVector, noTrainingCases);
			for (int tc = 0; tc < noTrainingCases; tc++) {
				Instance ins = new DenseInstance(noVar + 1);
				for (int var = 0; var < noVar; var++) {
					// initialise the variables
					//System.out.print(spec[var][line][tc] + " ");
					ins.setValue(featureVector.get(var), spec[var][line][tc]);
				}
				// System.out.println(" "+target[tc]);
				// initialise the target value
				ins.setValue(featureVector.get(noVar), target[tc]);
				trainingSet.add(ins);
			}
			trainingSet.setClass(targetAtt);
			try {
				// MultilayerPerceptron cModel = new MultilayerPerceptron();
				LinearRegression cModel = new LinearRegression();
				cModel.buildClassifier(trainingSet);
				System.out.println(cModel);
				 Evaluation eTest = new Evaluation(trainingSet);
				 eTest.evaluateModel(cModel, trainingSet);
				 // wibble: previous line should be testing set
				 String strSummary = eTest.toSummaryString();
				 System.out.println(strSummary);
			} catch (Exception e) {
				System.err.println("Error!");
				System.exit(1);
			}
		}// end of looping through lines
	}

}
