import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

public class Program {
	private final static Logger LOGGER = Logger.getLogger(Program.class.getName());
	int noVar, noInputVar;
	int argOutput;
	List<Statement> prog;
	List<Integer> commentLines;

	private static int numberInstructions = 4;

	public static void main(String[] args) {
		Program program = new Program(10);
		// System.out.println(program.getNoLines());
		System.out.println("print prog");
		System.out.println(program.toString());
		List<Double> inputs = new ArrayList<Double>(); 
		inputs.add(1.0);
		inputs.add(2.0);
		inputs.add(3.0);
		program.getSpectrum(inputs);
		System.out.println(program.getOutput(inputs));
	}

	Program(int numLines) {
		noVar = 3;// hard coded for now.
		noInputVar = 3;// hard coded for now.
		argOutput = 2;// hard coded for now.
		prog = new ArrayList<Statement>();
		commentLines = new ArrayList<>();

		for (int var=0;var<noInputVar;var++)
		{
			prog.add(new StatementVar(var+"","v"+var));
		}
		for (int i = 0; i < numLines; i++) {
			int randomInstruction = new Random().nextInt(numberInstructions);
			// System.out.println("randomInstruction " + randomInstruction);

			switch (randomInstruction) {
			case 0:
				prog.add(new StatementAdd(getRandomVariableNumber(noVar), getRandomVariableNumber(noVar), getRandomVariableNumber(noVar)));
				break;
			case 1:
				prog.add(new StatementSub(getRandomVariableNumber(noVar), getRandomVariableNumber(noVar), getRandomVariableNumber(noVar)));
				break;
			case 2:
				prog.add(new StatementMul(getRandomVariableNumber(noVar), getRandomVariableNumber(noVar), getRandomVariableNumber(noVar)));
				break;
			case 3:
				prog.add(new StatementDiv(getRandomVariableNumber(noVar), getRandomVariableNumber(noVar), getRandomVariableNumber(noVar)));
				break;

			default:
				System.out.println("Invalid instruction number Invalid instruction number Invalid instruction number Invalid instruction number");
				break;
			}
		}
		System.out.println("prog.size() " + prog.size());
	}

	public String getRandomVariableNumber(int noVar) {
		return "v" + (new Random().nextInt(noVar));
	}

	public String toString() {
		String str = "" + noVar + " " + noInputVar + " " + argOutput + "\n";
		for (int i = 0; i < prog.size(); i++) {
			str += prog.get(i) + "\n";
		}
		return str;
	}

