import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class Main {
	static boolean visit[];
	static int F, S, G, U, D, map[];
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		F = Integer.parseInt(st.nextToken()); //건물 층 수
		S = Integer.parseInt(st.nextToken()); //현재 층
		G = Integer.parseInt(st.nextToken()); //목표 층
		U = Integer.parseInt(st.nextToken()); //위
		D = Integer.parseInt(st.nextToken()); //아래
		

		map = new int[F+1];
		visit = new boolean[F+1];
		visit[S] = true;
		bfs(S);
		if (map[G] == 0) {
			if (S == G) {
				System.out.println(0);
			} else {
				System.out.println("use the stairs");
			}
		} else System.out.println(map[G]);
	}
	
	private static void bfs(int s) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(s);
		
		while (!queue.isEmpty()) {
			int point = queue.poll();
			
				int up = point + U;
				int down = point - D;
				
				if (up <= F){
					if (map[up] == 0 && !visit[up]) {
						map[up] = map[point] + 1;
						visit[up] = true;
						queue.offer(up);
					}
				}
				
				if (down >= 1) {
					if (map[down] == 0 && !visit[down]) {
						map[down] = map[point] + 1;
						visit[down] = true;
						queue.offer(down);
					}
				}
				
		}
	}
}