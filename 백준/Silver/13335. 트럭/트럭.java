import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Car {
	int weight;
	int move;
	public Car(int weight, int move) {
		super();
		this.weight = weight;
		this.move = move;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 트럭 수
		int w = Integer.parseInt(st.nextToken()); // 다리 길이
		int l = Integer.parseInt(st.nextToken()); // 다리 최대하중
		
		int[] truck = new int[n];
		st = new StringTokenizer(br.readLine()); // 트럭 무게
		for (int i=0; i<n; i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}
		
		Queue<Car> q = new LinkedList<>();
		q.add(new Car(truck[0], 0)); // 첫 번째 트럭 넣어놓기
		int totalWeight = truck[0];
		int idx = 1;
		int time = 0;
		while (true) {
			time++;
			// 트럭 이동
			int length = q.size();
			for (int i=0; i<length; i++) {
				Car t = q.poll();
				t.move++;
				q.add(t);
			}
			
			// 트럭 이동 길이 > 다리 길이 -> 큐에서 빼기
			if (q.peek().move > w) {
				totalWeight -= q.poll().weight;
				if (q.isEmpty() && idx == n) break;
			}
			
			if (idx < n) { // 다음 트럭 있을 때
				// 현재 다리에 있는 트럭 무게 + 다음트럭 <= 하중
				// 큐에 다음 트럭도 추가
				if (totalWeight + truck[idx] <= l) {
					totalWeight += truck[idx];
					q.add(new Car(truck[idx++], 1));
				}
			}
		}
		System.out.println(time);
	}
}