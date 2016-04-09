import java.util.*;

public class TargetExample2 implements Target {
	public double calculate(List<Double> inputs) {
		return (inputs.get(0)-inputs.get(1)) + (inputs.get(2)-inputs.get(3));
	}
}
