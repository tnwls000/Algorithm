import java.util.*;

class Solution {
    static final int MAX = Integer.MAX_VALUE;
    static int N,M;
    static final int[] dr = {-1,1,0,0};
    static final int[] dc = {0,0,-1,1};
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        int[][] visited = new int[N][M];
        Queue<int[]> q = new LinkedList<>();
        
        visited[0][0] = 1;
        q.add(new int[] {0,0});
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0] == N - 1 && curr[1] == M - 1) break;
            
            for (int i=0; i<4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];
                
                if (nr<0 || nr>=N || nc<0 || nc>=M) continue;
                if (maps[nr][nc] == 0) continue;
                if (visited[nr][nc] != 0) continue;
                
                visited[nr][nc] = visited[curr[0]][curr[1]] + 1;
                q.add(new int[] {nr,nc});
            }
        }
        
        if (visited[N - 1][M - 1] == 0) return -1;
        return visited[N - 1][M - 1];
    }
}