import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Numm {
	int number;
	int idx;
	public Numm(int number, int idx) {
		super();
		this.number = number;
		this.idx = idx;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine()); // 수열 A의 크기
		Stack<Numm> s = new Stack<>();
		int[] result = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		s.push(new Numm(Integer.parseInt(st.nextToken()), 0)); // 첫 번째 수는 먼저 넣어놓기
		
		for (int i=1; i<n; i++) {
			int num = Integer.parseInt(st.nextToken()); // 현재 타겟 넘버
			
			if (s.peek().number >= num) {
				s.push(new Numm(num, i));
			} else {
				while (!s.isEmpty() && s.peek().number < num) {
					result[s.peek().idx] = num; // 해당 인덱스에 결과값 숫자 넣기
					s.pop(); // 작은 숫자 결과값 출력했으니 빼기(이제 필요없음)
				}
				s.push(new Numm(num, i)); // while문 다 끝나면 타켓 넘버 push
			}
		}
		
		// 다 끝나고도 스택에 남은 애들은 걔보다 오른쪽에 큰 수가 없다는 뜻 -> -1
		while (!s.isEmpty()) {
			result[s.peek().idx] = -1; // 해당 인덱스에 -1 넣기
			s.pop();
		}
		
		for (int r : result) {
			sb.append(r + " ");
		}
		
		System.out.println(sb);
	}
}