import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        
        int[][] sum = new int[n+1][m+1];
        // 누적합
        for (int[] s : skill) {
            int sr = s[1];
            int sc = s[2];
            int er = s[3];
            int ec = s[4];
            
            int degree = s[5] * (s[0]==1 ? -1 : 1);
            sum[sr][sc] += degree;
            sum[sr][ec+1] += degree * -1;
            sum[er+1][sc] += degree * -1;
            sum[er+1][ec+1] += degree;
        }
        
        // 누적합 계산
        for (int r=1; r<=n; r++) {
            for (int c=0; c<=m; c++) {
                sum[r][c] += sum[r-1][c];
            }
        }
        for (int c=1; c<=m; c++) {
            for (int r=0; r<=n; r++) {
                sum[r][c] += sum[r][c-1];
            }
        }
        
        int cnt = 0;
        for (int r=0; r<n; r++) {
            for (int c=0; c<m; c++) {
                if (board[r][c] + sum[r][c] > 0) cnt++;
            }
        }
        
        return cnt;
    }
}