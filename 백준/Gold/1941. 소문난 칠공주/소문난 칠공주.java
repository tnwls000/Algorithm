import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static char[][] seat = new char[5][5];
	static boolean[][] memo = new boolean[5][5];
	static boolean[][] memo2 = new boolean[5][5];
	static Point[] combi = new Point[7];
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int result;
	
	static boolean bfs(Point[] combi) {
		memo2 = new boolean[5][5];
		memo2[combi[0].x][combi[0].y] = true;
		
		Queue<Point> q = new LinkedList<>();
		q.add(combi[0]);
		
		int cnt = 1;
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int j=0; j<4; j++) {
				int r = p.x + dr[j];
				int c = p.y + dc[j];
				if (r<0 || r>=5 || c<0 || c>=5) continue;
				if (memo[r][c] && !memo2[r][c]) {
					memo2[r][c] = true;
					q.add(new Point(r,c));
					cnt++;
				}
			}
		}

		if (cnt == 7) return true;
		else return false;
	}
	
	static void recur(int nr, int nc, int cnt, int dasom) {
		if (cnt == 7) {
			if (dasom >= 4 && bfs(combi)) result++;
			return;
		}
		
		int c = nc;
		for (int r=nr; r<5; r++) {
			for (; c<5; c++) {
				combi[cnt] = new Point(r,c);
				memo[r][c] = true;
				if (seat[r][c] == 'S') dasom++;
				
				if (c==4) recur(r+1,0,cnt+1, dasom);
				else recur(r,c+1,cnt+1, dasom);
				
				memo[r][c] = false;
				if (seat[r][c] == 'S') dasom--;
			}
			c = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int r=0; r<5; r++) {
			String tmp = br.readLine();
			for (int c=0; c<5; c++) {
				seat[r][c] = tmp.charAt(c);
			}
		}
		
		recur(0,0,0,0);
		
		System.out.println(result);
	}
}