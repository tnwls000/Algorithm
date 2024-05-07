import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 구역 개수
		int q = Integer.parseInt(st.nextToken()); // 쿼리 개수
		
		TreeSet<Integer> set = new TreeSet<>(); // 명소 트리셋
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=n; i++) {
			if (st.nextToken().equals("1")) set.add(i);
		}
		
		int now = 1;
		for (int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			switch (query) {
			case 1:
				int area = Integer.parseInt(st.nextToken());
				if (set.contains(area)) set.remove(area);
				else set.add(area);
				break;
			case 2:
				int num = Integer.parseInt(st.nextToken());
				now = ((now + num) % n == 0) ? n : (now + num) % n;
				break;
			case 3:
				if (set.isEmpty()) sb.append(-1); // 명소 없으면 -1
				else {
					int first = set.first();
					if (set.ceiling(now) == null) sb.append(first + n - now);
					else sb.append(set.ceiling(now) - now);
				}
				sb.append("\n");
				break;
			}
		}
		
		System.out.println(sb);
	}
}