import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 도화지 세로 크기
		int m = Integer.parseInt(st.nextToken()); // 도화지 가로 크기
		
		boolean[][] visited = new boolean[n][m]; // 방문 확인 배열
		int[][] paper = new int[n][m]; // 도화지
		// 그림 상태 입력 받기
		for (int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<m; c++) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dr = {-1, 1, 0, 0}; //델타배열
		int[] dc = {0, 0, -1, 1};
		
		int max = 0; // 그림 넓이 최댓값
		int cnt = 0; // 그림 수
		
		for (int r=0; r<n; r++) {
			for (int c=0; c<m; c++) {
				if (visited[r][c] || paper[r][c] == 0) continue;
				visited[r][c] = true; // 방문 처리
				cnt++;
				
				Queue<Point> q = new LinkedList<>();
				q.add(new Point(r,c));
				
				int area = 0;
				while (!q.isEmpty()) {
					area++;
					Point p = q.poll();
					for (int i=0; i<4; i++) {
						int nowR = p.x + dr[i];
						int nowC = p.y + dc[i];
						
						if (nowR < 0 || nowR >= n || nowC < 0 || nowC >= m) continue; // 범위 넘어갈 때
						
						// 방문하지 않았고 해당 칸이 1일 때
						if (!visited[nowR][nowC] && paper[nowR][nowC] == 1) {
							visited[nowR][nowC] = true;
							q.add(new Point(nowR, nowC));
						}
					}
				}
				max = Math.max(max, area);
				
			}
		}
		
		System.out.println(cnt);
		System.out.println(max);
	}
}