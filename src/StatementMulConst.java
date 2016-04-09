import java.util.*;

public class StatementMulConst extends Statement {
	String varRef1;
	double con;
	String out;

	public StatementMulConst(String n1, double conp, String outp) {
		varRef1 = n1;
		con = conp;
		out = outp;
	}

	public double eval(List<Double> inputs, List<Double> state) {
		return state.get(Integer.parseInt(varRef1.substring(1))) * con;
	}

	public int whereTo() {
		return Integer.parseInt(out.substring(1));
	}

}
