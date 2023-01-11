import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static boolean visited[][];
	static int count, m, n, cabbage[][];
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, -1, 1, 0};
	
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for (int i=0; i<4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if (cx >= 0 && cy >= 0 && cx < m && cy < n) {
				if (!visited[cx][cy] && cabbage[cx][cy] == 1) { 
					dfs(cx, cy);
				}
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int i=0; i<tc; i++) {
			count = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			visited = new boolean[m][n];
			cabbage = new int[m][n];
			
			int k = Integer.parseInt(st.nextToken());
			for (int j=0; j<k; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				cabbage[p1][p2] = 1;
			}
			
			for (int x=0; x<m; x++) {
				for (int y=0; y<n; y++) {
					if (cabbage[x][y] == 1 && !visited[x][y]) {
						dfs(x, y);
						count++;
					}
				}
			}
			System.out.println(count);
			
		}
    }
}