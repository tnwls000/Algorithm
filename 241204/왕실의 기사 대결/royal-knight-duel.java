import java.util.*;
import java.io.*;

// L * L 체스판
// (1,1) 로 시작
// (r,c) 좌상단 기준 h*w 크기 직사각형 크기의 기사들
// 체력 k
// 1. 기사 이동
//	명령 받은 기사 -> 상하좌우 이동 -> 해당 칸에 기사 존재 시 연쇄적으로 밀림 -> 마지막 기사 끝에 벽 있으면 모든 기사 이동 x
//	체스판에서 사라진 기사에게 명령 -> 아무 반응 x
// 2. 대결 대미지
//	밀려난 기사 -> 이동한 곳에서 w*h 내 함정 수 만큼 피해 입음
//	체력 이상의 대미지 받을 시, 사라짐
//	명령 받은 기사는 피해 x
//	모든 기사들이 이동(밀린) 후 대미지 입히기
// Q번의 대결 후 생존 기사들의 총 받은 대미지 합 출력

// queue 두개. 기사 이동 
//	-> 이동칸에 다른 기사?(여러명 가능. 중복은 안되게 유의) -> queue add
//	-> 이동칸에 벽? -> 이동 x
//	-> 이동칸에 아무것도 없음? -> 끝 -> 이동 및 대미지 계산(지도 상 위치도 변경 필요). 명령 받은 기사는 대미지x!!

public class Main {
	static class Knight {
		int r,c,h,w,k,d,idx;
		boolean isInChess;
		Knight(int r, int c, int h, int w, int k, int d, int idx) {
			this.r=r;
			this.c=c;
			this.h=h;
			this.w=w;
			this.k=k;
			this.d=d;
			this.idx=idx;
			this.isInChess = true;
		}
		
		@Override
		public String toString() {
			return this.r + " " + this.c + " " + this.k + " " + this.d + " " + this.idx;
		}
	}
	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};
	static int L,N,Q;
	static int[][] chess;
	static int[][] chessInKnight;
	static Knight[] knights;
	static Queue<Knight> moveKnights;
	static Queue<Knight> movableKnights;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void initChessInKnight() {
		chessInKnight = new int[L][L];
		for (int l=0; l<L; l++) {
			Arrays.fill(chessInKnight[l], -1);
		}
	}
	
	static void markKnightInChess(Knight k) {
		for (int r=k.r; r<=k.r+k.h-1; r++) {
			for (int c=k.c; c<=k.c+k.w-1; c++) {
				chessInKnight[r][c] = k.idx;
			}
		}
	}
	
	static boolean isAtWall(Knight k) {
        int er = k.r + k.h - 1;
        int ec = k.c + k.w - 1;
		if (k.r<0 || k.c<0 || er>=L || ec>=L) return true;
		for (int r=k.r; r<=er; r++) {
			for (int c=k.c; c<=ec; c++) {
				if (chess[r][c] == 2) return true;
			}
		}
		return false;
	}
	
	static void pushKnight(Knight k, int dir) {
		boolean[] visited = new boolean[N];
		visited[k.idx] = true;
		for (int r=k.r; r<=k.r+k.h-1; r++) {
			for (int c=k.c; c<=k.c+k.w-1; c++) {
				int target = chessInKnight[r][c];
				if (target == -1) continue;
				if (visited[target]) continue;
				visited[target] = true;
				
				Knight nk = knights[target];
				if (!nk.isInChess) continue;
				moveKnights.add(new Knight(nk.r + dr[dir], nk.c + dc[dir], nk.h, nk.w, nk.k, nk.d, nk.idx));
				movableKnights.add(new Knight(nk.r + dr[dir], nk.c + dc[dir], nk.h, nk.w, nk.k, nk.d, nk.idx));
			}
		}
	}
	
	static void moveKnight(Knight k) {
		knights[k.idx] = k;
		markKnightInChess(k);
	}
	
	static void getDamage(Knight k) {
		int damage = 0;
		for (int r=k.r; r<=k.r+k.h-1; r++) {
			for (int c=k.c; c<=k.c+k.w-1; c++) {
				if (chess[r][c] == 1) damage++;
			}
		}
		int health = knights[k.idx].k;
		if (health - damage <= 0) knights[k.idx].isInChess = false;
		else {
			knights[k.idx].k -= damage;
			knights[k.idx].d += damage;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = stoi(st.nextToken());
		N = stoi(st.nextToken());
		Q = stoi(st.nextToken());
		chess = new int[L][L];
		initChessInKnight();
		knights = new Knight[N];
		
		for (int r=0; r<L; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<L; c++) {
				chess[r][c] = stoi(st.nextToken());
			}
		}
		
		for (int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int r = stoi(st.nextToken()) - 1;
			int c = stoi(st.nextToken()) - 1;
			int h = stoi(st.nextToken());
			int w = stoi(st.nextToken());
			int k = stoi(st.nextToken());
			knights[n] = new Knight(r,c,h,w,k,0,n);
			markKnightInChess(knights[n]);
		}
		
		for (int q=0; q<Q; q++) {
			st = new StringTokenizer(br.readLine());
			int targetKnight = stoi(st.nextToken()) - 1;
			int dir = stoi(st.nextToken());
			
			// 기사 밀기
			Knight tk = knights[targetKnight];
			if (!tk.isInChess) continue;
			
			moveKnights = new LinkedList<>();
			movableKnights = new LinkedList<>();
			
			moveKnights.add(new Knight(tk.r + dr[dir], tk.c + dc[dir], tk.h, tk.w, tk.k, tk.d, tk.idx));
			movableKnights.add(new Knight(tk.r + dr[dir], tk.c + dc[dir], tk.h, tk.w, tk.k, tk.d, tk.idx));
			while(!movableKnights.isEmpty()) {
				Knight currK = movableKnights.poll();
				
				if (isAtWall(currK)) {
					moveKnights.clear();
					break;
				}
				
				pushKnight(currK, dir);
			}
			
			if (moveKnights.isEmpty()) continue;
			
			// 기사 이동 및 대미지 계산
			initChessInKnight();
			while (!moveKnights.isEmpty()) {
				// 기사 이동
				Knight currK = moveKnights.poll();
				moveKnight(currK);
				
				// 대미지 계산
				if (currK.idx == targetKnight) continue;
				getDamage(currK);
			}
		}
		
		int result = 0;
		for (int n=0; n<N; n++) {
			Knight k = knights[n];
			if (!k.isInChess) continue;
			result += k.d;
		}
		System.out.println(result);
	}
}