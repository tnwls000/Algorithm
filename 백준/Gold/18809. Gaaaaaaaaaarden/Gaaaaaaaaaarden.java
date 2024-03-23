import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, g, r, result, flower;
	static int[][] garden;
	static Point[][] memo; // x: 시간, y: G(1)/R(2)
	static Point[] green, red, possibleGround;
	static boolean[] memoGround;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};

	static void combiG(int idx, int cnt) { // 초록색 배양액 조합
		if (cnt == g) {
			combiR(0,0, green);
			return;
		}
		if (idx >= possibleGround.length)
			return;

		for (int i = idx; i < possibleGround.length; i++) {
			if (memoGround[i]) continue;

			green[cnt] = possibleGround[i];
			memoGround[i] = true;
			combiG(i + 1, cnt + 1);
			memoGround[i] = false;
		}
	}

	static void combiR(int idx, int cnt, Point[] green) { // 빨간색 배양액 조합
		if (cnt == r) {
			bloom(green, red);
			return;
		}
		if (idx >= possibleGround.length)
			return;

		for (int i = idx; i < possibleGround.length; i++) {
			if (memoGround[i]) continue;

			red[cnt] = possibleGround[i];
			memoGround[i] = true;
			combiR(i + 1, cnt + 1, green);
			memoGround[i] = false;
		}
	}
	
	static void bloom(Point[] green, Point[] red) {
		Queue<Point> greenQ = new LinkedList<>();
		Queue<Point> redQ = new LinkedList<>();
		
		flower = 0;
		memo = new Point[n][m];
		for (Point p : green) {
			greenQ.add(p);
			memo[p.x][p.y] = new Point(0, 1);
		}
		for (Point p : red) {
			redQ.add(p);
			memo[p.x][p.y] = new Point(0, 2);
		}
		
		int cnt = 0;
		int time = 1;
		// 종료 조건 : 이번에 도달한 곳이 없을 때(cnt == 0)
		while (true) {
			cnt = 0;
			
			int greenSize = greenQ.size();
			for (int i=0; i<greenSize; i++) {
				Point p = greenQ.poll();
				if (memo[p.x][p.y].y == 3) continue;
				
				for (int j=0; j<4; j++) {
					int nr = p.x + dr[j];
					int nc = p.y + dc[j];
					
					if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
					if (garden[nr][nc] == 0) continue; // 호수면 넘김
					if (memo[nr][nc] != null) continue; // 이미 도달했던 곳 넘김
					
					greenQ.add(new Point(nr,nc));
					memo[nr][nc] = new Point(time, 1);
					cnt++;
				}
			}
			
			int redSize = redQ.size();
			for (int i=0; i<redSize; i++) {
				Point p = redQ.poll();
				for (int j=0; j<4; j++) {
					int nr = p.x + dr[j];
					int nc = p.y + dc[j];
					
					if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
					if (garden[nr][nc] == 0) continue; // 호수면 넘김
					if (memo[nr][nc] != null) {
						if (memo[nr][nc].x == time && memo[nr][nc].y == 1) {
							flower++;
							memo[nr][nc].y = 3; // 꽃으로 처리
						} else continue;
						
					} else {
						redQ.add(new Point(nr,nc));
						memo[nr][nc] = new Point(time, 2);
						cnt++;
					}
				}
			}
			
			if (cnt == 0) break;
			time++;
		}
		result = Math.max(result, flower);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = tmp[0];
		m = tmp[1];
		g = tmp[2];
		r = tmp[3];

		garden = new int[n][m];
		memo = new Point[n][m];
		green = new Point[g];
		red = new Point[r];
		int possibleGroundCnt = 0;
		for (int r = 0; r < n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				garden[r][c] = Integer.parseInt(st.nextToken());
				if (garden[r][c] == 2) {
					possibleGroundCnt++;
				}
			}
		}

		int idx = 0;
		possibleGround = new Point[possibleGroundCnt];
		memoGround = new boolean[possibleGroundCnt];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (garden[r][c] == 2) {
					possibleGround[idx++] = new Point(r, c);
				}
			}
		}

		combiG(0,0);
		System.out.println(result);
	}
}