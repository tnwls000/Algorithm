import java.util.*;
import java.io.*;

public class Main {
	static class Point {
		int r,c,p;
		Point(int r, int c, int p) {
			this.r=r;
			this.c=c;
			this.p=p;
		}
	}
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	static int N,min;
	static int[][] board;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		board = new int[N][N];
		visited = new boolean[N][N];
		for (int r=0; r<N; r++) {
			board[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int number = 2;
		for (int r=0; r<N; r++) {
			for (int c=0; c<N; c++) {
				if (board[r][c] == 1) dfs(r,c,number++);
			}
		}
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<N; c++) {
				if (board[r][c] > 1) bfs(r,c,board[r][c]); 
			}
		}
		
		System.out.println(min);
	}
	
	static void bfs(int r, int c, int number) {
		visited = new boolean[N][N];
		Queue<Point> q = new LinkedList<>();
		visited[r][c] = true;
		q.add(new Point(r,c,0));
		while (!q.isEmpty()) {
			Point point = q.poll();
			if (min < point.p) return;
			
			for (int i=0; i<4; i++) {
				int nr = point.r + dr[i];
				int nc = point.c + dc[i];
				if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
				
				if (board[nr][nc] == number) continue;
				if (visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				if (board[point.r][point.c] == 0 && board[nr][nc] > 1) min = Math.min(min, point.p);
				else q.add(new Point(nr,nc,point.p+1));
			}
		}
	}
	
	static void dfs(int r, int c, int number) {
		board[r][c] = number;
		visited[r][c] = true;
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
			if (visited[nr][nc] || board[nr][nc] != 1) continue;	
			dfs(nr,nc,number);
		}
	}
}