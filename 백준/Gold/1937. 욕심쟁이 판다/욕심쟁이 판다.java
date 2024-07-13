import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int max;
    static int[][] forest;
    static int[][] dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int dfs(int r, int c) {
        if (dp[r][c] != 0) return dp[r][c];
        dp[r][c] = 1;

        for (int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr<0 || nr>=n || nc<0 || nc>=n) continue;
            if (forest[nr][nc] > forest[r][c]) {
                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
            }
        }

        return dp[r][c];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        forest = new int[n][n];
        dp = new int[n][n];
        for (int r=0; r<n; r++) {
            forest[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        max = -1;
        for (int r=0; r<n; r++) {
            for (int c = 0; c < n; c++) {
                max = Math.max(max, dfs(r, c));
            }
        }

        System.out.println(max);
    }
}