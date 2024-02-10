import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[] virus;
	static int[][] computer;
	static int cnt = 0;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine()); // 컴퓨터 수
		int m = Integer.parseInt(br.readLine()); // 컴퓨터 쌍의 수
		
		virus = new boolean[n+1];
		computer = new int[n+1][n+1];
		for (int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// 반대 상황[end][start]도 1로 만들어줘야 함(양방향)
			computer[start][end] = computer[end][start] = 1;
		}
		
		dfs(1); // 1번 컴퓨터부터 시작
		System.out.println(cnt);
	}
	
	static void dfs(int nodeIdx) {
		virus[nodeIdx] = true; // 방문 처리
		
		for (int i=1; i<n+1; i++) {
			// 2차원 배열 도는데
			// 아직 감염 안된 컴퓨터이고 && 해당 노드와 연결되어 있는 컴퓨터라면
			if (!virus[i] && computer[nodeIdx][i] != 0) {
				cnt++; // 개수 세고
				dfs(i); // 해당 컴퓨터 dfs 실행
			}
		}
	}
}