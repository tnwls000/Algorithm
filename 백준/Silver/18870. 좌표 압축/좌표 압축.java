import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		Set<Integer> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
		}
		
		Integer[] setArr = set.toArray(new Integer[0]);
		Arrays.sort(setArr);
		
		for (int i=0; i<n; i++) {
			int target = arr[i];
			
			int start = 0;
			int end = setArr.length;
			while (start <= end) {
				int mid = (start + end) / 2;
				if (setArr[mid] == target) {
					sb.append(mid + " ");
					break;
				}
				else if (setArr[mid] > target) end = mid-1;
				else start = mid+1;
			}
		}
		
		System.out.println(sb);
	}
}