import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] goods = new int[N+1][2];
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			goods[i][0] = Integer.parseInt(st.nextToken());
			goods[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][K+1];
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=K; j++) {
				if (goods[i][0] > j) dp[i][j] = dp[i-1][j];
				else dp[i][j] = Math.max(dp[i-1][j], goods[i][1] + dp[i-1][j-goods[i][0]]);
			}
		}
		
		System.out.println(dp[N][K]);
	}
}