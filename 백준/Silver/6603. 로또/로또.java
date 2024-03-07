import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int k;
	static int[] arr;
	static int[] result = new int[6];
	static StringBuilder sb;
	
	static void recur(int idx, int cnt) {
		if (cnt == 6) {
			for (int num : result) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		if (idx == k) return;
		
		result[cnt] = arr[idx];
		recur(idx+1, cnt+1);
		
		recur(idx+1, cnt);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k == 0) break;
			
			arr = new int[k];
			for (int i=0; i<k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			
			recur(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}