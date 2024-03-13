import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n,m;
	static int[] arr, newArr;
	
	static void mergeSort(int left, int right) {
		if (right == left+1) return;
		
		int mid = (left + right) / 2;
		mergeSort(left, mid);
		mergeSort(mid, right);
		merge(left, mid, right);
	}
	
	static void merge(int left, int mid, int right) {
		int L = left;
		int R = mid;
		
		for (int i=left; i<right; i++) {
			if (L == mid) newArr[i] = arr[R++];
			else if (R == right) newArr[i] = arr[L++];
			else if (arr[L] <= arr[R]) newArr[i] = arr[L++];
			else newArr[i] = arr[R++];
		}
		
		for (int i=left; i<right; i++) {
			arr[i] = newArr[i];
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+m];
		newArr = new int[n+m];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n+m; i++) {
			if (i==n) st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// merge sort 이용
		mergeSort(0, n+m);
		
		
		for (int num : arr) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}
}