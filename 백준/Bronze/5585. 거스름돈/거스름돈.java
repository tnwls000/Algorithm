import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cost = 1000 - Integer.parseInt(br.readLine());
		int[] coin = {500, 100, 50, 10, 5, 1};
		int cnt = 0;
		for (int i=0; i<coin.length; i++) {
			cnt += cost/coin[i];
			cost %= coin[i];
		}
		System.out.print(cnt);
	}
}
