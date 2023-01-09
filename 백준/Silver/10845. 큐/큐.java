import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine()); //명령의 수
		String[] str = new String[n];
		//명령
		for (int i=0; i<n; i++) {
			str[i] = br.readLine();
		}
		
		Deque<String> dq = new LinkedList<>();
		for (int i=0; i<n; i++) {
			String s = str[i];
			if (s.contains("push")) dq.add(s.substring(5,s.length()));
			else if (s.contains("pop")) {
				if (dq.size() == 0) {
					bw.write("-1");
					bw.newLine();
				} else {
					bw.write(dq.pop());
					bw.newLine();
				}
			}
			else if (s.contains("size")) {
				bw.write(String.valueOf(dq.size()));
				bw.newLine();
			}
			else if (s.contains("empty")) {
				if (dq.size() == 0) {
					bw.write("1");
					bw.newLine();
				} else {
					bw.write("0");
					bw.newLine();
				}
			}
			else if (s.contains("front")) {
				if (dq.size() == 0) {
					bw.write("-1");
					bw.newLine();
				} else {
					bw.write(dq.peek());
					bw.newLine();
				}
			}
			else if (s.contains("back")) {
				if (dq.size() == 0) {
					bw.write("-1");
					bw.newLine();
				} else {
					bw.write(dq.peekLast());
					bw.newLine();
				}
			}
		}
		
		
		bw.flush();
		bw.close();
    }
}