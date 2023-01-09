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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); //명령의 수
		String[] str = new String[n];
		for (int i=0; i<n; i++) {
			str[i] = br.readLine();
		}
		
		Deque<String> dq = new LinkedList<>();
		
		for (int i=0; i<n; i++) {
			String value = str[i];
			if (value.contains("push_front")) dq.offerFirst(value.substring(11));
			else if (value.contains("push_back")) dq.offerLast(value.substring(10));
			else if (value.contains("pop_front")) {
				if (dq.size() == 0) sb.append("-1").append("\n");
				else sb.append(dq.pollFirst()).append("\n");
			}
			else if (value.contains("pop_back")) {
				if (dq.size() == 0) sb.append("-1").append("\n");
				else sb.append(dq.pollLast()).append("\n");
			}
			else if (value.contains("size")) {
				sb.append(String.valueOf(dq.size())).append("\n");
			}
			else if (value.contains("empty")) {
				if (dq.size() == 0) sb.append("1").append("\n");
				else sb.append("0").append("\n");
			}
			else if (value.contains("front")) {
				if (dq.size() == 0) sb.append("-1").append("\n");
				else sb.append(dq.peek()).append("\n");
			}
			else if (value.contains("back")) {
				if (dq.size() == 0) sb.append("-1").append("\n");
				else sb.append(dq.peekLast()).append("\n");
			}
			
		}
		System.out.println(sb);
		bw.flush();
		bw.close();
    }
}