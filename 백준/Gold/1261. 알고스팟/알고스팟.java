import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node>{
		int r,c,cnt;
		Node(int r, int c, int cnt) {
			this.r=r;
			this.c=c;
			this.cnt=cnt;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int M = arr[0];
		int N = arr[1];
		int[][] board = new int[N][M];
		for (int i=0; i<N; i++) {
			board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		
		int[][] dist = new int[N][M];
		for (int i=0; i<N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		boolean[][] visited = new boolean[N][M];
		
		dist[0][0] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0,0,0));
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if (visited[curr.r][curr.c]) continue;
			visited[curr.r][curr.c] = true;
			
			for (int i=0; i<4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				
				if (nr<0 || nc<0 || nr>=N || nc>=M) continue;
				int wall = (board[nr][nc] == 1) ? 1 : 0;
				
				if (!visited[nr][nc] && dist[nr][nc] > dist[curr.r][curr.c] + wall) {
					dist[nr][nc] = dist[curr.r][curr.c] + wall;
					pq.add(new Node(nr,nc,dist[nr][nc]));
				}
				
			}
		}
		
		System.out.println(dist[N-1][M-1]);
	}
}