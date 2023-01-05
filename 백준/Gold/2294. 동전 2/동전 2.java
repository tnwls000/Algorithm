import java.util.Scanner;
import java.util.Arrays;

class Main {
	static int N, arr[], dp[][], IMPOSSIBLE = 1000000000;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
		int K = sc.nextInt();
		arr = new int[N];
		dp = new int[N+1][K+1];
		for (int i=0; i<N+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for (int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		int answer = f(0,K);
		if (answer == IMPOSSIBLE) {
			System.out.println(-1);
		} else System.out.println(answer);
		
    }
	
	private static int f(int n, int k) {
		
		if (n == N) return (k==0 ? 0 : IMPOSSIBLE);
		
		if (dp[n][k] != -1) return dp[n][k];
		
		int result = f(n+1, k);
		if (k >= arr[n]) {
			if (result > f(n, k - arr[n]) + 1) {
				result = f(n, k - arr[n]) + 1;
			}
		}
		
		dp[n][k] = result;
		return result;
		
	}
}