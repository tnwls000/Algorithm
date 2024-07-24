import java.io.*;
import java.util.*;

public class Main {
    static int n,m,zeroCnt,result;
    static int[][] map;
    static int[][] emptyCells;
    static int[][] combinationArr;
    static List<int[]> virus;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static void combination(int idx, int cnt) {
        if (cnt == 3) {
            result = Math.max(result, spreadVirus());
            return;
        }

        for (int i=idx; i<zeroCnt; i++) {
            combinationArr[cnt][0] = emptyCells[i][0];
            combinationArr[cnt][1] = emptyCells[i][1];
            combination(i+1, cnt+1);
        }
    }

    static int spreadVirus() {
        int[][] newMap = new int[n][m];
        for (int r=0; r<n; r++) {
            newMap[r] = Arrays.copyOf(map[r], map[r].length);
        }
        for (int[] arr : combinationArr) newMap[arr[0]][arr[1]] = 1;

        Queue<int[]> q = new LinkedList<>(virus);

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int i=0; i<4; i++) {
                int nr = curr[0]+dr[i];
                int nc = curr[1]+dc[i];

                if (nr<0 || nc<0 || nr>=n || nc>=m) continue;

                if (newMap[nr][nc]==0) {
                    newMap[nr][nc] = 2;
                    q.add(new int[]{nr,nc});
                }
            }
        }

        int cnt = 0;
        for (int r=0; r<n; r++) {
            cnt += (int) Arrays.stream(newMap[r]).filter(i -> i==0).count();
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        n = Integer.parseInt(arr[0]);
        m = Integer.parseInt(arr[1]);
        map = new int[n][m];

        zeroCnt = 0;
        virus = new ArrayList<>();
        for (int r=0; r<n; r++) {
            map[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            zeroCnt += (int) Arrays.stream(map[r]).filter(i->i==0).count();
            for (int c=0; c<m; c++) {
                if (map[r][c] == 2) {
                    virus.add(new int[]{r,c});
                }
            }
        }

        int idx = 0;
        emptyCells = new int[zeroCnt][2];
        for (int r=0; r<n; r++) {
            for (int c=0; c<m; c++) {
                if (map[r][c]==0) {
                    emptyCells[idx][0] = r;
                    emptyCells[idx][1] = c;
                    idx++;
                }
            }
        }

        combinationArr = new int[3][2];
        result = 0;
        combination(0, 0);

        System.out.println(result);
    }
}