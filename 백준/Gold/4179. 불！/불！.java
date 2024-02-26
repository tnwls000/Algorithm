import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 행
		int m = Integer.parseInt(st.nextToken()); // 열

		char[][] maze = new char[n][m];
		int[][] fire = new int[n][m];
		int[][] jihoon = new int[n][m];
		Queue<Point> q = new LinkedList<>();
		for (int r = 0; r < n; r++) {
			Arrays.fill(fire[r], -1);
			Arrays.fill(jihoon[r], -1);
		}

		Point jihoonP = new Point();
		for (int r = 0; r < n; r++) {
			String str = br.readLine();
			for (int c = 0; c < m; c++) {
				maze[r][c] = str.charAt(c);
				if (maze[r][c] == 'J') {
					jihoonP = new Point(r, c);
					jihoon[r][c] = 0;
				}
				if (maze[r][c] == 'F') {
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
		q.add(jihoonP);
		
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
				if (jihoon[nr][nc] != -1 || maze[nr][nc] == '#' || maze[nr][nc] == 'F')
					continue;

				// 불이 지훈이보다 먼저 확산된거면 넘김
				if (fire[nr][nc] != -1 && jihoon[px][py] + 1 >= fire[nr][nc])
					continue;

				jihoonP.x = nr; // 마지막 위치 판단 위함
				jihoonP.y = nc;
				
				jihoon[nr][nc] = jihoon[px][py] + 1;
				time = jihoon[nr][nc]; // 시간 재기
				
				if (nr == 0 || nr == n - 1 || nc == 0 || nc == m - 1) // 가장자리면 멈춤
					break loop;
				q.add(new Point(nr, nc));
			}
		}

		// 지훈이 가장 자리에 있다면(-> 탈출 성공)
		if (jihoonP.x == 0 || jihoonP.x == n - 1 || jihoonP.y == 0 || jihoonP.y == m - 1) {
			System.out.println(time + 1); // 가장자리에 있을 때 break 걸어서 +1 해줌
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}
}