import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n, m;
	static int[] result, arr;
	static boolean[] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		str = br.readLine().split(" ");
		arr = new int[n];
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		
		result = new int[m];
		visited = new boolean[n];
		
		perm(0);
		System.out.println(sb);

	}

	private static void perm(int idx) {
		if (idx == m) {
			for (int n : result) {
				sb.append(n + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=0; i<n; i++) {
			if (visited[i]) continue;
			
			result[idx] = arr[i];
			visited[i] = true;
			perm(idx+1);
			visited[i] = false;
		}
		
	}

}