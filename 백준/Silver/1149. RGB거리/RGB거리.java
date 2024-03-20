import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] home = new int[n][3];
		int[][] dp = new int[n][3];
		for (int i=0; i<n; i++) {
			String[] tmp = br.readLine().split(" ");
			home[i][0] = Integer.parseInt(tmp[0]); // R
			home[i][1] = Integer.parseInt(tmp[1]); // G
			home[i][2] = Integer.parseInt(tmp[2]); // B
		}
		
		dp[0][0] = home[0][0];
		dp[0][1] = home[0][1];
		dp[0][2] = home[0][2];
		for (int i=1; i<n; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + home[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + home[i][1];
			dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + home[i][2];
		}
		
		System.out.println(Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2])));
	}
}