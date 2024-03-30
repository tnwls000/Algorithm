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
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		int[][] visited = new int[n][m];
		for (int[] visit : visited) {
			Arrays.fill(visit, -1);
		}
		Point start = null;
		for (int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) start = new Point(r,c);
				if (map[r][c] == 0) visited[r][c] = 0;
			}
		}
		
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		Queue<Point> q = new LinkedList<>();
		visited[start.x][start.y] = 0;
		q.add(start);
		while (!q.isEmpty()) {
			Point p = q.poll();
			
			for (int i=0; i<4; i++) {
				int nr = p.x + dr[i];
				int nc = p.y + dc[i];
				
				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (visited[nr][nc] != -1) continue;
				visited[nr][nc] = visited[p.x][p.y] + 1;
				q.add(new Point(nr,nc));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int r=0; r<n; r++) {
			for (int c=0; c<m; c++) {
				sb.append(visited[r][c] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}