import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] answers = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			answers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(answers);
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		binary: for (int i=0; i<m; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			// 이분탐색
			int left = 0;
			int right = n-1;
			while (left <= right) {
				int mid = (left + right) / 2;
				if (answers[mid] == num) {
					sb.append("1 ");
					continue binary;
				}
				if (answers[mid] < num) {
					left = mid + 1;
				} else if (answers[mid] > num){
					right = mid - 1;
				}
			}
			sb.append("0 ");
		}
		
		System.out.println(sb);
	}
}