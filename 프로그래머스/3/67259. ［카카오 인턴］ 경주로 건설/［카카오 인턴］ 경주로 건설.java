import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int minCost = Integer.MAX_VALUE;
        boolean[][][] check = new boolean[n][n][4];
        
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,-1,0});
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == n-1 && cur[1] == n-1) {
                minCost = Math.min(minCost, cur[3]);
            }
        
            for (int i=0; i<4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if (nr>=n || nc>=n || nr<0 || nc<0) continue;
                if (board[nr][nc] == 1) continue;
                
                int nowCost = (cur[2] == -1 || cur[2] == i) ? cur[3]+100 : cur[3]+600;
                
                if (!check[nr][nc][i] || board[nr][nc] >= nowCost) {
                    check[nr][nc][i] = true;
                    board[nr][nc] = nowCost;
                    q.add(new int[]{nr,nc,i,nowCost});
                }
            }
        }
        
        return minCost;
    }
}