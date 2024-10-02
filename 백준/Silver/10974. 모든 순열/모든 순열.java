import java.util.Scanner;

public class Main {
	static int n;
	static int[] arr, result;
	static boolean[] memo;
	
	static void recur(int idx) {
		if (idx == n+1) {
			for (int i=1; i<=n; i++) {
				System.out.print(result[i]+" ");
			}
			System.out.println();
			return;
		}
		
		
		for (int i=1; i<=n; i++) {
			if (memo[i]) continue;
			result[idx] = arr[i];
			memo[i] = true;
			recur(idx+1);
			memo[i] = false;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n+1];
		for (int i=1; i<=n; i++) arr[i] = i;
		memo = new boolean[n+1];
		
		result = new int[n+1];
		recur(1);
	}
}