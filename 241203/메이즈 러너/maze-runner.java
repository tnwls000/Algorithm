import java.util.*;
import java.io.*;

// n*n 미로 격자
// 좌상단 (1,1)
// 1. 빈칸 : 참가자 이동 가능 칸
// 2. 벽
//  참가자 이동 못하는 칸
//  1~9 내구도
//  회전 시, 내구도 - 1
//  내구도 0 -> 빈 칸으로 변경됨
// 3. 출구 : 참가자 도달 시 즉시 탈출

// 모든 참가자 1초마다 한 칸씩 동시에 이동
// 상하좌우
// 움직인 칸이 현재 칸보다 출구까지의 최단 거리가 가까워야 함
// 이동 가능 칸 2개 이상 -> 상하가 우선
// 이동 불가 시 -> 이동 X
// 한 칸에 2명 이상의 참가자 위치 가능

// 이동 끝나면 미로 회전
// 출구 및 한 명 이상의 가장 작은 정사각형 잡기
// 정사각형 우선순위 : r 작 -> c 작
// 시계방향 90도. 회전 시 내구도 - 1
// 참가자 중 이 정사각형 안에 있는 사람은 좌표 변경 필요

// k초 동안 반복. k초 전 모든 참가자 탈출 시, 게임 끝
// 모든 참가자들의 이동 거리 합 및 출구 좌표
public class Main {
    static class Person {
        int r; // 행
        int c; // 열
        int m; // 이동 거리 합

        Person(int r, int c, int m) {
            this.r = r;
            this.c = c;
            this.m = m;
        }
    }
    static class Square {
        int sr,sc,er,ec,size;
        Square(int sr, int sc, int er, int ec, int size) {
            this.sr=sr;
            this.sc=sc;
            this.er=er;
            this.ec=ec;
            this.size=size;
        }
    }
    static final int[] dr = {-1,1,0,0};
    static final int[] dc = {0,0,-1,1};
    static int N,M,K,moveSum;
    static int[][] maze;
    static int[][] mazeInPeople; // 현재 미로 칸 안 사람 인원 수 파악
    static Queue<Person> participants;
    static int[] outP; // 출구 좌표

    // 참가자 이동 함수
    static Person moveParticipant(Person p) {
        int moveP = -1;
        int dis = Math.abs(p.r - outP[0]) + Math.abs(p.c - outP[1]);

        for (int i=0; i<4; i++) {
            int nr = p.r + dr[i];
            int nc = p.c + dc[i];
            
            if (nr<0 || nc<0 || nr>=N || nc>=N) continue;
            if (maze[nr][nc] > 0) continue;
            
            int nDis = Math.abs(nr - outP[0]) + Math.abs(nc - outP[1]);
            if (nDis < dis) {
                dis = nDis;
                moveP = i;
            }
        }

        mazeInPeople[p.r][p.c]--;
        if (moveP == -1) return new Person(p.r, p.c, p.m);
        else return new Person(p.r + dr[moveP], p.c + dc[moveP], p.m + 1);
    }

    // 작은 정사각형 찾는 함수
    static Square findSquare() {
        for (int size=2; size<=N; size++) {
            for (int r=0; r<=N-size; r++) {
                for (int c=0; c<=N-size; c++) {
                    int sr = r;
                    int sc = c;
                    int er = r+size-1;
                    int ec = c+size-1;
                    
                    if (outP[0] < sr || outP[0] > er || outP[1] < sc || outP[1] > ec) continue;
                    if (isInPeople(sr,sc,er,ec)) return new Square(sr,sc,er,ec,size);
                }
            }
        }
        return new Square(0,0,0,0,0);
    }

    static boolean isInPeople(int sr, int sc, int er, int ec) {
        for (int r=sr; r<=er; r++) {
            for (int c=sc; c<=ec; c++) {
                if (mazeInPeople[r][c] > 0) return true;
            }
        }
        return false;
    }

    // 미로 회전 함수
    static void rotateMaze(Square sq) {
        int size = sq.size;
        int[][] arr = new int[size][size];
        int[][] rotateArr = new int[size][size];
        for (int r=0; r<size; r++) {
            for (int c=0; c<size; c++) {
                arr[r][c] = maze[r+sq.sr][c+sq.sc];
            }
        }

        for (int r=0; r<size; r++) {
            for (int c=0; c<size; c++) {
                rotateArr[r][c] = arr[size-1-c][r];
                if (rotateArr[r][c] > 0) rotateArr[r][c]--;
            }
        }

        for (int r=0; r<size; r++) {
            for (int c=0; c<size; c++) {
                maze[r+sq.sr][c+sq.sc] = rotateArr[r][c];
            }
        }

        rotateOutP(sq);
    }

    // 출구 회전 반영
    static void rotateOutP(Square sq) {
        int or = outP[0] - sq.sr;
        int oc = outP[1] - sq.sc;
        outP[0] = oc + sq.sr;
        outP[1] = sq.size - 1 - or + sq.sc;
    }

    // 미로 회전 참가자 좌표 반영 함수
    static void rotateParticipant(Square sq) {
        int participantCnt = participants.size();
        for (int p=0; p<participantCnt; p++) {
            Person person = participants.poll();
            if (person.r < sq.sr || person.r > sq.er || person.c < sq.sc || person.c > sq.ec) {
                participants.add(person);
                continue;
            }
            mazeInPeople[person.r][person.c]--;
            int or = person.r - sq.sr;
            int oc = person.c - sq.sc;
            person.r = oc + sq.sr;
            person.c = sq.size - 1 - or + sq.sc;
            mazeInPeople[person.r][person.c]++;

            participants.add(person);
        }
    }

    static boolean canEscape(Person p) {
        if (p.r == outP[0] && p.c == outP[1]) return true;
        else return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        moveSum = 0;
        
        maze = new int[N][N];
        mazeInPeople = new int[N][N];
        for (int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0; c<N; c++) {
                maze[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        participants = new LinkedList<>();
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            participants.add(new Person(r,c,0));
            mazeInPeople[r][c]++;
        }

        st = new StringTokenizer(br.readLine());
        outP = new int[] {Integer.parseInt(st.nextToken()) - 1,Integer.parseInt(st.nextToken()) - 1};

        // K초 동안 반복
        for (int k=0; k<K; k++) {
            // 참가자 이동(출구 도달 시 즉시 탈출)
            int participantCnt = participants.size();
            for (int p=0; p<participantCnt; p++) {
                Person person = participants.poll();
                Person movedPerson = moveParticipant(person);
                if (!canEscape(movedPerson)) {
                    mazeInPeople[movedPerson.r][movedPerson.c]++;
                    participants.add(movedPerson);
                }
                else moveSum += movedPerson.m;
            }
            if (participants.isEmpty()) break; // 모든 참가자 탈출

            // 미로 회전
            Square square = findSquare();
            rotateMaze(square);
            rotateParticipant(square);
        }

        // 참가자 이동 거리 합 및 출구 좌표 출력
        while (!participants.isEmpty()) {
            Person person = participants.poll();
            moveSum += person.m;
        }
        
        System.out.println(moveSum);
        outP[0]++;
        outP[1]++;
        System.out.println(outP[0] + " " + outP[1]);
    }
}