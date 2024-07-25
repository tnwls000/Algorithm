import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, spreadableCnt, result;
    static int INF = Integer.MAX_VALUE;
    static int[][] lab;
    static int[][] virus;
    static boolean[][] visited;
    static List<int[]> two;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void combination(int idx, int cnt) {
        if (cnt == m) {
            int spread = spreadVirus();
            if (spread != -1) result = Math.min(result, spread);
            return;
        }

        for (int i=idx; i<two.size(); i++) {
            virus[cnt][0] = two.get(i)[0];
            virus[cnt][1] = two.get(i)[1];
            combination(i+1, cnt+1);
        }
    }

    static int spreadVirus() {
        visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();

        for (int[] vi : virus) {
            q.add(new int[]{vi[0], vi[1], 0});
            visited[vi[0]][vi[1]] = true;
        }

        int cnt = 0;
        int spreadCnt = 0;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (lab[curr[0]][curr[1]] != 2) {
                cnt = Math.max(cnt, curr[2]);
                spreadCnt++;
            }

            for (int i=0; i<4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                if (nr<0 || nr>=n || nc<0 || nc>=n) continue;
                if (visited[nr][nc]) continue;
                if (lab[nr][nc] == 1) continue;

                visited[nr][nc] = true;

                q.add(new int[]{nr, nc, curr[2] + 1});
            }
        }

        if (spreadCnt < spreadableCnt) return -1;
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lab = new int[n][n];
        virus = new int[m][2];
        two = new ArrayList<>();
        spreadableCnt = 0;

        for (int r=0; r<n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0; c<n; c++) {
                lab[r][c] = Integer.parseInt(st.nextToken());
                if (lab[r][c] == 2) {
                    two.add(new int[]{r, c});
                }
                if (lab[r][c] == 0) spreadableCnt++;
            }
        }

        result = INF;
        combination(0,0);
        System.out.println(result == INF ? -1 : result);
    }
}