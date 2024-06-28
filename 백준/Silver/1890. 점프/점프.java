import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] board = new int[n][n];
        for (int r=0; r<n; r++) {
            for (int c=0; c<n; c++) {
                board[r][c] = sc.nextInt();
            }
        }

        long[][] dp = new long[n][n];
        dp[0][0] = 1;
        for (int r=0; r<n; r++) {
            for (int c=0; c<n; c++) {
                if (dp[r][c] == 0 || board[r][c] == 0) continue;
                if (c+board[r][c] < n) dp[r][c+board[r][c]] += dp[r][c];
                if (r+board[r][c] < n) dp[r+board[r][c]][c] += dp[r][c];
            }
        }

        System.out.println(dp[n-1][n-1]);
    }
}