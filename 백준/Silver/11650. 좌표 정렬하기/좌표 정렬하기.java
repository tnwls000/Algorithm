import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	
	Point(int x, int y) { // x, y 좌표 담는 Point 클래스
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine()); // 점의 개수
		Point[] points = new Point[n];
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(points, (Point p1, Point p2) -> {
			if (p1.x == p2.x) {
				return p1.y - p2.y;
			}
			return p1.x - p2.x;
		});
		
		for (Point p : points) {
			bw.write(String.format("%d %d\n", p.x, p.y));
		}
		bw.flush();
		bw.close();
	}
}