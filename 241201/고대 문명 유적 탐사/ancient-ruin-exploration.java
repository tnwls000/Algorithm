import java.util.*;
import java.io.*;

public class Main {
    static int k, m;
    static final int[] dr = {-1,1,0,0};
    static final int[] dc = {0,0,-1,1};
    static int[][] map = new int[5][5];
    static int[][] tmpMaxMap = new int[5][5];
    static int[][] tmpMap = new int[5][5];
    static int[][] exploreMid = {
        {1,1},{2,1},{3,1},{1,2},{2,2},{3,2},{1,3},{2,3},{3,3}
    };
    static Queue<Integer> wallJewel;

    static void rotate(int x, int y, int d) {
        copyMap(tmpMap, map);

        int[][] rotateArr = new int[3][3];
        for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j)
				if (d == 1) // 90도 회전
					rotateArr[i][j] = tmpMap[3 - j + x - 2][i + y - 1];
				else if (d == 2) // 180도 회전
					rotateArr[i][j] = tmpMap[3 - i + x - 2][3 - j + y - 2];
				else // 270도 회전
					rotateArr[i][j] = tmpMap[j + x - 1][3 - i + y - 2];
		}

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j)
				tmpMap[i + x - 1][j + y - 1] = rotateArr[i][j];
		}
    }

    static int bfsJewel() {
        int jewelCnt = 0;
        boolean[][] visited = new boolean[5][5];
        Queue<int[]> q = new LinkedList<>();

        for (int r=0; r<5; r++) {
            for (int c=0; c<5; c++) {
                if (visited[r][c]) continue;
                visited[r][c] = true;

                int currJewelCnt = 1;
                q.add(new int[] {r,c});
                
                while (!q.isEmpty()) {
                    int[] curr = q.poll();
                    for (int i=0; i<4; i++) {
                        int nextR = curr[0] + dr[i];
                        int nextC = curr[1] + dc[i];
                        if (nextR < 0 || nextC < 0 || nextR >= 5 || nextC >= 5) continue;
                        if (visited[nextR][nextC]) continue;
                        if (tmpMap[curr[0]][curr[1]] != tmpMap[nextR][nextC]) continue;

                        visited[nextR][nextC] = true;
                        currJewelCnt++;
                        q.add(new int[] {nextR, nextC});
                    }
                }
                if (currJewelCnt < 3) continue;
                jewelCnt += currJewelCnt;
            }
        }

        return jewelCnt;
    }

    static int countJewel() {
        int jewelCnt = 0;
        boolean[][] visited = new boolean[5][5];

        PriorityQueue<int[]> jewelQ = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });
        Queue<int[]> tmpList = new LinkedList<>();
        Queue<int[]> q = new LinkedList<>();
        for (int r=0; r<5; r++) {
            for (int c=0; c<5; c++) {
                if (visited[r][c]) continue;
                visited[r][c] = true;

                int currJewelCnt = 1;
                tmpList.add(new int[] {r,c});
                q.add(new int[] {r,c});
                
                while (!q.isEmpty()) {
                    int[] curr = q.poll();
                    for (int i=0; i<4; i++) {
                        int nextR = curr[0] + dr[i];
                        int nextC = curr[1] + dc[i];
                        if (nextR < 0 || nextC < 0 || nextR >= 5 || nextC >= 5) continue;
                        if (visited[nextR][nextC]) continue;
                        if (tmpMaxMap[curr[0]][curr[1]] != tmpMaxMap[nextR][nextC]) continue;

                        visited[nextR][nextC] = true;
                        currJewelCnt++;
                        tmpList.add(new int[] {nextR,nextC});
                        q.add(new int[] {nextR, nextC});
                    }
                }
                if (currJewelCnt >= 3) {
                    jewelCnt += currJewelCnt;
                    jewelQ.addAll(tmpList);
                }
                tmpList.clear();
            }
        }

        while (!jewelQ.isEmpty()) {
            int[] curr = jewelQ.poll();
            tmpMaxMap[curr[0]][curr[1]] = wallJewel.poll();
        }

        return jewelCnt;
    }

    static void copyMap(int[][] targetMap, int[][] givingMap) {
        for (int r=0; r<5; r++) {
            targetMap[r] = Arrays.copyOf(givingMap[r], 5);
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. 탐사 진행
        //  회전 각도 작은 것부터 회전 - 함수
        //  열 -> 행 작은 곳부터
        // 2. 유물 획득
        //  bfs로 같은 숫자 3개 이상 모인 것 찾기(개수 세기) - 함수
        //  찾으면 자리 비워야 함(visited로 판단)
        //  유물 있으면 벽면 유물 배분(열 작 -> 행 큰)
        //  벽면 유물 배분 후에도 유물 있는 것 세야 함
        //  각 턴의 유물 총합 출력 반복
        // 모든 탐사 진행 -> 유물 획득에도 유물 0이라면 아무것도 반환 안하고 끝
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        wallJewel = new LinkedList<>();

        for (int r=0; r<5; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0; c<5; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<m; i++) {
            wallJewel.add(Integer.parseInt(st.nextToken()));
        }

        for (int i=0; i<k; i++) {
            int currMaxJewelCnt = 0;
            for (int rotate=0; rotate<3; rotate++) { // 회전 각도 작은 것부터
                for (int ex=0; ex<exploreMid.length; ex++) { // 열 작 -> 행 작
                    // 탐사 진행
                    rotate(exploreMid[ex][0], exploreMid[ex][1], rotate);
                    // 유물 확인
                    int jewelCnt = bfsJewel();

                    if (currMaxJewelCnt < jewelCnt) { // 최대
                        copyMap(tmpMaxMap, tmpMap);
                        currMaxJewelCnt = jewelCnt;
                    }
                }
            }

            if (currMaxJewelCnt == 0) return;
            else {
                int sumExtraJewel = 0;
                int extraJewel = 0;
                do {
                    extraJewel = countJewel();
                    sumExtraJewel += extraJewel;
                } while (extraJewel != 0);
                copyMap(map, tmpMaxMap);
                System.out.print(sumExtraJewel + " ");
            }
        }
    }
}