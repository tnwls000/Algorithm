import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int r,c,d;
        Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    static int n,m,visitArea;
    static final int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
    static final int[] dc = {0, 1, 0, -1}; // 북 동 남 서
    static int[][] map;
    static boolean[][] visited;
    static Point car;

    // 사방 좌회전 이동 가능 여부 함수
    static boolean checkTurnLeft() {
        int currR = car.r;
        int currC = car.c;
        int nextD = car.d;
        
        for (int i=0; i<4; i++) {
            nextD = (nextD + 3) % 4;
            int nextR = currR + dr[nextD];
            int nextC = currC + dc[nextD];
            
            if (map[nextR][nextC] == 1) continue;
            if (visited[nextR][nextC]) continue;

            visitArea++;
            visited[nextR][nextC] = true;
            car.r = nextR;
            car.c = nextC;
            car.d = nextD;

            return true;
        }

        return false;
    }

    static boolean checkGoBack() {
        int nextD = (car.d + 2) % 4;
        int nextR = car.r + dr[nextD];
        int nextC = car.c + dc[nextD];

        if (map[nextR][nextC] == 1) return false;

        car.r = nextR;
        car.c = nextC;

        return true;
    }

    public static void main(String[] args) throws IOException {
        // 좌회전 후 직진 가능 여부 판단(좌회전은 +3 % 4로 판단)
        // 4방향 모두 판단
        // -> 4방향 모두 안되면 후진
        // 후진 안되면 끝
        // 초기 위치도 방문한 것이라 판단하고 방문 면적 1로 시작해야 함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        car = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

        map = new int[n][m];
        visited = new boolean[n][m];
        visited[car.r][car.c] = true; // 초기 위치
        int totalRoadCnt = 0;

        for (int r=0; r<n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0; c<m; c++) {
                int road = Integer.parseInt(st.nextToken());
                map[r][c] = road;
                if (road == 0) totalRoadCnt++; 
            }
        }

        visitArea = 1;
        while (visitArea < totalRoadCnt) {
            if (checkTurnLeft()) continue;
            else {
                if (checkGoBack()) continue;
                else break;
            }
        }

        System.out.println(visitArea);
    }
}