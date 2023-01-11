import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static boolean visited[], finished[];
	static int count, n, arr[];
	
	private static void dfs(int n) {
		if (finished[n]) return;
		if (visited[n]) {
			finished[n] = true;
			count++;
		}
		visited[n] = true;
		dfs(arr[n]);
		finished[n] = true;
		visited[n] = false;
	}
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int i=0; i<t; i++) {
			n = Integer.parseInt(br.readLine());
			count = 0;
			arr = new int[n+1];
			finished = new boolean[n+1];
			visited = new boolean[n+1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j=1; j<=n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			for (int j=1; j<=n; j++) {
				dfs(j);
			}
			System.out.println(n - count);
		}
	}
}