import java.util.*;

class Solution {
    static final int INF = Integer.MAX_VALUE;
    static List<Node>[] list;
    static boolean[] visited;
    static int[] dist;
    
    static class Node implements Comparable<Node> {
        int v,w;
        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    
    static int[] dijkstra(int start, int n) {
        visited = new boolean[n+1];
        
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
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
        
        return dist;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        list = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i=0; i<fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];
            list[start].add(new Node(end, cost));
            list[end].add(new Node(start, cost));
        }
        
        int cost = INF;
        int[] share = dijkstra(s,n);
        for (int i=1; i<=n; i++) {
            int[] alone = dijkstra(i,n);
            cost = Math.min(cost, share[i] + alone[a] + alone[b]);
        }
        
        return cost;
    }
}