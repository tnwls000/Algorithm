import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		List<Integer>[] list = new ArrayList[n+1];
		for (int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		int[] cnt = new int[n+1];
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int in = Integer.parseInt(st.nextToken());
			int out = Integer.parseInt(st.nextToken());
			list[in].add(out);
			cnt[out]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		for (int i=1; i<=n; i++) {
			if (cnt[i] == 0) q.add(i);
		}
		
		while (!q.isEmpty()) {
			int in = q.poll();
			System.out.print(in + " ");
			
			for (int out : list[in]) {
				if (cnt[out] == 1) {
					cnt[out]--;
					q.add(out);
				}
				if (cnt[out] > 1) cnt[out]--;
			}
		}
	}
}