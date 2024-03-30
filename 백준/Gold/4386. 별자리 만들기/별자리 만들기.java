import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	static class Star {
		double x,y;
		public Star(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Node implements Comparable<Node> {
		int v;
		double w;
		public Node(int v, double w) {
			super();
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return Double.compare(this.w, o.w);
		}
	}
	
	static double getDist(Star s1, Star s2) {
		return Math.sqrt(Math.pow(Math.abs(s1.x-s2.x),2) + Math.pow(Math.abs(s1.y-s2.y),2));
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		Star[] star = new Star[n];
		for (int i=0; i<n; i++) {
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			star[i] = new Star(x,y);
		}
		
		List<Node>[] list = new ArrayList[n];
		for (int i=0; i<n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i=0; i<n-1; i++) {
			for (int j=i+1; j<n; j++) {
				double dist = getDist(star[i], star[j]);
				list[i].add(new Node(j, dist));
				list[j].add(new Node(i, dist));
			}
		}
		
		boolean[] visited = new boolean[n];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		visited[0] = true;
		pq.addAll(list[0]);
		
		double ans = 0.0;
		int pick = 1;
		while (pick != n) {
			Node node = pq.poll();
			
			if (visited[node.v]) continue;
			visited[node.v] = true;
			ans += node.w;
			pick++;
			pq.addAll(list[node.v]);
		}
		System.out.printf("%.2f", ans);
	}
}