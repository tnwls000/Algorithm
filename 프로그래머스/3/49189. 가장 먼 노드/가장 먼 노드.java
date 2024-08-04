import java.io.*;
import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int v, level;
        Node(int v, int level) {
            this.v = v;
            this.level = level;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.level - o.level;
        }
    }
    
    public int solution(int n, int[][] edge) {
        List<Integer>[] list = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i=0; i<edge.length; i++) {
            int start = edge[i][0];
            int end = edge[i][1];
            list[start].add(end);
            list[end].add(start);
        }
        
        int[] visited = new int[n+1];
        Arrays.fill(visited, -1);
        visited[1] = 0;
        
        int max = -1;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            max = Math.max(max, curr.level);
            
            for (int next : list[curr.v]) {
                if (visited[next] == -1) {
                    visited[next] = visited[curr.v] + 1;
                    pq.add(new Node(next, visited[curr.v] + 1));
                }
            }
        }
        
        int cnt = 0;
        for (int v : visited) {
            if (v == max) cnt++;
        }
        return cnt;
    }
}