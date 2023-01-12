import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.util.Queue;
import java.util.LinkedList;

class Main {
	static boolean visit[];
	static int n, m, count, map[][];
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken()); //정점의 개수
		m = Integer.parseInt(st.nextToken()); //간선의 개수
		
		map = new int[n+1][n+1];
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		visit = new boolean[n+1];
		count = 0;
		for (int i=1; i<=n; i++) {
			if (visit[i]) continue;
			count++;
			dfs(i);
		}
		
		System.out.println(count);
	}
	
	private static void dfs(int s) {
		
		for (int i=1; i<=n; i++) {
			if (map[s][i] == 1 && !visit[i]) {
				visit[i] = true;
				dfs(i);
			}
		}
	}
}