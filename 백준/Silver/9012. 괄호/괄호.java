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
		
		int k = Integer.parseInt(br.readLine()); //테스트 데이터 개수
		String[] str = new String[k];
		//테스트 데이터
		for (int i=0; i<k; i++) {
			str[i] = br.readLine();
		}
		
		Stack<String> st;
		for (int i=0; i<k; i++) {
			st = new Stack<>();
			String test = str[i];
			for (int j=0; j<test.length(); j++) {
				//여는 괄호라면 push
				if (test.charAt(j) == '(') {
					st.push("(");
				} else {
					//닫는 괄호라면 stack에 있는 여는 괄호 하나 pop
					if (st.empty()) {
						//NO로 나오게 하기 위해 stack에 뭐 추가해줌
						st.push("NO");
						break;
					} else st.pop();
				}
			}
			//test 다 돌렸는데 stack에 괄호 남아 있으면 NO
			if (st.empty()) bw.write("YES");
			else bw.write("NO");
			bw.newLine();
		}
		
		
		bw.flush();
		bw.close();
    }
}