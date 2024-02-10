import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken()); // 가로
		int r = Integer.parseInt(st.nextToken()); // 세로
		int n = Integer.parseInt(br.readLine()); // 자르는 점선 개수
		
		List<Integer> Clist = new ArrayList<>();
		List<Integer> Rlist = new ArrayList<>();
		Clist.add(c);
		Clist.add(0);
		Rlist.add(r);
		Rlist.add(0);
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("0")) { // 자르는 점선이 가로일 때
				Rlist.add(Integer.parseInt(st.nextToken()));
			} else { // 자르는 점선이 세로일 때
				Clist.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		Collections.sort(Clist);
		Collections.sort(Rlist);
		int colMax = 0; // 가로 최대
		int rowMax = 0; // 세로 최대
		for (int i=1; i<Clist.size(); i++) {
			colMax = Math.max(colMax, Clist.get(i) - Clist.get(i-1));
		}
		for (int i=1; i<Rlist.size(); i++) {
			rowMax = Math.max(rowMax, Rlist.get(i) - Rlist.get(i-1));
		}
		
		System.out.println(colMax * rowMax);
	}
}