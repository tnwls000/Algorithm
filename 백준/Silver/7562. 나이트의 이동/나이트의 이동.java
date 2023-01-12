import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class Main {
	static boolean visit[][];
	static int t, I, nowX, nowY, moveX, moveY, map[][];
	static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		t = Integer.parseInt(br.readLine());
		for (int i=0; i<t; i++) {
			I = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			nowX = Integer.parseInt(st.nextToken());
			nowY = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			moveX = Integer.parseInt(st.nextToken());
			moveY = Integer.parseInt(st.nextToken());

			map = new int[I][I];
			visit = new boolean[I][I];
			
			visit[nowX][nowY] = true;
			bfs(nowX, nowY);
			
			System.out.println(map[moveX][moveY]);
			
		}
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x,y});
		
		while (!queue.isEmpty()) {
			int[] point = queue.poll();
			int X = point[0];
			int Y = point[1];
			for (int i=0; i<8; i++) {
				int cx = X + dx[i];
				int cy = Y + dy[i];
				if (cx >=0 && cy >= 0 && cx < I && cy < I){
					if (map[cx][cy] == 0 && !visit[cx][cy]) {
						map[cx][cy] = map[X][Y] + 1;
						visit[cx][cy] = true;
						queue.offer(new int[] {cx, cy});
					}
				}
			}
		}
	}
}