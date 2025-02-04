import java.util.*;
import java.io.*;

public class Main {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] room = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		// 로봇 초기 좌표 및 방향
		int robR = Integer.parseInt(st.nextToken());
		int robC = Integer.parseInt(st.nextToken());
		int robD = Integer.parseInt(st.nextToken());
		
		// 방 정보
		for (int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<M; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 청소하는 칸 개수
		int cnt = 0;
		
		while (true) {
			// 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소
			if (room[robR][robC] == 0 && !visited[robR][robC]) {
				cnt++;
				visited[robR][robC] = true;
			}
			
			// 주변 4칸 중 청소되지 않은 빈 칸 찾기(반시계 방향으로)
			boolean flag = false;
			int nd = robD;
			for (int i=0; i<4; i++) {
				nd = (nd + 3) % 4;
				int nr = robR + dr[nd];
				int nc = robC + dc[nd];
				
				// 빈 칸 존재
				if (room[nr][nc] == 0 && !visited[nr][nc]) {
					robR = nr;
					robC = nc;
					robD = nd;
					flag = true;
					break;
				}
			}
			
			if (flag) continue;
			
			// 빈 칸 존재하지 않음
			nd = (robD + 2) % 4;
			int nr = robR + dr[nd];
			int nc = robC + dc[nd];
			if (room[nr][nc] == 1) break;
			robR = nr;
			robC = nc;
		}
		
		System.out.println(cnt);
	}
}