import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 명령의 수
		Stack<Integer> s = new Stack<>();
		
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			switch(order) {
			case 1:
				int num = Integer.parseInt(st.nextToken());
				s.add(num);
				break;
			case 2:
				if (s.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(s.pop() + "\n");
				}
				break;
			case 3:
				sb.append(s.size() + "\n");
				break;
			case 4:
				if (s.isEmpty()) {
					sb.append("1\n");
				} else {
					sb.append("0\n");
				}
				break;
			case 5:
				if (s.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(s.peek() + "\n");
				}
				break;	
			}
		}
		
		System.out.println(sb);
	}
}