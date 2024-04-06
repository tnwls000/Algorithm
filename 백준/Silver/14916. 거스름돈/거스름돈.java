import java.util.Scanner;

public class Main {
	static int[] change = {2, 5};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N+1];
		
		for (int n=1; n<=N; n++) {
			int min = 1000000;
			for (int c=0; c<2; c++) {
				if (n < change[c]) continue;
				if (min > dp[n-change[c]] + 1) {
					min = dp[n-change[c]] + 1;
				}
			}
			dp[n] = min;
		}
		
		dp[N] = (dp[N] == 1000000) ? -1 : dp[N];
		System.out.println(dp[N]);
	}
}