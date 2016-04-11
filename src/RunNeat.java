import com.anji.neat.*;
import com.anji.util.*;
import java.nio.file.*;
import java.util.regex.*;

public class RunNeat {
	public static void main(String[] args) throws Exception {
		run();
	}

	public static int[] run() throws Exception {
		Properties props = new Properties("C:\\Users\\jrw\\git\\spectralTesting\\myTest2.properties");
		Evolver evolver = new Evolver();
		evolver.init(props);
		evolver.run();
		String db = new String(Files.readAllBytes(Paths.get("db\\neatid.xml")));
		int[] complexities = new int[2];
		complexities = processFile(db);
		System.out.println("Complexities: " + complexities[0] + " " + complexities[1]);
		return complexities;
	}

	private static int[] processFile(String db) {
		int[] ans = new int[2];
		ans[0] = 0;
		ans[1] = 0;
		Pattern p = Pattern.compile("<neuron id");
		Matcher m = p.matcher(db);
		while (m.find()) {
			ans[0]++;
		}
		p = Pattern.compile("src_neuron_id");
		m = p.matcher(db);
		while (m.find()) {
			ans[1]++;
		}
		return ans;
	}

}
