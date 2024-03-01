import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n, m;
	static int[] result, arr;
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
		
		perm(0);
		System.out.println(sb);
	}

	static void perm(int idx) {
		if (idx == m) {
			for (int r : result) {
				sb.append(r + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=0; i<n; i++) {
			if (idx > 0 && result[idx-1] > arr[i]) continue;
			result[idx] = arr[i];
			perm(idx+1);
		}
	}
}