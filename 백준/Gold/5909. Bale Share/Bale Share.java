import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int sum = 0;
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		
		boolean[][] dp = new boolean[sum+1][sum+1];
		dp[0][0] = true;
		for (int n=0; n<N; n++) {
			int v = arr[n];
			for (int i=sum; i>=0; i--) {
				for (int j=sum-i; j>=0; j--) {
					if (i >= v)
						dp[i][j] |= dp[i-v][j];
					if (j >= v)
						dp[i][j] |= dp[i][j-v];
				}
			}
		}
		
		int answer = sum+1;
		for (int i=0; i<=sum; i++) {
			for (int j=0; j<=i; j++) {
				int last = sum - i - j;
				if (dp[i][j] && last>=i) {
					answer = Math.min(answer, last);
				}
			}
		}
		System.out.println(answer);
	}
}