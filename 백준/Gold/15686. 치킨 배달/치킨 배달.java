import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n,m, result;
	static int[] pick;
	static List<Point> house;
	static List<Point> chicken;
	
	static void recur(int idx, int cnt) {
		// base case
		if (cnt == m) { // m개만큼 치킨집 골랐으면
			int sum = 0;
			// 각각 집에서 가까운 치킨집 거리 합 구하기
			for (Point h : house) {
				int minDis = Integer.MAX_VALUE;
				for (int p : pick) {
					int dis = Math.abs(h.x-chicken.get(p).x)+Math.abs(h.y-chicken.get(p).y);
					minDis = Math.min(minDis, dis);
				}
				sum += minDis;
			}
			// 거리 최솟값 구함
			result = Math.min(result, sum);
			return;
		}
		if (idx >= chicken.size()) return;
		
		// recur
		// 치킨집 고르기
		for (int i=idx; i<chicken.size(); i++) {
			pick[cnt] = i;
			recur(i+1, cnt+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		for (int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<n; c++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					house.add(new Point(r,c));
				}
				if (num == 2) {
					chicken.add(new Point(r,c));
				}
			}
		}
		
		pick = new int[m];
		result = Integer.MAX_VALUE;
		recur(0,0);
		System.out.println(result);
	}
}