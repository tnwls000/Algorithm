import java.io.*;
import java.util.*;

public class Main {
    static int n, black, white;
    static int[] dr = {1,-1,-1,1};
    static int[] dc = {1,1,-1,-1};
    static int[][] board;
    static int[][] chess;
    static boolean[][] visited;

    static void recur(int r, int c, int color, int cnt) {
        if (r >= n) {
            if (color == 0) black = Math.max(black, cnt);
            else white = Math.max(white, cnt);
            return;
        }

        int nr = r;
        int nc = c + 2;
        if (nc >= n) {
            nr++;
            if (nr < n) {
                if (chess[nr][0] == color) nc = 0;
                else nc = 1;
            }
        }

        if (board[r][c] == 0) {
            recur(nr,nc,color,cnt);
            return;
        }

        if (existsBishop(r,c)) {
            visited[r][c] = true;
            recur(nr,nc,color,cnt+1);
            visited[r][c] = false;
        }

        recur(nr,nc,color,cnt);

    }

    static boolean existsBishop(int r, int c) {
        for (int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            while (nr>=0 && nr<n && nc>=0 && nc<n) {
                if (visited[nr][nc]) return false;
                nr += dr[i];
                nc += dc[i];
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        visited = new boolean[n][n];

        for (int r=0; r<n; r++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int c=0; c<n; c++) {
                board[r][c] = arr[c];
            }
        }

        chess = new int[n][n];
        for (int r=0; r<n; r++) {
            for (int c=0; c<n; c++) {
                chess[r][c] = (r+c) % 2;
            }
        }

        black = 0;
        white = 0;

        recur(0,0, chess[0][0], 0);
        recur(0,1,chess[0][1], 0);

        System.out.println(black + white);
    }
}