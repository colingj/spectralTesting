import java.util.*;

public class StatementDiv extends Statement {
	String varRef1;
	String varRef2;
	String out;

	public StatementDiv(String n1, String n2, String outp) {
		varRef1 = n1;
		varRef2 = n2;
		out = outp;
	}

	public double eval(List<Double> inputs, List<Double> state) {
		double ans = 0.0;
		if (state.get(Integer.parseInt(varRef2.substring(1))) == 0.0) {
			ans = 0.0;
		} else {
			ans = state.get(Integer.parseInt(varRef1.substring(1))) / state.get(Integer.parseInt(varRef2.substring(1)));
		}
		return ans;
	}

	public int whereTo() {
		return Integer.parseInt(out.substring(1));
	}
}
