import java.util.*;
import java.io.*;

public class Main {
	static class Point {
		int r,c;
		Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		test: for (int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int hr = Integer.parseInt(st.nextToken());
			int hc = Integer.parseInt(st.nextToken());
			
			Point[] stores = new Point[n];
			for (int s=0; s<n; s++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				stores[s] = new Point(r,c);
			}
			
			st = new StringTokenizer(br.readLine());
			int fr = Integer.parseInt(st.nextToken());
			int fc = Integer.parseInt(st.nextToken());
			
			Queue<Point> q = new LinkedList<>();
			boolean[] visited = new boolean[n];
			q.add(new Point(hr,hc));
			while (!q.isEmpty()) {
				Point curr = q.poll();
				if (Math.abs(curr.r - fr) + Math.abs(curr.c - fc) <= 1000) {
					System.out.println("happy");
					continue test;
				}
				
				for (int i=0; i<n; i++) {
					if (visited[i]) continue;
					
					Point store = stores[i];
					if (Math.abs(curr.r - store.r) + Math.abs(curr.c - store.c) <= 1000) {
						visited[i] = true;
						q.add(store);
					}
				}
			}
			
			System.out.println("sad");
		}
		
		
	}
}