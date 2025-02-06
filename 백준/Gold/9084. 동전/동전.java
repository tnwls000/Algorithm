import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int n=1; n<=N; n++) {
				arr[n] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			
			int[] dp = new int[M+1];
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=M; j++) {
					if (j - arr[i] > 0) dp[j] = dp[j] + dp[j-arr[i]];
					else if (j - arr[i] == 0) dp[j]++;
				}
			}
			sb.append(dp[M]).append("\n");
		}
		System.out.println(sb.toString());	
	}
}