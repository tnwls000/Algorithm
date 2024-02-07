import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Tower {
	int num;
	int height;

	public Tower(int num, int height) {
		super();
		this.num = num;
		this.height = height;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine()); // 탑의 수
		Stack<Tower> s = new Stack<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) { // 탑 높이
			int height = Integer.parseInt(st.nextToken());

			if (s.isEmpty()) { // 현재 스택 비어 있으면 0을 출력하고 현재 높이 넣음
				sb.append("0 ");
				s.add(new Tower(i, height));
			} else {
				while (true) {
					if (s.isEmpty()) { // 현재 스택 비어 있으면 0을 출력하고 현재 높이 넣음
						sb.append("0 ");
						s.add(new Tower(i, height));
						break;
					}
					
					if (s.peek().height > height) { // 비어 있지 않고 peek한 높이가 현재 높이보다 높다면
						sb.append(s.peek().num + " ");
						s.add(new Tower(i, height));
						break;
					} else {
						// 비어 있지 않고 peek한 높이가 현재 높이보다 낮다면 pop()하고 continue
						s.pop();
					}

				}
			}

		}
		System.out.println(sb);
	}
}