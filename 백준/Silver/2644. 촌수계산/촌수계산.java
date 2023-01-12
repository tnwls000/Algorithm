import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class Main {
	static int n, m, count, fam[][], cnt[];
	
	private static void bfs(int x, int y) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(x);
		while (!queue.isEmpty()) {
			int point = queue.poll();
			if (point == y) break;
	
			for (int j=1; j<=n; j++) {
				if (fam[point][j] == 1 && cnt[j] == 0) {
					cnt[j] = cnt[point] + 1;
					queue.offer(j);
				}
			}
				
			

		}
	}	
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//전체 사람의 수
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//촌수를 계산해야 하는 서로 다른 두 사람의 번호
		int p1 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());
		
		//부모 자식들 간의 관계의 개수
		m = Integer.parseInt(br.readLine());
		fam = new int[n+1][n+1];
		cnt = new int[n+1];
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			fam[a][b] = 1;
			fam[b][a] = 1;
		}
		
		bfs(p1, p2);
		if (cnt[p2] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(cnt[p2]);
		}
		
	}
}