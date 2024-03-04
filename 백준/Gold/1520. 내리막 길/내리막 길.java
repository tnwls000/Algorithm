import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] map, dp;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int dfs(int r, int c) {
		if (r == n-1 && c == m-1) return 1;
		if (dp[r][c] != -1) return dp[r][c];
		
		dp[r][c] = 0;
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue; // 범위 넘으면 pass
			if (map[r][c] <= map[nr][nc]) continue; // 오르막이면 pass
			
			dp[r][c] += dfs(nr,nc);
		}
		
		return dp[r][c];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		dp = new int[n][m];
		for (int[] d : dp) {
			Arrays.fill(d, -1);
		}
		
		for (int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(0,0));
	}
}