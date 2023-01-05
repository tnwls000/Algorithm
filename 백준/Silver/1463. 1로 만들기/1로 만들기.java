import java.util.Scanner;
import java.util.Arrays;

class Main {
	static int dp[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        System.out.println(solve(n)); 
    }
    
    private static int solve(int n) {
        if (n == 1) return 0;
        
        if (dp[n] != -1) return dp[n];
        
        int result = solve(n-1) + 1;
        if (n % 3 == 0) {
            result = result > (solve(n/3) + 1) ? (solve(n/3) + 1) : result;
        }
        if (n % 2 == 0) {
            result = result > (solve(n/2) + 1) ? (solve(n/2) + 1) : result;
        }
        
        dp[n] = result;
        return result;
    }
}