import java.util.*;

//ground truth
public class TargetExampleQuad implements Target {
	public double calculate(List<Double> inputs) {
		return (-inputs.get(1) + Math.sqrt(inputs.get(1) * inputs.get(1) + 4.0 * inputs.get(0) * inputs.get(2))) / (2.0 * inputs.get(0));
	}
}
