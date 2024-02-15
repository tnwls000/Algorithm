import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		test: for (int t=0; t<T; t++) {
			char[] function = br.readLine().toCharArray(); // 수행할 함수
			int n = Integer.parseInt(br.readLine()); // 배열에 들어있는 수의 개수
			
			Deque<String> d = new ArrayDeque<>();
			String[] str = br.readLine().replace("[", "").replace("]", "").split(",");
			
			for (int i=0; i<n; i++) {
				d.offerLast(str[i]);
			}
			
			boolean isForward = true; // 순방향 여부(false:역방향)
			
			for (char f : function) {
				if (f == 'R') { // 순서 뒤집기
					if (isForward) isForward = false;
					else isForward = true;
				} else { // 첫 번째 수 버리기
					if (d.isEmpty()) { // 비어있으면 에러
						sb.append("error\n");
						continue test;
					}
					if (isForward) d.pollFirst();
					else d.pollLast();
				}
			}
			// 유의할 반례 : 명령어 R만 존재하고 배열 길이 0일 때 -> error가 아닌 [] 출력
			// 결과 출력
			sb.append("[");
			if (n!=0 && isForward) {
				sb.append(String.join(",", d));
			} else if (n!=0 && !isForward) { // 역방향 시, 거꾸로 출력
				Deque<String> d2 = new ArrayDeque<String>();
				while (!d.isEmpty()) {
					d2.offerLast(d.pollLast());
				}
				sb.append(String.join(",", d2));
			}
			sb.append("]\n");
		}
		System.out.println(sb);
	}
}