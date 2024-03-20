import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	static int n,m;
	static int[] result, arr;
	static Set<String> set;
	static StringBuilder sb = new StringBuilder();
	
	static void recur(int cnt) {
		if (cnt == m) {
			String str = Arrays.toString(result);
			if (set.contains(str)) return;
			
			set.add(str);
			for (int i=0; i<m; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=0; i<n; i++) {
			result[cnt] = arr[i];
			recur(cnt+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		arr = new int[n];
		result = new int[m];
		tmp = br.readLine().split(" ");
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(tmp[i]);
		}
		Arrays.sort(arr);
		
		set = new HashSet<String>();
		recur(0);
		System.out.println(sb);
	}
}