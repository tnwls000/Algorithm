import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken()); // 너비(열)
			int n = Integer.parseInt(st.nextToken()); // 높이(행)
			
			char[][] maze = new char[n][m];
			int[][] fire = new int[n][m];
			int[][] sang = new int[n][m];
			Queue<Point> q = new LinkedList<>();
			for (int r = 0; r < n; r++) {
				Arrays.fill(fire[r], -1);
				Arrays.fill(sang[r], -1);
			}

			Point sangP = new Point();
			for (int r = 0; r < n; r++) {
				String str = br.readLine();
				for (int c = 0; c < m; c++) {
					maze[r][c] = str.charAt(c);
					if (maze[r][c] == '@') {
						sangP = new Point(r, c);
						sang[r][c] = 0;
					}
					if (maze[r][c] == '*') {
						fire[r][c] = 0;
						q.add(new Point(r,c));
					}
				}
			}

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };
			
			// 불부터 확산 확인
			while (!q.isEmpty()) {
				Point p = q.poll();
				int px = p.x;
				int py = p.y;
				for (int i = 0; i < 4; i++) {
					int nr = px + dr[i];
					int nc = py + dc[i];

					// 범위 넘으면 넘김
					if (nr < 0 || nr >= n || nc < 0 || nc >= m)
						continue;
					// 이미 방문했었거나 벽이면 넘김
					if (fire[nr][nc] != -1 || maze[nr][nc] == '#')
						continue;

					fire[nr][nc] = fire[px][py] + 1;
					q.add(new Point(nr, nc));
				}
			}
			
			// 지훈 탈출 여부 확인
			q.clear();
			q.add(sangP);
			
			int time = 0;
			loop: while (!q.isEmpty()) {
				Point p = q.poll();
				int px = p.x;
				int py = p.y;
				// 처음부터 가장자리에 있을 경우를 위한 if문
				if (px == 0 || px == n - 1 || py == 0 || py == m - 1) break loop;
				
				for (int i = 0; i < 4; i++) {
					int nr = px + dr[i];
					int nc = py + dc[i];
					
					// 범위 넘으면 넘김
					if (nr < 0 || nr >= n || nc < 0 || nc >= m)
						continue;

					// 이미 방문했었거나 벽이거나 불이면 넘김
					if (sang[nr][nc] != -1 || maze[nr][nc] == '#' || maze[nr][nc] == '*')
						continue;

					// 불이 지훈이보다 먼저 확산된거면 넘김
					if (fire[nr][nc] != -1 && sang[px][py] + 1 >= fire[nr][nc])
						continue;

					sangP.x = nr; // 마지막 위치 판단 위함
					sangP.y = nc;
					
					sang[nr][nc] = sang[px][py] + 1;
					time = sang[nr][nc]; // 시간 재기
					
					if (nr == 0 || nr == n - 1 || nc == 0 || nc == m - 1) // 가장자리면 멈춤
						break loop;
					q.add(new Point(nr, nc));
				}
			}
			
			// 지훈이 가장 자리에 있다면(-> 탈출 성공)
			if (sangP.x == 0 || sangP.x == n - 1 || sangP.y == 0 || sangP.y == m - 1) {
				sb.append(time+1).append("\n"); // 가장자리에 있을 때 break 걸어서 +1 해줌
			} else {
				sb.append("IMPOSSIBLE\n");
			}
		}
		System.out.println(sb);
	}
}