import java.io.*;
import java.util.*;

public class Main {
    static int n, m, cnt;
    static int[] start;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static char[][] maze;
    static int bfs(int startR, int startC) {

        boolean[][][] visited = new boolean[n][m][64];
        visited[startR][startC][0] = true;
        cnt = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {startR,startC,0});
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s=0; s<size; s++) {
                int[] curr = q.poll();

                if (maze[curr[0]][curr[1]] == '1') {
                    return cnt;
                }

                for (int i=0; i<4; i++) {
                    int nr = curr[0] + dr[i];
                    int nc = curr[1] + dc[i];
                    int keys = curr[2];

                    if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
                    if (maze[nr][nc] == '#') continue;

                    char cell = maze[nr][nc];

                    if (cell >= 'a' && cell <= 'f') { // 열쇠
                        keys |= (1 << (cell - 'a')); // 해당 열쇠 추가
                    }
                    else if (cell >= 'A' && cell <= 'F') { // 문
                        if ((keys & (1 << (cell - 'A'))) == 0) { // 해당 문의 열쇠가 없으면 pass
                            continue;
                        }
                    }

                    if (!visited[nr][nc][keys]) {
                        visited[nr][nc][keys] = true;
                        q.add(new int[]{nr,nc, keys});
                    }
                }
            }
            cnt++;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = arr[0]; // 세로
        m = arr[1]; // 가로
        maze = new char[n][m];
        start = new int[2];

        for (int r=0; r<n; r++) {
            String[] str = br.readLine().split("");
            for (int c=0; c<m; c++) {
                maze[r][c] = str[c].charAt(0);

                if (maze[r][c] == '0') {
                    start[0] = r;
                    start[1] = c;
                }
            }
        }

        int result = bfs(start[0],start[1]);
        System.out.println(result);
    }
}