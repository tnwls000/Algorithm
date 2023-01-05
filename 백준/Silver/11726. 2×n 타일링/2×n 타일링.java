import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
		int mod = 10007;
		long[] dp = new long[1001];
		dp[1] = 1;
		dp[2] = 2; 

		for (int i=3; i<=N; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%mod;
		}
		
		System.out.println(dp[N]);
    }
}