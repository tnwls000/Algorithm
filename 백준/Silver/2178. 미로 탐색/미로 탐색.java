import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class Main {
	static boolean visit[][];
	static int start, end, n, m, map[][];
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visit = new boolean[n][m];
		for (int x=0; x<n; x++) {
			String str = br.readLine();
			for (int y=0; y<m; y++) {
				map[x][y] = Integer.parseInt(str.substring(y,y+1));
			}
		}
		
		visit[0][0] = true;
		//하,우로 이동하는데 값이 0이면 이동불가
		//end에 도달하면 칸 수 return
		//end까지 도달하는데 최소 칸 수
		bfs(0, 0);
		System.out.println(map[n-1][m-1]);
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x,y});
		
		while (!queue.isEmpty()) {
			int[] point = queue.poll();
			int nowX = point[0];
			int nowY = point[1];
			for (int i=0; i<4; i++) {
				int cx = nowX + dx[i];
				int cy = nowY + dy[i];
				if (cx >=0 && cy >= 0 && cx < n && cy < m){
					if (map[cx][cy] == 1 && !visit[cx][cy]) {
						map[cx][cy] = map[nowX][nowY] + 1;
						visit[cx][cy] = true;
						queue.offer(new int[] {cx, cy});
					}
				}
			}
		}
	}
}