import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
// 복습 필수 ....
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] values = new int[n]; // 값이 담길 배열
		Deque<Integer> dq = new ArrayDeque<>(); // 덱에는 인덱스가 담김
		
		for (int i=0; i<n; i++) {
			values[i] = Integer.parseInt(st.nextToken());
			
			// 덱이 비어 있지 않고 해당 덱(인덱스) 마지막 값의 value가 현재 value보다 크다면 빼기
			while (!dq.isEmpty() && values[dq.peekLast()] > values[i]) dq.pollLast();
			dq.offer(i); // 현재 인덱스값 넣기
			
			if (i >= l-1 && dq.peekFirst() < i-l+1) dq.pollFirst(); // l의 범위 넘어가면 맨 앞에 있는 거 빼기
			sb.append(values[dq.peekFirst()] + " "); // 맨 앞에는 최소값이 남아 있음
		}
		
		System.out.println(sb);
	}
}