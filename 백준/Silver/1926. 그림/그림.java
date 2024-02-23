import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
// dfs로 풀기
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 도화지 세로 크기
		int m = Integer.parseInt(st.nextToken()); // 도화지 가로 크기

		boolean[][] visited = new boolean[n][m]; // 방문 확인 배열
		int[][] paper = new int[n][m]; // 도화지
		// 그림 상태 입력 받기
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[] dr = { -1, 1, 0, 0 }; // 델타배열
		int[] dc = { 0, 0, -1, 1 };

		int max = 0; // 그림 넓이 최댓값
		int cnt = 0; // 그림 수

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (visited[r][c] || paper[r][c] == 0)
					continue;
				visited[r][c] = true;
				cnt++;

				int area = 0;
				Stack<Point> s = new Stack<>();
				s.add(new Point(r, c));
				while (!s.isEmpty()) {
					Point p = s.pop();
					area++;

					for (int i = 0; i < 4; i++) {
						int nr = p.x + dr[i];
						int nc = p.y + dc[i];

						if (nr < 0 || nr >= n || nc < 0 || nc >= m)
							continue;
						if (!visited[nr][nc] && paper[nr][nc] == 1) {
							visited[nr][nc] = true;
							s.add(new Point(nr, nc));
						}
					}
				}
				max = Math.max(max, area);
			}
		}
		System.out.println(cnt + "\n" + max);
	}
}