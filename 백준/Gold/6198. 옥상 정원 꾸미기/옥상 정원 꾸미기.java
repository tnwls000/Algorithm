import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 빌딩의 개수
		Stack<Long> stack = new Stack<>();

		long cnt = 0;
		for (int i = 0; i < n; i++) {
			long height = Long.parseLong(br.readLine());

			if (stack.isEmpty()) {
				stack.push(height);
				continue;
			}
			
			if (stack.peek() > height) { // 앞의 빌딩 높이가 현재 빌딩 높이보다 높았다면
				cnt += stack.size();
			} else { // 앞의 빌딩 높이가 현재 빌딩 높이보다 낮거나 같았다면
				while (!stack.isEmpty() && stack.peek() <= height) {
					stack.pop(); // 앞의 빌딩 높이 낮은 것들 다 버림
				}
				cnt += stack.size(); // 남은건 현재 빌딩 높이보다 높이가 높은 빌딩들
			}
			stack.push(height);
		}
		System.out.println(cnt);
	}
}