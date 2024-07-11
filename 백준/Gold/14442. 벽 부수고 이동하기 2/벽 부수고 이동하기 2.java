import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Point {
        int r, c, wallCnt, moveCnt;
        Point(int r, int c, int wallCnt, int moveCnt) {
            this.r = r;
            this.c = c;
            this.wallCnt = wallCnt;
            this.moveCnt = moveCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        for (int r=0; r<n; r++) {
            board[r] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int[][][] visited = new int[n][m][k+1];
        visited[0][0][0] = 1;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0,0, 1));
        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            if (curr.r == n-1 && curr.c == m-1) {
                System.out.println(visited[curr.r][curr.c][curr.wallCnt]);
                return;
            }

            for (int i=0; i<4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                int newMoveCnt = curr.moveCnt + 1;
                if (nr<0 || nr>=n || nc<0 || nc>=m) continue; // 범위 넘으면

                if (board[nr][nc] == 0 && visited[nr][nc][curr.wallCnt] == 0) {
                    visited[nr][nc][curr.wallCnt] = newMoveCnt;
                    queue.add(new Point(nr,nc,curr.wallCnt,newMoveCnt));
                } else if (board[nr][nc] == 1 && curr.wallCnt < k && visited[nr][nc][curr.wallCnt+1] == 0) {
                    visited[nr][nc][curr.wallCnt+1] = newMoveCnt;
                    queue.add(new Point(nr,nc, curr.wallCnt+1,newMoveCnt));
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i=0; i<=k; i++) min = Math.min(min, visited[n - 1][m - 1][i]);
        System.out.println((min == 0) ? -1 : min);
    }
}