import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	static int l,c,moeumCnt,jaeumCnt;
	static char[] result, arr;
	static Set<Character> moeum = new HashSet<Character>();
	static StringBuilder sb = new StringBuilder();
	
	static void recur(int idx, int cnt) {
		if (cnt == l) {
			if (moeumCnt == 0 || jaeumCnt < 2) return;
			
			for (char alpha : result) {
				sb.append(alpha);
			}
			sb.append("\n");
			return;
		}
		if (idx >= c) return;
		
		for (int i=idx; i<c; i++) {
			if (moeum.contains(arr[i])) moeumCnt++;
			else jaeumCnt++;
			
			result[cnt] = arr[i];
			recur(i+1, cnt+1);
			
			if (moeum.contains(arr[i])) moeumCnt--;
			else jaeumCnt--;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");
		l = Integer.parseInt(tmp[0]);
		c = Integer.parseInt(tmp[1]);
		
		arr = new char[c];
		result = new char[l];
		
		tmp = br.readLine().split(" ");
		for (int i=0; i<c; i++) {
			arr[i] = tmp[i].charAt(0);
		}
		Arrays.sort(arr);
		
		moeum.add('a');
		moeum.add('e');
		moeum.add('i');
		moeum.add('o');
		moeum.add('u');
		
		recur(0, 0);
		System.out.println(sb);
	}
}