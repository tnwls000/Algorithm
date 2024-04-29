import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static long[] input;
	static Map<Long, Long> dp;
	static long dfs(long i) {
		if (dp.containsKey(i)) return dp.get(i);
		dp.put(i, dfs(i/input[1]) + dfs(i/input[2]));
		return dp.get(i);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		dp = new HashMap<>();
		dp.put(0L, 1L);
		
		System.out.println(dfs(input[0]));
	}
}