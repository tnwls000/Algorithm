import java.util.*;
import java.io.*;

// N*N 좌상단(1,1)
// M개의 턴. 루돌프 한 번 이동 -> 1번~P번 산타 순서대로 이동(기절 or 격자 밖 산타는 이동 X)
// 두 칸 거리 : (r1-r2)^2 + (c1-c2)^2

// 1. 루돌프
// 	가장 가까운 산타 향해 1칸 이동(게임 탈락한 산타는 제외)
//	거리 같으면 r -> c(큰->작)
//	가장 가까운 산타한테 8방 중 가장 가까워지는 한 방향으로 이동

// 2. 산타
//	1~P번 순서대로 이동
//	루돌프에게 가장 가까워지는 방향으로 1칸 이동
// 	다른 산타 or 격자 밖은 이동 불가
//	움직일 수 없으면 이동 X
//	움직일 수 있더라도 루돌프에게 가까워지는 방법 아니면 이동 X <주의!!>
// 	루돌프와 달리 4방으로 이동 가능(여러 개 -> 상우하좌)

// 3. 충돌
//	산타 = 루돌프 충돌
//	루돌프 이동 시 충돌 -> 산타 + C 점수 & 루돌프 이동 방향으로 C칸 이동됨
// 	산타 이동 시 충돌 -> 산타 + D 점수 & 이동 방향 반대로 D칸 밀림
// 	밀리는 것은 도중에 충돌 안 일어나고 정확히 위치 도달
//	밀린 칸에 다른 산타 -> 상호작용 발생

// 4. 상호작용
//	루돌프와 충돌 후 밀리고 난후 다른 산타 중복에만 발생
//	이동할 칸에 다른 산타 있을 경우 이 산타가 1칸 해당 방향을 밀림 -> 연쇄적으로 밀림
// 	격자 밖 이동 시 탈락

// 5. 기절
//	루돌프와의 충돌 시 -> 산타 기절
// 	k턴일 시, 다음 턴까지 기절. k+2(다다음) 턴부터 정상 상태
//	충돌이나 상호작용은 가능
// 	기절한 산타도 루돌프 충돌 대상으로 선택 가능

// 6. 게임 종료
//	M번의 턴에 걸쳐 루 -> 산 이동 후 게임 종료
//	P명의 산타 모두 탈락 -> 즉시 게임 종료
// 	매 턴 이후 탈락하지 않은 산타들 +1 점씩 추가 부여
//	게임 종료 시 각 산타가 얻은 최종 점수 출력

public class Main {
	// activeTurn이 현재 턴보다 크면 기절인 것. 작거나 같으면 이동 가능
	static class Santa {
		int num,r,c,score,activeTurn;
		boolean isExit;
		Santa(int num, int r, int c) {
			this.num=num;
			this.r=r;
			this.c=c;
			score = 0;
			activeTurn = 0;
			isExit = false;
		}
	}
	static final int MAX = Integer.MAX_VALUE;
	static int[] dr = {-1,0,1,0,-1,-1,1,1};
	static int[] dc = {0,1,0,-1,-1,1,1,-1};
	static int N,M,P,C,D,exitSantaCnt;
	static int[][] board;
	static Santa[] santas;
	static int dearR;
	static int dearC;
	static int nowTurn;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	// 거리 계산
	static int getDistance(int r1, int c1, int r2, int c2) {
		return (int) Math.pow(r1 - r2, 2) + (int) Math.pow(c1 - c2, 2);
	}
	
	// 루돌프 이동
	static void moveDear() {
		int santaIdx = findNearestSanta();
		int direction = decideDearDirection(santaIdx);
		
		board[dearR][dearC] = 0;
		dearR += dr[direction];
		dearC += dc[direction];
		
		// 산타와의 충돌
		if (board[dearR][dearC] > 0) {
			crush(true, santaIdx, direction);
		}
		board[dearR][dearC] = -1;
	}
	
	static int findNearestSanta() {
		int nearestDis = MAX;
		int santaIdx = -1;
		for (int p=0; p<P; p++) {
			Santa curr = santas[p];
			if (curr.isExit) continue;
			int dis = getDistance(dearR, dearC, curr.r, curr.c);
			if (dis < nearestDis) {
				nearestDis = dis;
				santaIdx = p;
			} else if (dis == nearestDis) {
				if (curr.r > santas[santaIdx].r || (curr.r == santas[santaIdx].r && curr.c > santas[santaIdx].c)) {
					nearestDis = dis;
					santaIdx = p;
				}
			}
		}
		return santaIdx;
	}
	
