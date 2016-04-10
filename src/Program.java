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
	int[] target;

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
	public List<List<Double>> eval(List<Double> inputs) {
		LOGGER.setLevel(Level.SEVERE);
		List<List<Double>> evalByStep = new ArrayList<>();
		// this is a list by line of state-lists
		List<Double> state = new ArrayList<>();
		for (int v = 0; v < noVar; v++) {
			state.add(0.0);
		}
		String stateString = new String();
		stateString += "\n";
		for (int i = 0; i < prog.size(); i++) {
			LOGGER.info("here: "+prog.get(i).whereTo()+" "+prog.get(i).eval(inputs, state));
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
