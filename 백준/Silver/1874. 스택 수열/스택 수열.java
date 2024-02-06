import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		for (int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(br.readLine()); // 수열을 이루는 1이상 n이하의 정수 배열
		}
		
		Stack<Integer> s = new Stack<>();
		int now = 1;
		boolean result = true;
		for (int i=0; i<n ;i++) {
			int target = nums[i];
			
			if (!s.isEmpty() && s.peek() == target) { // 타겟 숫자 스택 맨 앞에 있을 경우 pop
				s.pop();
				sb.append("-\n");
				
			} else if (s.isEmpty() || s.peek() != target){ // 타겟 숫자 스택 맨 앞에 없을 경우
				
				if (now <= target) {
					for (int j=now; j<=target; j++) {
						s.push(now++);
						sb.append("+\n");
					} // 넣을 숫자 타겟 숫자 넘기 전이라면 스택에 그 전 숫자 까지 push하고 타겟 숫자 pop
					s.pop();
					sb.append("-\n");
					
				} else {
					result = false; // 이미 넣을 숫자가 그 숫자 넘었다면 no
				}
			}
		}
		
		if (result) {
			System.out.println(sb);
		} else {
			System.out.println("NO");
		}
	}
}