	static int decideDearDirection(int santaIdx) {
		Santa santa = santas[santaIdx];
		int dis = getDistance(dearR, dearC, santa.r, santa.c);
		int direction = -1;
		
		for (int i=0; i<8; i++) {
			int nr = dearR + dr[i];
			int nc = dearC + dc[i];
			if (!isInBoard(nr,nc)) continue;
			
			int nextDis = getDistance(nr, nc, santa.r, santa.c);
			if (nextDis < dis) {
				dis = nextDis;
				direction = i;
			}
		}
		return direction;
	}
	
	static boolean isInBoard(int r, int c) {
		if (r<=0 || c<=0 || r>N || c>N) return false;
		return true;
	}
	
	// 산타 이동
	static void moveSanta(int santaIdx) {
		int direction = decideSantaDirection(santaIdx);
		if (direction == -1) return;
		
		Santa santa = santas[santaIdx];
		
		board[santa.r][santa.c] = 0;
		santas[santaIdx].r += dr[direction];
		santas[santaIdx].c += dc[direction];
		
		if (board[santas[santaIdx].r][santas[santaIdx].c] == -1) {
			crush(false, santaIdx, direction);
		} else board[santas[santaIdx].r][santas[santaIdx].c] = santaIdx + 1;
	}
	
	static int decideSantaDirection(int santaIdx) {
		Santa santa = santas[santaIdx];
		int distance = getDistance(dearR, dearC, santa.r, santa.c);
		int direction = -1;
		
		for (int i=0; i<4; i++) {
			int nr = santa.r + dr[i];
			int nc = santa.c + dc[i];
			
			if (!isInBoard(nr,nc)) continue;
			if (board[nr][nc] > 0) continue;
			
			int dis = getDistance(dearR, dearC, nr, nc);
			if (distance <= dis) continue;
			
			direction = i;
			distance = dis;
		}
		return direction;
	}
	
	// 충돌
	static void crush(boolean isStartedDear, int santaIdx, int dir) {
		Santa santa = santas[santaIdx];
		int score = -1;
		if (isStartedDear) score = C;
		else {
			score = D;
			dir = (dir + 2) % 4;
		}
		
		santas[santaIdx].score += score;
		if (board[santa.r][santa.c] == santaIdx+1) board[santa.r][santa.c] = 0;
		
		int nr = santa.r + (dr[dir] * score);
		int nc = santa.c + (dc[dir] * score);
		
		if (!isInBoard(nr, nc)) {
			santas[santaIdx].isExit = true;
			exitSantaCnt++;
			return;
		} 
		
		santas[santaIdx].r = nr;
		santas[santaIdx].c = nc;
		
		// 밀림
		if (board[nr][nc] > 0) pushSanta(board[nr][nc]-1, dir, nr, nc);
		board[nr][nc] = santaIdx + 1;
		
		// 기절
		santas[santaIdx].activeTurn = nowTurn + 2;
	}
	
	// 상호작용
	static void pushSanta(int idx, int dir, int r, int c) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		if (!isInBoard(nr,nc)) {
			santas[idx].isExit = true;
			exitSantaCnt++;
			return;
		}
		
		santas[idx].r = nr;
		santas[idx].c = nc;
		
		if (board[nr][nc] > 0) pushSanta(board[nr][nc]-1, dir, nr, nc);
		board[nr][nc] = idx + 1;
	}
	
	// 1번의 턴 이후 활성화 산타 +1
	static void giveScoreAfterTurn() {
		for (int p=0; p<P; p++) {
			Santa curr = santas[p];
			if (curr.isExit) continue;
			santas[p].score += 1;
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		P = stoi(st.nextToken());
		C = stoi(st.nextToken());
		D = stoi(st.nextToken());
		exitSantaCnt = 0;
		board = new int[N+1][N+1]; // -1: 루돌프 1~P: 산타
		santas = new Santa[P];
		
		st = new StringTokenizer(br.readLine());
		dearR = stoi(st.nextToken());
		dearC = stoi(st.nextToken());
		board[dearR][dearC] = -1;
		
		for (int p=0; p<P; p++) {
			st = new StringTokenizer(br.readLine());
			int num = stoi(st.nextToken());
			int r = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			santas[p] = new Santa(num - 1,r,c);
			board[r][c] = num;
		}
		
		for (int m=1; m<=M; m++) {
			if (exitSantaCnt == P) break;
			nowTurn = m;
			
			moveDear();
			
			for (int p=0; p<P; p++) {
				Santa curr = santas[p];
				if (curr.isExit || curr.activeTurn > m) continue;
				moveSanta(p);
			}
			
			giveScoreAfterTurn();
		}
		
		Arrays.sort(santas, (o1, o2) -> {
			return Integer.compare(o1.num, o2.num);
		});
		StringBuilder sb = new StringBuilder();
		for (Santa santa : santas) sb.append(santa.score).append(" ");
		System.out.println(sb.toString());
	}

}
