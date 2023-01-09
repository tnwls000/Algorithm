import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList<>();
		for (int i=1; i<=n; i++) {
			q.add(i);
		}
		
		while (q.size() > 1) {
			//제일 위에꺼는 버리기
			q.remove();
			//그 다음 제일 위 카드를 제일 아래에 넣기
			q.add(q.poll());
		}
		
		bw.write(String.valueOf(q.poll()));
		
		bw.flush();
		bw.close();
    }
}