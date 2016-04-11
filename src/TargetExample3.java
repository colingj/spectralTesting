import java.util.*;

public class TargetExample3 implements Target {
	public double calculate(List<Double> inputs) {
		double v8 = inputs.get(0)+inputs.get(1);
		double v9 = inputs.get(2)+inputs.get(3);
		double v10 = inputs.get(4)+inputs.get(5);
		double v11 = inputs.get(6)+inputs.get(7);
		double v12 = v8*v9;
		double v13 = v10*v11;
		double v14 = v12*v13;
		return v14;
	}
}
