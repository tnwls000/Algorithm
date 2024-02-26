import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Box {
	int h; // 높이
	int r; // 세로
	int c; // 가로
	public Box(int h, int r, int c) {
		super();
		this.h = h;
		this.r = r;
		this.c = c;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()); // 가로
		int n = Integer.parseInt(st.nextToken()); // 세로
		int h = Integer.parseInt(st.nextToken()); // 높이
		
		int[][][] tomato = new int[h][n][m]; // 3차원 배열의 상자 배열
		int[][][] visited = new int[h][n][m];
		for (int[][] visits : visited) {
			for (int[] v : visits) {
				Arrays.fill(v, -1);
			}
		}
		
		int cnt = n*m*h;
		int cnt2 = 0;
		Queue<Box> q = new LinkedList<>();
		for (int k=0; k<h; k++) { // 높이
			for (int r=0; r<n; r++) { // 세로
				st = new StringTokenizer(br.readLine());
				for (int c=0; c<m; c++) { // 가로
					tomato[k][r][c] = Integer.parseInt(st.nextToken());
					if (tomato[k][r][c] == 1) {
						q.add(new Box(k,r,c));
						visited[k][r][c] = 0;
						cnt2++;
					}
					if (tomato[k][r][c] == -1) cnt--; // cnt에는 -1을 제외한 칸의 개수 들어감
				}
			}
		}
		
		int date = 0;
		int[] dh = {-1, 1};
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		while (!q.isEmpty()) {
			Box b = q.poll();
			
			for (int i=0; i<6; i++) { // 그냥 i==5,6일 때 3차원 위아래 판단하기로 함
				int nh = b.h;
				int nr; int nc;
				if (i==4) {
					nh += dh[0]; // 위의 상자 도달
					nr = b.r;
					nc = b.c;
				}
				else if (i==5) {
					nh += dh[1]; // 아래 상자 도달
					nr = b.r;
					nc = b.c;
				}
				else {
					nr = b.r + dr[i]; // 위아래 아닐 때는 그냥 해당 차원의 상하좌우
					nc = b.c + dc[i];
				}
				
				if (nr<0 || nr>=n || nc<0 || nc>=m || nh<0 || nh>=h) continue;
				if (visited[nh][nr][nc] != -1 || tomato[nh][nr][nc] == -1) continue;
				
				visited[nh][nr][nc] = visited[b.h][b.r][b.c] + 1;
				date = Math.max(date, visited[nh][nr][nc]);
				tomato[nh][nr][nc] = 1;
				
				q.add(new Box(nh,nr,nc));
				cnt2++;
			}
			
		}
		
		if (cnt == cnt2) System.out.println(date); // 토마토 다 익으면 최소 날짜 출력
		else System.out.println(-1); // 도달하지 못한 토마토 있으면(다 안익음) -1 출력
	}
}