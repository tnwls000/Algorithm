import java.util.Scanner;
import java.util.Arrays;

class Main {
	static int n, arr[][], dp[][];
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
		int[] answer = new int[t];
		for (int i=0; i<t; i++) {
			n = sc.nextInt();
			arr = new int[2][n];
			dp = new int[n][3];
			
			for (int j=0; j<dp.length; j++) {
				Arrays.fill(dp[j], -1);
			}
			
			for (int k=0; k<n; k++) {
				arr[0][k] = sc.nextInt();
			}
			for (int k=0; k<n; k++) {
				arr[1][k] = sc.nextInt();
			}
			
			answer[i] = sticker(0, 0);
		}
		
		for (int i=0; i<t; i++) {
			System.out.println(answer[i]);
		}
    }
	
	private static int sticker(int c, int status) {
		if (c == n) return 0;
		if (dp[c][status] != -1) return dp[c][status];
		
		int result = sticker(c+1, 0);
		int value = 0;
		if (status != 1) {
			value = sticker(c+1, 1) + arr[0][c];
			result = result >  value ? result : value;
		}
		if (status != 2) {
			value = sticker(c+1, 2) + arr[1][c];
			result = result > value ? result : value;
		}
		
		dp[c][status] = result;
		return result;
		
	}
}