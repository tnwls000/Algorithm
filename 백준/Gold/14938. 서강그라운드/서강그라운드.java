import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int v,w;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[] items = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Node>[] list = new ArrayList[n+1];
		for (int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b,w));
			list[b].add(new Node(a,w));
		}
		
		int maxItem = 0;
		for (int i=1; i<=n; i++) {
			// i : 예은이가 떨어진 위치
			boolean[] visited = new boolean[n+1];
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(i, 0));
			
			int[] dist = new int[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[i] = 0;

			while (!pq.isEmpty()) {
				Node curr = pq.poll();

				if (visited[curr.v]) continue;
				visited[curr.v] = true;
				
				for (Node node : list[curr.v]) {
					if (!visited[node.v] && dist[node.v] > dist[curr.v] + node.w) {
						dist[node.v] = dist[curr.v] + node.w;
						pq.add(new Node(node.v, dist[node.v]));
					}
				}
			}
			
			int item = 0;
			for (int j=1; j<=n; j++) {
				if (dist[j] <= m) item += items[j]; 
			}
			maxItem = Math.max(maxItem, item);
		}
		System.out.println(maxItem);
	}
}