import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 계단의 개수
		arr = new int[n];
		
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine()); // 계단 점수 입력 받기
		}
		
		dp = new int[n];
		
		dp[0] = arr[0];
		// n이 1로 입력될 경우의 예외처리
		if (n > 1) {  
			dp[1] = arr[0] + arr[1];
            // n이 2로 입력될 경우의 예외처리
			if (n > 2) {
				dp[2] = Math.max(arr[0]+arr[2], arr[1]+arr[2]);
			}
		}
		
		System.out.println(dp(n-1));
	}
	
	static int dp(int n) {
		if (dp[n] == 0) {
			// 해당 계단의 최댓값은
			// (2개 전 계단 점수)와 (3개 전 계단+1개 전 계단 점수) 중 최댓값 + 해당 계단 점수
			dp[n] = Math.max(dp(n-2), dp(n-3) + arr[n-1]) + arr[n];
		}
		
		return dp[n];
	}
}