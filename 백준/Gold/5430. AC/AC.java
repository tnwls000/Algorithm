import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.LinkedList;
import java.util.Deque;

class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스의 수
		int t = Integer.parseInt(br.readLine());
		
		for (int i=0; i<t; i++) {
			//수행할 함수
			String p = br.readLine();
			//배열에 들어있는 수의 개수
			int n = Integer.parseInt(br.readLine());
			//배열에 들어있는 정수
			String arr = br.readLine();
			String[] newArr;
			Deque<Integer> dq = new LinkedList<>();
			if (n != 0) {
				arr = arr.substring(1, arr.length() - 1);
				newArr = arr.split(",");
				//배열을 덱으로 변환
				for (String ar : newArr) {
					dq.offer(Integer.parseInt(ar));
				}
			}
			
			boolean way = true;
			boolean error = false;
			//함수에 따라 수행
			Loop1: for (int j=0; j<p.length(); j++) {
				switch (p.charAt(j)) {
					case 'R':
						if (way) way = false;
						else way = true;
						break;
					case 'D':
						if (dq.size() == 0) {
							sb.append("error").append("\n");
							error = true;
							break Loop1;
						} 
						else if (way) dq.pollFirst();
						else dq.pollLast();
						break;
				}
			}
			if (dq.size() != 0) {
				int size = dq.size();
				sb.append("[");
				if (way) {
					for (int j=0; j<size; j++) {
						if (dq.size() == 1) sb.append(dq.pollFirst());
						else sb.append(dq.pollFirst()).append(",");
					}
				} else {
					for (int j=0; j<size; j++) {
						if (dq.size() == 1) sb.append(dq.pollLast());
						else sb.append(dq.pollLast()).append(",");
					}
				}
				sb.append("]").append("\n");
			}
			else if (error == false) {
				sb.append("[]").append("\n");
			}
		}
		System.out.println(sb);
    }
}