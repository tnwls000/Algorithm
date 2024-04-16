import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); // 오름차순 정렬
		int cnt = 0;
		for (int i=0; i<n; i++) {
			int left = 0;
			int right = n-1;
			
			binary: while (left < right) {
				if (left == i) left++;
				if (right == i) right--;
				if (left == right) break;
				
				int num = arr[left] + arr[right];
				if (num == arr[i]) {
					cnt++;
					break binary;
				}
				if (num > arr[i]) right--;
				else left++;
			}
		}
		
		System.out.println(cnt);
	}
}