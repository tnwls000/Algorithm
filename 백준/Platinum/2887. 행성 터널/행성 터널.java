import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	static class Edge implements Comparable<Edge> {
		int a,b,w;

		public Edge(int a, int b, int w) {
			super();
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
		
	}
	
	static int[] p;

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
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (int i=0; i<n-1; i++) {
			pq.add(new Edge(x[i].idx, x[i+1].idx, Math.abs(x[i].point - x[i+1].point)));
			pq.add(new Edge(y[i].idx, y[i+1].idx, Math.abs(y[i].point - y[i+1].point)));
			pq.add(new Edge(z[i].idx, z[i+1].idx, Math.abs(z[i].point - z[i+1].point)));
			
		}
		
		p = new int[n];
		for (int i=0; i<n; i++) {
			p[i] = i;
		}
		
		int ans = 0;
		int pick = 0;
		while (pick != n-1) {
			Edge edge = pq.poll();
			int px = findset(edge.a);
			int py = findset(edge.b);
			if (px != py) {
				union(px, py);
				pick++;
				ans += edge.w;
			}
		}
		System.out.println(ans);
	}

	static void union(int x, int y) {
		p[y] = x;
	}

	static int findset(int a) {
		if (a != p[a]) p[a] = findset(p[a]);
		return p[a];
	}
}