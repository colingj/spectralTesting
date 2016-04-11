import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import weka.core.*;
import weka.classifiers.*;
import weka.classifiers.functions.*;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.trees.*;
import weka.attributeSelection.*;

public class Spectrum {
	private final static Logger LOGGER = Logger.getLogger(Spectrum.class.getName());

	int noTrainingCases, noVar, noLines;

	double[][][] spec; // [variable][line][trainingCase]
	double[] target; // [trainingCase]
	int targetInState; // which variable in state is the target
	List<Integer> commentLines;

	public Spectrum(Program prog, DataSet ds, Target trg) {
		LOGGER.setLevel(Level.SEVERE);

		commentLines = prog.getCommentLines();
		/** the main spectrum **/

		noTrainingCases = ds.getNoInst();
		noVar = prog.getNoVar();
		noLines = prog.getNoLines();
		spec = new double[noVar][noLines][noTrainingCases];

		for (int tc = 0; tc < noTrainingCases; tc++) {
			List<Double> inputs = ds.getTrainingCase(tc);
			List<List<Double>> results = prog.getSpectrum(inputs);
			for (int line = 0; line < noLines; line++) {
				List<Double> state = results.get(line);
				for (int var = 0; var < noVar; var++) {
					spec[var][line][tc] = state.get(var);
				}
			}
		}

		/** the target **/

		target = new double[noTrainingCases];
		for (int tc = 0; tc < noTrainingCases; tc++) {
			target[tc] = trg.calculate(ds.getTrainingCase(tc));
		}

		targetInState = prog.getArgOutput();
	}

	public double[][] analyse() {
		// LOGGER.setLevel(Level.INFO);
		LOGGER.setLevel(Level.SEVERE);

		/** set up outputs **/
		int numberOfMLMethods = 1;
		double[][] answer = new double[noLines - noVar + 1][numberOfMLMethods];

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
					// System.out.printf("%.2f ", spec[var][line][tc]);
					ins.setValue(featureVector.get(var), spec[var][line][tc]);
				}
				//System.out.println("test case: "+tc);
				// System.out.printf("%.2f %n ", target[tc]);
				// initialise the target value
				ins.setValue(featureVector.get(noVar), target[tc]);
				trainingSet.add(ins);
			}
//			String pattern = "##########.#";
//			DecimalFormat decimalFormat = new DecimalFormat(pattern);
//
//			String format = decimalFormat.format(123456789.123);
//			System.out.println(format);
			String theString = new String();
			for (int tc = 0; tc < noTrainingCases; tc++) {
				for (int var = 0; var < noVar; var++) {
					theString += spec[var][line][tc]-target[tc] + ";";
				}
			}
			System.out.println(theString);
			String outStr = new String();
			try {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				GZIPOutputStream gzip = new GZIPOutputStream(out);
				gzip.write(theString.getBytes());
				gzip.close();
				outStr = out.toString("ISO-8859-1");
			}
			catch (java.io.IOException ee)
			{
				System.err.println("Error");
				System.exit(1);
			}
			System.out.println(outStr.length());

			// /** write to files **/
			//
			// String stimuli = new String();
			// String targets = new String();
			// for (int tc = 0; tc < noTrainingCases; tc++) {
			// for (int var = 0; var < noVar-1; var++) {
			// stimuli += spec[var][line][tc]/16.0+";";
			// }
			// stimuli += spec[noVar-1][line][tc]/16.0+"\n";
			// targets += target[tc]/16.0+"\n";
			// }
			//
			// try {
			// Files.write(Paths.get("C:\\Users\\jrw\\git\\spectralTesting\\outputFiles\\stimuli"
			// + line+".txt"), stimuli.getBytes());
			// Files.write(Paths.get("C:\\Users\\jrw\\git\\spectralTesting\\outputFiles\\targets"
			// + line+".txt"), targets.getBytes());
			// } catch (IOException e1) {
			// e1.printStackTrace();
			// }
			//
			// /** back to weka **/

			// trainingSet.setClass(targetAtt);
			// try {
			// Random rn = new Random();
			//
			// // /** write first model to answer **/
			// // MultilayerPerceptron model2 = new MultilayerPerceptron();
			// // model2.setHiddenLayers("7,2");
			// // model2.setTrainingTime(500);
			// // model2.buildClassifier(trainingSet);
			// // Evaluation evaluation2 = new Evaluation(trainingSet);
			// // evaluation2.evaluateModel(model2, trainingSet);
			// // System.out.printf("Absolute error: %.2f %n",
			// // evaluation2.meanAbsoluteError());
			// // System.out.println(model2.toString());
			//
			// // /** write first model to answer **/
			// // if (line > (noVar - 1)) {
			// // answer[line][0] = evaluation2.meanAbsoluteError();
			// // }
			//
			// // /** print out details **/
			// // System.out.printf("Complexity %d:\t %.2f", line + 4,
			// // evaluation2.meanAbsoluteError());
			// // if (commentLines.contains(line + 4)) {
			// // System.out.print("\t ******");
			// // }
			// // System.out.println();
			// // /** end of print out details **/
			//
			// } catch (Exception e) {
			// System.err.println("Error!");
			// System.exit(1);
			// }
		}// end of looping through lines
		System.out.println("end");
		return answer;
	}

}
