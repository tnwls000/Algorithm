import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Point {
        int r, c, cnt;
        boolean isBroke;
        Point(int r, int c, boolean isBroke, int cnt) {
            this.r = r;
            this.c = c;
            this.isBroke = isBroke;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        for (int r=0; r<n; r++) {
            board[r] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int[][][] visited = new int[n][m][2];
        for (int r=0; r<n; r++) {
            for (int c=0; c<m; c++) {
                Arrays.fill(visited[r][c], Integer.MAX_VALUE);
            }
        }
        visited[0][0][0] = 1;
        visited[0][0][1] = 1;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0,false, 1));
        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            for (int i=0; i<4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                int newCnt = curr.cnt + 1;
                if (nr<0 || nr>=n || nc<0 || nc>=m) continue; // 범위 넘으면

                if (board[nr][nc] == 0) {
                    if (curr.isBroke) {
                        if (visited[nr][nc][1] <= newCnt) continue;
                        visited[nr][nc][1] = newCnt;
                    } else {
                        if (visited[nr][nc][0] <= newCnt) continue;
                        visited[nr][nc][0] = newCnt;
                    }
                    queue.add(new Point(nr,nc,curr.isBroke,newCnt));
                } else {
                    if (visited[nr][nc][1] <= newCnt || curr.isBroke) continue;
                    visited[nr][nc][1] = newCnt;
                    queue.add(new Point(nr,nc,true,newCnt));
                }

                boolean newIsBroke = curr.isBroke;
                if (!curr.isBroke && board[nr][nc] == 1) newIsBroke = true;
                queue.add(new Point(nr,nc,newIsBroke,newCnt));
            }
        }

        int min = Math.min(visited[n-1][m-1][0], visited[n-1][m-1][1]);
        System.out.println((min == Integer.MAX_VALUE) ? -1 : min);
    }
}