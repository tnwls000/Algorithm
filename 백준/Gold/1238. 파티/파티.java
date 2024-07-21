import java.io.*;
import java.util.*;

public class Main {
    static int n,m,x,max;
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
        public int compareTo(Node o1) {
            return Integer.compare(this.w, o1.w);
        }
    }
    static void graph(int i) {
        visited = new boolean[n+1];
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[i] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(i, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visited[curr.v]) continue;
            visited[curr.v] = true;

            for (Node node : list[curr.v]) {
                if (!visited[node.v] && dist[node.v] > node.w + dist[curr.v]) {
                    dist[node.v] = node.w + dist[curr.v];
                    pq.add(new Node(node.v, dist[node.v]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        max = -1;

        list = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, w));
        }

        int[] totalDist = new int[n+1];

        graph(x);
        for (int i=1; i<=n; i++) { // x에서 각 집으로의 최단 거리
            totalDist[i] += dist[i];
        }

        for (int i=1; i<=n; i++) { // 각 집에서 x로의 최단 거리
            graph(i);
            totalDist[i] += dist[x];
        }

        System.out.println(Arrays.stream(totalDist).max().getAsInt());
    }
}