	/********************************************************************/
	public Program(String programFilename) throws java.io.FileNotFoundException {
		LOGGER.setLevel(Level.SEVERE);
		prog = new ArrayList<>();
		commentLines = new ArrayList<>();
		Scanner sc = new Scanner(new File(programFilename));
		String firstLine = sc.nextLine();
		Scanner lineSc = new Scanner(firstLine);// split into tokens by
												// whitespace
		String type = lineSc.next();
		if (!type.equals("novar")) {
			System.err.println("Error");
			System.exit(1);
		}
		noInputVar = lineSc.nextInt();
		noVar = lineSc.nextInt();

		String secondLine = sc.nextLine();
		lineSc = new Scanner(secondLine);// split into tokens by whitespace
		type = lineSc.next();
		if (!type.equals("output")) {
			System.err.println("Error");
			System.exit(1);
		}
		argOutput = Integer.parseInt(lineSc.next().substring(1));

		String thirdLine = sc.nextLine();
		lineSc = new Scanner(thirdLine);// split into tokens by whitespace
		type = lineSc.next();
		if (!type.equals("comments")) {
			System.err.println("Error");
			System.exit(1);
		}
		String comment = lineSc.next();
		commentLines.add(Integer.parseInt(comment));

		while (sc.hasNextLine()) {
			lineSc = new Scanner(sc.nextLine());// split into tokens by
												// whitespace
			String out, index, first, second;
			double con;
			type = lineSc.next();
			switch (type) {
			case "var":
				index = lineSc.next();
				lineSc.next();// ignore "into"
				out = lineSc.next();
				prog.add(new StatementVar(index, out));
				break;
			case "add":
				first = lineSc.next();
				second = lineSc.next();
				lineSc.next();// ignore "into"
				out = lineSc.next();
				prog.add(new StatementAdd(first, second, out));
				break;
			case "sub":
				first = lineSc.next();
				second = lineSc.next();
				lineSc.next();// ignore "into"
				out = lineSc.next();
				prog.add(new StatementSub(first, second, out));
				break;
			case "mul":
				first = lineSc.next();
				second = lineSc.next();
				lineSc.next();// ignore "into"
				out = lineSc.next();
				prog.add(new StatementMul(first, second, out));
				break;
			case "div":
				first = lineSc.next();
				second = lineSc.next();
				lineSc.next();// ignore "into"
				out = lineSc.next();
				prog.add(new StatementDiv(first, second, out));
				break;
			case "sqrt":
				first = lineSc.next();
				lineSc.next();// ignore "into"
				out = lineSc.next();
				prog.add(new StatementSqrt(first, out));
				break;
			case "mulConst":
				first = lineSc.next();
				con = Double.parseDouble(lineSc.next());
				lineSc.next();// ignore "into"
				out = lineSc.next();
				prog.add(new StatementMulConst(first, con, out));
				break;
			default:
				System.err.println("Error");
				System.exit(1);
			}
		}
		lineSc.close();
		sc.close();
	}

	/********************************************************************/
	public List<List<Double>> getSpectrum(List<Double> inputs) {
		LOGGER.setLevel(Level.SEVERE);
//		LOGGER.setLevel(Level.INFO);
		List<List<Double>> evalByStep = new ArrayList<>();
		// this is a list by line of state-lists
		List<Double> state = new ArrayList<>();
		for (int v = 0; v < noVar; v++) {
			//state.add(Math.random());
			state.add(0.0);
		}
		String stateString = new String();
		stateString += "\n";
		for (int i = 0; i < prog.size(); i++) {
			//System.out.println("test case: "+i);
			LOGGER.info("here: " + prog.get(i).whereTo() + " " + prog.get(i).eval(inputs, state));
			stateString += "State:\t";
			state.set(prog.get(i).whereTo(), prog.get(i).eval(inputs, state));
			for (int v = 0; v < noVar; v++) {
				stateString += state.get(v) + "\t";
			}
			stateString += "\n";
			/** end of temp print **/
			evalByStep.add(new ArrayList<Double>(state));
		}
		stateString += "\n";
		LOGGER.info(stateString);
		return evalByStep;
	}
	
	/********************************************************************/
	public double getOutput(List<Double> inputs) {
		List<List<Double>> spec = this.getSpectrum(inputs);
		return spec.get(spec.size()-1).get(argOutput);
	}
	
	/********************************************************************/
	/*
	 * public int[][] multiEval(List<List<Integer>> inputsList) { //each "eval"
	 * is a test case List<List<Integer>> evals = new ArrayList<>(); for
	 * (List<Integer> input: inputsList) { List<Integer> thisEval =
	 * this.eval(input); evals.add(thisEval); } int noEvals = inputsList.size();
	 * int noLines = evals.get(0).size(); int[][] ans = new
	 * int[noLines][noEvals]; for (int li=0;li<noLines;li++) { for (int
	 * ev=0;ev<noEvals;ev++) { ans[li][ev] = evals.get(ev).get(li); } } return
	 * ans; }
	 */

	/********************************************************************/
	public int getNoInputVar() {
		return noInputVar;
	}

	/********************************************************************/
	public int getNoVar() {
		return noVar;
	}

	/********************************************************************/
	public int getNoLines() {
		return prog.size();
	}

	/********************************************************************/
	public int getArgOutput() {
		return argOutput;
	}

	/********************************************************************/
	public List<Integer> getCommentLines() {
		return commentLines;
	}

}
