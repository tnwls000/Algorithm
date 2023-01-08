import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine()); //주어지는 명령의 수
		String[] str = new String[n];
		for (int i=0; i<n; i++) {
			str[i] = br.readLine();
		}
		
		Stack<Integer> st = new Stack<>();
		
		for (int i=0; i<n; i++) {
			String value = str[i];
			if (value.contains("push")) {
				int idx = value.length();
				st.push(Integer.valueOf(value.substring(5,idx)));
			}
			else if (value.contains("pop")) {
				if (st.empty()) {
					bw.write("-1");
					bw.newLine();
				}
				else {
					bw.write(String.valueOf(st.pop()));
					bw.newLine();
				}
			}
			else if (value.contains("size")) {
				bw.write(String.valueOf(st.size()));
				bw.newLine();
			}
			else if (value.contains("empty")) {
				if (st.empty()) {
					bw.write("1");
					bw.newLine();
				}
				else {
					bw.write("0");
					bw.newLine();
				}	
			}
			else if (value.contains("top")) {
				if (st.empty()) {
					bw.write("-1");
					bw.newLine();
				}
				else {
					bw.write(String.valueOf(st.peek()));
					bw.newLine();
				}
			}
		}
		bw.flush();
		bw.close();
    }
}