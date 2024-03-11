import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, max;
	static int[][] board;
	static boolean[][] visited; // 두 번 이상 합체 방지 불리언 배열
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};

	static void recur(int cnt, int direction, int[][] board) {
		// base case
		if (cnt == 5) { // 최대 5번 이동
			return;
		}
		
		// recursive case
		visited = new boolean[n][n]; // 매번 이동 시마다 초기화
		int[][] newBoard = new int[n][n];
		for (int i=0; i<n; i++) {
			newBoard[i] = Arrays.copyOf(board[i], board[i].length);
		}
		if (direction > 0) {
			switch(direction) {
			case 1:
				for (int c=0; c<n; c++) {
					for (int r=0; r<n; r++) {
						move(direction, r, c, newBoard);
					}
				}
				break;
			case 2:
				for (int c=0; c<n; c++) {
					for (int r=n-1; r>=0; r--) {
						move(direction, r, c, newBoard);
					}
				}
				break;
			case 3:
				for (int r=0; r<n; r++) {
					for (int c=0; c<n; c++) {
						move(direction, r, c, newBoard);
					}
				}
				break;
			case 4:
				for (int r=0; r<n; r++) {
					for (int c=n-1; c>=0; c--) {
						move(direction, r, c, newBoard);
					}
				}
				break;	
			}
		}
		
		recur(cnt+1, 1, newBoard); // 상
		recur(cnt+1, 2, newBoard); // 하
		recur(cnt+1, 3, newBoard); // 좌
		recur(cnt+1, 4, newBoard); // 우
	}
	
	static void move(int direction, int r, int c, int[][] newBoard) {
		if (newBoard[r][c] == 0) return;
		
		int nr = r + dr[direction];
		int nc = c + dc[direction];
		
		while (nr>=0 && nr<n && nc>=0 && nc<n) {
			if (newBoard[nr][nc] == 0) {
				if (visited[r][c]) { // 겹친 거 확인한 것도 옮겨야 함
					visited[r][c] = false;
					visited[nr][nc] = true;
				}
				newBoard[nr][nc] = newBoard[r][c];
				newBoard[r][c] = 0;
				r = nr;
				c = nc;
				nr += dr[direction];
				nc += dc[direction];

			} 
			
			else if (newBoard[r][c] != newBoard[nr][nc]) break;
			
			else if (!visited[nr][nc]) {
				newBoard[nr][nc] *= 2;
				max = Math.max(max, newBoard[nr][nc]);
				
				visited[nr][nc] = true;
				newBoard[r][c] = 0;
				
				break;
			}
			
			else break;
		}
	} 

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		visited = new boolean[n][n];
		for (int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<n; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				max = Math.max(max, board[r][c]); // 초기상태에서 가장 큰 블록 먼저 저장해놓기
			}
		}
		
		recur(-1, 0, board);
		System.out.println(max);
	}
}