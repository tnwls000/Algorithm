import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int r,c;
		Node(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = arr[0];
		int M = arr[1];
		ArrayList<Node>[][] adjList = new ArrayList[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) adjList[i][j] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adjList[arr[0]-1][arr[1]-1].add(new Node(arr[2]-1, arr[3]-1));
		}
		
		boolean[][] light = new boolean[N][N];
		boolean[][] visited = new boolean[N][N];
		light[0][0] = true;
		visited[0][0] = true;
		
		int cnt = 1;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0));
		while (!q.isEmpty()) {
			Node curr = q.poll();
			
			if (!adjList[curr.r][curr.c].isEmpty()) {
				visited = new boolean[N][N];
				visited[curr.r][curr.c] = true;
				// 불켜기
				for (Node next : adjList[curr.r][curr.c]) {
					if (!light[next.r][next.c]) {
						light[next.r][next.c] = true;
						cnt++;
					}
				}
				adjList[curr.r][curr.c].clear();
			}
			
			// 이동 가능 여부 확인
			for (int i=0; i<4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				
				if (nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if (light[nr][nc] && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new Node(nr,nc));
				}
			}
		}
		
		System.out.println(cnt);
	}
}