import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // n : 사람 수
		int k = Integer.parseInt(st.nextToken()); // k : 제거할 사람의 인덱스
	
		Deque<Integer> list = new ArrayDeque<>();
		for (int i=1; i<=n; i++) { // 1번째부터 n번째까지의 사람 추가
			list.add(i);
		}
		
		int idx = 0;
		String[] josephus = new String[n];
		while (!list.isEmpty()) { // 사람 다 제거될 때까지
			for (int i=0; i<k-1; i++) { // k-1번째까지는 앞에꺼 뒤로 보냄
				int tmp = list.poll(); 
				list.addLast(tmp);
			}
			josephus[idx++] = String.valueOf(list.poll()); // k번째 사람
		}
		
		String result = String.join(", ", josephus);
		bw.write("<" + result + ">");
		bw.flush();
		bw.close();
	}
}
