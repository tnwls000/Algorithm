import java.util.*;

class Solution {
    int[] X = {-1, 1, 0, 0};
    int[] Y = {0, 0, -1, 1};
    int n, m;
    int[][] visited;
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visited = new int[n][m];
        
        bfs(maps);
        int answer = visited[n-1][m-1];
        
        if (answer == 0) {
            return -1;
        } else return answer;
    }
    
    private void bfs(int[][] maps) {
        
        visited[0][0] = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        
        while (!q.isEmpty()) {
            int[] now = q.remove();
            int nowX = now[0];
            int nowY = now[1];
            for (int i=0; i<4; i++) {
                int nextX = nowX + X[i];
                int nextY = nowY + Y[i];
                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;
                
                if (visited[nextX][nextY] == 0 && maps[nextX][nextY] == 1) {
                    visited[nextX][nextY] = visited[nowX][nowY] + 1;
                    q.add(new int[]{nextX, nextY});
                }
            }  
        }
        
    }
}