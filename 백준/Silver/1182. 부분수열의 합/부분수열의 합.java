import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int n, s, count, map[], sum;
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		map = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<n; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		count = 0;
		dfs(0, 0);
		if (s == 0) System.out.println(count - 1);
		else System.out.println(count);
	}
	
	private static void dfs(int current, int sum) {
		if (current == n) {
			if (sum == s) count++;
			return;
		}
		
		dfs(current + 1, sum + map[current]);
		dfs(current + 1, sum);
	}
}