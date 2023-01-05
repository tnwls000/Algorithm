import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
		int mod = 10007;
		int[] dp = new int[1001];
		dp[1] = 1;
		dp[2] = 3;
		dp[3] = 5;

		for (int i=4; i<=N; i++) {
			dp[i] = (dp[i-1] + 2*dp[i-2])%mod;
		}
		
		System.out.println(dp[N]);
    }
}