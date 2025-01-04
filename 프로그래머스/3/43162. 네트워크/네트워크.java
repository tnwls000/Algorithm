import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int cnt = 0;
        boolean[] visited = new boolean[n];
        for (int i=0; i<computers.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            cnt++;
            
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            while (!q.isEmpty()) {
                int curr = q.poll();
                for (int next=0; next<n; next++) {
                    if (curr == next) continue;
                    if (visited[next]) continue;
                    if (computers[curr][next] == 0) continue;
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return cnt;
    }
}