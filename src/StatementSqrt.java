import java.util.*;

public class StatementSqrt extends Statement {
	String varRef1;
	String out;

	public StatementSqrt(String n1, String outp) {
		varRef1 = n1;
		out = outp;
	}

	public double eval(List<Double> inputs, List<Double> state) {
		return Math.sqrt(state.get(Integer.parseInt(varRef1.substring(1))));
	}

	public int whereTo() {
		return Integer.parseInt(out.substring(1));
	}

}
