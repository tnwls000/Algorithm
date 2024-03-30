import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Planet {
		int point,idx;

		public Planet(int point, int idx) {
			super();
			this.point = point;
			this.idx = idx;
		}
	}
	static class Node implements Comparable<Node> {
		int v;
		int w;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.w, o.w);
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Planet[] x = new Planet[n];
		Planet[] y = new Planet[n];
		Planet[] z = new Planet[n];
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// Planet x : 좌표, Planet y : 정점 번호
			x[i] = new Planet(Integer.parseInt(st.nextToken()), i);
			y[i] = new Planet(Integer.parseInt(st.nextToken()), i);
			z[i] = new Planet(Integer.parseInt(st.nextToken()), i);
		}
		
		Arrays.sort(x, (o1,o2) -> {
			return o1.point - o2.point;
		});
		Arrays.sort(y, (o1,o2) -> {
			return o1.point - o2.point;
		});
		Arrays.sort(z, (o1,o2) -> {
			return o1.point - o2.point;
		});
		
		List<Node>[] list = new ArrayList[n];
		for (int i=0; i<n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i=0; i<n-1; i++) {
			list[x[i].idx].add(new Node(x[i+1].idx, Math.abs(x[i].point-x[i+1].point)));
			list[x[i+1].idx].add(new Node(x[i].idx, Math.abs(x[i].point-x[i+1].point)));
			
			list[y[i].idx].add(new Node(y[i+1].idx, Math.abs(y[i].point-y[i+1].point)));
			list[y[i+1].idx].add(new Node(y[i].idx, Math.abs(y[i].point-y[i+1].point)));
			
			list[z[i].idx].add(new Node(z[i+1].idx, Math.abs(z[i].point-z[i+1].point)));
			list[z[i+1].idx].add(new Node(z[i].idx, Math.abs(z[i].point-z[i+1].point)));
		}
		
		boolean[] visited = new boolean[n];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.addAll(list[0]);
		visited[0] = true;
		int ans = 0;
		int pick = 1;
		while (pick != n) {
			Node node = pq.poll();
			
			if (visited[node.v]) continue;
			visited[node.v] = true;
			ans += node.w;
			pick++;
			pq.addAll(list[node.v]);
		}
		System.out.println(ans);
	}
}