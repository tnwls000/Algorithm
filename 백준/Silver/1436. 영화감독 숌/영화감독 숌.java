import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// n번째 666이 들어간 수 찾기
		int n = Integer.parseInt(br.readLine());

		int num = 666; // 666 포함 숫자 세는 것이므로 666부터 시작해도 무관
		int cnt = 0;
		while (true) {
			if (String.valueOf(num).contains("666")) { // 숫자에 666 포함이면
				cnt++; // 개수 더하기
			}
			if (cnt == n) { // 목표 숫자 다다르면 멈추기
				break;
			}
			num++; // 숫자 더하기
		}
		
		bw.write(String.valueOf(num));
		bw.flush();
		bw.close();
	}
}