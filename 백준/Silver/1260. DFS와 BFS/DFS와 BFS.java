import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.util.Queue;
import java.util.LinkedList;

class Main {
	static boolean visit[];
	static int n, m, v, map[][];
	static Queue<Integer> q1, q2;
	static StringBuilder sb1, sb2;
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken()); //정점의 개수
		m = Integer.parseInt(st.nextToken()); //간선의 개수
		v = Integer.parseInt(st.nextToken()); //탐색을 시작할 번호
		
		map = new int[n+1][n+1];
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		visit = new boolean[n+1];
		visit[v] = true;
		sb1 = new StringBuilder();
		
		dfs(v);	
		bfs(v);
		
		
		sb1.deleteCharAt(sb1.length() - 1);
		sb2.deleteCharAt(sb2.length() - 1);
		System.out.println(sb1);
		System.out.println(sb2);
		
	}
	
	private static void dfs(int s) {
		sb1.append(s).append(" ");
		
		for (int i=1; i<=n; i++) {
			if (map[s][i] == 1 && !visit[i]) {
				visit[i] = true;
				dfs(i);
			}
		}
	}
	
	private static void bfs(int s) {
		visit = new boolean[n+1];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(s);
		
		sb2 = new StringBuilder();
		sb2.append(s).append(" ");
		visit[s] = true;
		
		while (!queue.isEmpty()) {
			int point = queue.poll();
				
			for (int i=1; i<=n; i++) {
				if (map[point][i] == 1 && !visit[i]) {
					visit[i] = true;
					queue.offer(i);
					sb2.append(i).append(" ");
				}
			}	
		}
	}
}