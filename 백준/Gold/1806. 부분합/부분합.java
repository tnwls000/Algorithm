import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (i > 0) arr[i] += arr[i-1]; // 누적합
		}
		
		int result = 100000;
		int left = 0; int right = 0;
		while (left < n && right < n && left <= right) {
			int sum = (left == 0) ? arr[right] : arr[right] - arr[left-1];
			
			if (sum >= s) {
				result = Math.min(result, right - left + 1);
				left++;
				
			} else right++;
		}
		
		System.out.println(result == 100000 ? 0 : result);
	}
}