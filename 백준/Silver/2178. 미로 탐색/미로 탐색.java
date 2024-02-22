import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] maze = new int[n][m];
		int[][] visited = new int[n][m];
		for (int[] v : visited) {
			Arrays.fill(v, -1); // 방문 배열로 이동거리 셀 것임
		}

		for (int r = 0; r < n; r++) {
			String str = br.readLine();
			for (int c = 0; c < m; c++) {
				maze[r][c] = str.charAt(c) - '0';
			}
		}

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0)); // 시작위치
		visited[0][0] = 1;

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nowR = p.x + dr[i];
				int nowC = p.y + dc[i];

				if (nowR < 0 || nowR >= n || nowC < 0 || nowC >= m)
					continue;
				if (visited[nowR][nowC] != -1 || maze[nowR][nowC] == 0)
					continue;
				visited[nowR][nowC] = visited[p.x][p.y] + 1;
				q.add(new Point(nowR, nowC));
			}
		}

		System.out.println(visited[n - 1][m - 1]);
	}
}