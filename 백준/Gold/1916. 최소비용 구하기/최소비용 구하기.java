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
		int v, w;

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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		List<Node>[] list = new ArrayList[n+1];
		for (int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		int[] cnt = new int[n+1];
		for (int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int in = Integer.parseInt(st.nextToken());
			int out = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[in].add(new Node(out,w));
			cnt[out]++;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int targetIn = Integer.parseInt(st.nextToken());
		int targetOut = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		boolean[] visited = new boolean[n+1];
		pq.add(new Node(targetIn, 0));
		dist[targetIn] = 0;
		
		loop: while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (curr.v == targetOut) break loop;
			
			if (visited[curr.v]) continue;
			visited[curr.v] = true;
			
			for (Node node : list[curr.v]) {
				if (!visited[node.v] && dist[node.v] > dist[curr.v] + node.w) {
					dist[node.v] = dist[curr.v] + node.w;
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}
		System.out.println(dist[targetOut]);
	}
}