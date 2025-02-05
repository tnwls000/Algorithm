import java.util.*;
import java.io.*;

public class Main {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int N,M;
	static int[][] sea, next;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sea = new int[N][M];
		
		for (int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<M; c++) {
				sea[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int t = 1;
		while (true) {
			next = new int[N][M];
			
			// 녹기
			melting();
			
			// 갱신된 map으로 복사
			for (int r=0; r<N; r++) {
				sea[r] = Arrays.copyOf(next[r], M);
			}
			
			// 덩어리 확인
			int cnt = bfs();
			if (cnt == 0) {
				t = 0;
				break;
			} else if (cnt > 1) break;
			t++;
		}
		System.out.println(t);
	}
	
	static void melting() {
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (sea[r][c] == 0) continue;
				int cnt = 0;
				for (int i=0; i<4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (nr<0 || nc<0 || nr>=N || nc>=M) continue;
					if (sea[nr][nc] == 0) cnt++;
				}
				next[r][c] = sea[r][c] <= cnt ? 0 : sea[r][c] - cnt;
			}
		}
	}
	
	static int bfs() {
		int cnt = 0;
		boolean[][] visited = new boolean[N][M];
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (sea[r][c] == 0 || visited[r][c]) continue;
				cnt++;
				if (cnt > 1) return 2;
				Queue<int[]> q = new LinkedList<>();
				q.add(new int[] {r,c});
				visited[r][c] = true;
				while (!q.isEmpty()) {
					int[] curr = q.poll();
					
					
					for (int i=0; i<4; i++) {
						int nr = curr[0] + dr[i];
						int nc = curr[1] + dc[i];
						if (nr<0 || nc<0 || nr>=N || nc>=M) continue;
						if (visited[nr][nc] || sea[nr][nc] == 0) continue;
						visited[nr][nc] = true;
						q.add(new int[] {nr,nc});
					}
				}
			}
		}
		return cnt;
	}
}