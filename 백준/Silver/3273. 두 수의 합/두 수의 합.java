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
		
		int maxIdx = 0;
		for (int i=n-1; i>=0; i--) { // x 보다 큰 수 제외하기
			if (nums[i] <= x) {
				maxIdx = i+1;
				break;
			}
		}
		
		// ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수 구하기
		int cnt = 0;
		for (int i=0; i<maxIdx-1; i++) { // i : 탐색 시작하는 인덱스. 첫번째 숫자
			sum : for (int j=i+1; j<maxIdx; j++) { // j : 두번째 숫자
				int sum = nums[i] + nums[j];
				
				if (sum == x) { // 합이 x가 되면 개수 더하기
					cnt++;
				} else if (sum > x) { // 합이 x가 넘으면 그 뒤에는 볼 필요 없으므로 멈춤
					break sum;
				}
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
	}
}