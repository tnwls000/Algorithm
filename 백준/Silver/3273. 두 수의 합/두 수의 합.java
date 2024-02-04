import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine()); // n : 수열의 크기 (1<= n <= 100,000)
		int[] nums = new int[n]; // nums : 수열
		
		int idx = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) { // 수열에 포함되는 수 입력(중복 X) (1<= num <= 1,000,000)
			nums[idx++] = Integer.parseInt(st.nextToken());
		}
		
		int x = Integer.parseInt(br.readLine()); // x : 자연수 (1<= x <= 2,000,000)
		Arrays.sort(nums); // 오름차순 정렬
		
		int start = 0;
		int end = nums.length - 1;
		int cnt = 0;
		while (start<end) {
			int sum = nums[start] + nums[end];
			if (sum == x) { // 두수의 합이 x가 맞으면 cnt++하고 다음으로 넘어가기
				cnt++;
				start++;
				end--;
			} else if (sum > x) { // 합이 x보다 크면
				end--;
			} else { // 합이 x보다 작으면
				start++;
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
	}
}