import java.io.*;
import java.util.*;

public class Main {
    static int k,h,w;
    static int[][] board;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[] horseR = {-1,-2,-2,-1,1,2,2,1};
    static int[] horseC = {-2,-1,1,2,2,1,-1,-2};

    static int bfs() {
        boolean[][][] visited = new boolean[h][w][k+1];

        int cnt = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0});
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s=0; s<size; s++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];
                int horseCnt = curr[2];

                if (r == h-1 && c == w-1) return cnt;

                if (horseCnt < k) { // 아직 말 움직임 가능
                    for (int i=0; i<8; i++) {
                        int nr = r + horseR[i];
                        int nc = c + horseC[i];

                        if (nr<0 || nr>=h || nc<0 || nc>=w) continue;
                        if (board[nr][nc] == 1) continue;

                        if (!visited[nr][nc][horseCnt + 1]) {
                            visited[nr][nc][horseCnt + 1] = true;
                            q.add(new int[]{nr,nc,horseCnt+1});
                        }
                    }
                }

                for (int i=0; i<4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr<0 || nr>=h || nc<0 || nc>=w) continue;
                    if (board[nr][nc] == 1) continue;

                    if (!visited[nr][nc][horseCnt]) {
                        visited[nr][nc][horseCnt] = true;
                        q.add(new int[]{nr,nc,horseCnt});
                    }
                }

            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        w = arr[0];
        h = arr[1];
        board = new int[h][w];
        for (int r=0; r<h; r++) {
            board[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs());
    }
}