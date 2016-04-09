import java.util.*;

public class TargetExample1 implements Target {
	public double calculate(List<Double> inputs) {
		double ans = 0;
		for (Double i : inputs) {
			ans += i;
		}
		return ans;
	}
}
