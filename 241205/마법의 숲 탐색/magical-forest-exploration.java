import java.util.*;
import java.io.*;

// (R,C) 좌상단(1,1)
// 북쪽으로만 정령 들어올 수 있음
// K명의 정령 -> 각자 골렘 타고 숲 탐색
// 골렘 : 십자 모양의 구조(5칸)
// 어떤 방향으로든 in 가능. out은 정해진 출구만
// i번째 정령 -> 중앙이 Ci열 위치에서 내려옴
// 초기 골렘 출구 : Di 방향
// <숲 탐색 우선순위>
// 1. 남쪽 한 칸 내려감(비어있을 때만 가능)
// 1-1. 남쪽 X -> 서쪽으로 회전해서 내려감(반시계 90도)(서쪽 남쪽 모두 비어있는 것 확인 필수)
// 1-2. 서쪽도 X -> 동쪽으로 회전해서 내려감(시계 90도)
// <가장 남쪽으로 이동 완료 시>
// 정령이 골렘 내에서 상하좌우 인접 칸으로 이동 가능
// 다른 골렘의 출구랑 인접해 있으면 그 골렘으로 이동 가능
// 정령이 갈 수 있는 가장 남쪽으로 이동(최종위치)
// 정령의 최종 위치의 행 번호의 합
// <주의> 
// 골렘 최종 이동 시에도 숲을 벗어났다면 해당 골렘 및 아래의 골렘 모두 없앰(초기화)
// 다음 골렘 부터 새롭게 탐색 시작

public class Main {
	static class Fairy {
		int r,c;
		Fairy(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static int R,C,K,rowSum;
	static int[][] forest;
	static int[][] isExit;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static boolean canMoveDown(int r, int c) {
		for (int i=1; i<4; i++) {
			int nr = r + dr[i] + 1;
			int nc = c + dc[i];
			if (nc<=0 || nr>R || nc>C) return false;
			if (nr>0 && forest[nr][nc] != 0) return false;
		}
		return true;
	}
	
	static boolean canMoveLeft(int r, int c) {
		// 서쪽 먼저 확인
		for (int i=0; i<4; i++) {
			if (i==1) continue;
			int nr = r + dr[i];
			int nc = c + dc[i] - 1;
			if (nc<=0 || nr>R || nc>C) return false;
			if (nr>0 && forest[nr][nc] != 0) return false;
		}
		
		// 남쪽 확인
		if (canMoveDown(r, c-1)) return true;
		else return false;
	}
	
	static boolean canMoveRight(int r, int c) {
		// 동쪽 먼저 확인
		for (int i=0; i<3; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i] + 1;
			if (nc<=0 || nr>R || nc>C) return false;
			if (nr>0 && forest[nr][nc] != 0) return false;
		}
		
		// 남쪽 확인
		if (canMoveDown(r, c+1)) return true;
		else return false;
	}
	
	static boolean isInForest(int r) {
		for (int nr=r-1; nr<=r+1; nr++) {
			if (nr <= 0) return false;
		}
		return true;
	}
	
	static int moveFairy(int r, int c) {
		int rowMax = 0;
		boolean[][] visited = new boolean[R+1][C+1];
		Queue<Fairy> q = new LinkedList<>();
		
		visited[r][c] = true;
		q.add(new Fairy(r,c));
		while (!q.isEmpty()) {
			Fairy f = q.poll();
			rowMax = Math.max(rowMax, f.r);
			
			for (int i=1; i<4; i++) {
				int nr = f.r + dr[i];
				int nc = f.c + dc[i];
				if (nc<=0 || nr>R || nc>C) continue;
				if (forest[nr][nc] == 0) continue;
				if (visited[nr][nc]) continue;
				
				if (forest[nr][nc] == forest[f.r][f.c]) {
					visited[nr][nc] = true;
					q.add(new Fairy(nr,nc));
				}
				else {
					if (isExit[f.r][f.c] == forest[f.r][f.c]) {
						visited[nr][nc] = true;
						q.add(new Fairy(nr,nc));
					}
				}
			}
		}
		return rowMax;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		K = stoi(st.nextToken());
		forest = new int[R+1][C+1];
		isExit = new int[R+1][C+1];
		
		for (int k=1; k<=K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = -1;
			int c = stoi(st.nextToken());
			int d = stoi(st.nextToken());
			
			while (true) {
				// 1) 남쪽 이동
				if (canMoveDown(r,c)) {
					r += 1;
					continue;
				}
				
				// 2) 서쪽 이동
				if (canMoveLeft(r,c)) {
					r -= 1;
					c -= 1;
					d = (d+3) % 4;
					continue;
				}
				
				// 3) 동쪽 이동
				if (canMoveRight(r,c)) {
					r += 1;
					c += 1;
					d = (d+1) % 4;
					continue;
				}
				
				// 이동 못하면 끝
				break;
			}
			
			// 4) 정령 이동
			// 숲 밖에 있으면 초기화
			if (!isInForest(r)) {
				forest = new int[R+1][C+1];
				isExit = new int[R+1][C+1];
				continue;
			}
			
			// 숲에 골렘 저장
			forest[r][c] = k;
			for (int i=0; i<4; i++) forest[r+dr[i]][c+dc[i]] = k;
			isExit[r+dr[d]][c+dc[d]] = k;
			
			// 정령 이동
			rowSum += moveFairy(r,c);
		}
		
		System.out.println(rowSum);
	}
}