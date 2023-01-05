import java.util.Scanner;
import java.util.Arrays;

class Main {
	static int N;
	static long dp[];
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
		
		dp = new long[N+1];
		
		System.out.println(f(N));
		
    }
	
	private static long f(int n) {
		if (n == 1) return 1;
		if (n == 0) return 0;
		
		if (dp[n] > 0) return dp[n];
		
		dp[n] = f(n-1) + f(n-2);
		return dp[n];
		
	}
}