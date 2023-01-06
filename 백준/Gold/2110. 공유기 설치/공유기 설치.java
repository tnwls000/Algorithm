import java.util.Scanner;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //집의 개수
		int c = sc.nextInt(); //공유기의 개수
		long[] arr = new long[n];
		for (int i=0; i<n; i++) {
			arr[i] = sc.nextLong();
		}
		Arrays.sort(arr);
		
		long left = 1;
		long right = 1000000001L;
		
		while (right - left > 1) {
			long mid = (left + right) / 2;
			
			long start = arr[0];
			long count = 1;
			for (int i=0; i<n; i++) {
				if (start + mid <= arr[i]) {
					start = arr[i];
					count++;
				}
			}
			
			if (count >= c) left = mid;
			else right = mid;
			
		}
		System.out.println(left);
    }
}