import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, result;
	static char[][] map;
	static int[][] visited;
	static boolean[] alpha;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		// 상하좌우 이동. 같은 알파벳 두 번 지나가기 안됨
		// (0,0)에서 시작. 지날 수 있는 칸 최대값 구하기
		// (0,0)도 셈 -> 1부터 시작
		// 한 칸씩 이동하는데, 같은 알파벳 만나면 그 방향 이동은 넘김
		// 중복 알파벳 판단은 길 다시 시작할 때 다시 초기화해줘야 함
		// -> 길 갈라질때 초기화하는데 자기가 이전에 걸어왔던 길 알파벳들은 기억하고 있어야 함
		// 스택 타입으로 사용자 정의 클래스 -> 안에 알파벳 담은 변수 만들어놓기
		// 방문 배열도 길 갈라질 때마다 달라짐 -> 사용자 정의 클래스 안에 변수 생성 필요
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 행
		m = Integer.parseInt(st.nextToken()); // 열
		
		map = new char[n][m];
		for (int r=0; r<n; r++) {
			String str = br.readLine();
			for (int c=0; c<m; c++) {
				map[r][c] = str.charAt(c); // 알파벳 입력 받기
			}
		}
		
		// 초기화
		result = 1;
		alpha = new boolean[26];
		visited = new int[n][m];
		for (int[] v : visited) {
			Arrays.fill(v, -1);
		}
		
		// 시작점 처리
		alpha[map[0][0]-65] = true;
		visited[0][0] = 1;
		dfs(0,0,alpha,visited);
		
		System.out.println(result);
	}
	
	static void dfs(int r, int c, boolean[] alpha, int[][] visited) {
		
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 범위 넘어가는 경우
			if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
			// 방문한 경우
			if (visited[nr][nc] != -1) continue;
			// 중복 알파벳인 경우
			if(alpha[map[nr][nc]-65]) continue;
			
			alpha[map[nr][nc]-65] = true;
			visited[nr][nc] = visited[r][c] + 1;
			
			result = Math.max(result, visited[nr][nc]);
			
			dfs(nr,nc, alpha, visited); // 재귀 함수 호출
			
			alpha[map[nr][nc]-65] = false; // 다시 false로 만들고 넘겨야 함
			visited[nr][nc] = -1; // 다시 방문 안한 걸로 하고 넘겨야 함
		}
	}
}