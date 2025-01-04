import java.util.*;

class Solution {
    static class Node {
        int v, lv;
        Node(int v, int lv) {
            this.v=v;
            this.lv=lv;
        }
    }
    public int solution(int n, int[][] edge) {
        List<Integer>[] list = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i=0; i<edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            list[a].add(b);
            list[b].add(a);
        }
        
        int max = -1;
        int[] visited = new int[n+1];
        visited[1] = 1;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1,1));
        while(!q.isEmpty()) {
            Node curr = q.poll();
            
            for (int node : list[curr.v]) {
                if (visited[node] != 0) continue;
                
                visited[node] = curr.lv + 1;
                max = Math.max(max, visited[node]);
                q.add(new Node(node, visited[node]));  
            }
        }
        
        int cnt = 0;
        for (int v : visited) {
            if (max == v) cnt++;
        }
        
        return cnt;
    }
}