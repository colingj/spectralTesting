import java.util.*;

public class TargetExample4 implements Target {
	public double calculate(List<Double> inputs) {
		double v0 = inputs.get(0);
		double v1 = inputs.get(1);
		double v2 = inputs.get(2);
		double v3 = inputs.get(3);
		double v4 = v0+v1;
		double v5 = v2+v3;
		double v6 = v4*v2;
		double v7 = v3+v6;
		double v8 = v5*v7;
		double v9 = v0-v7;
		double v10 = v8+v6;
		return v10;
	}
}
