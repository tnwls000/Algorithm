import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Car> q = new LinkedList<>();
		q.add(new Car(truck_weights[0], 0)); // 첫 번째 트럭 넣어놓기
		int totalWeight = truck_weights[0];
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
			if (q.peek().move > bridge_length) {
				totalWeight -= q.poll().weight;
				if (q.isEmpty() && idx == truck_weights.length) break;
			}
			
			if (idx < truck_weights.length) { // 다음 트럭 있을 때
				// 현재 다리에 있는 트럭 무게 + 다음트럭 <= 하중
				// 큐에 다음 트럭도 추가
				if (totalWeight + truck_weights[idx] <= weight) {
					totalWeight += truck_weights[idx];
					q.add(new Car(truck_weights[idx++], 1));
				}
			}
		}
        return time;
    }
}

class Car {
	int weight;
	int move;
	public Car(int weight, int move) {
		super();
		this.weight = weight;
		this.move = move;
	}
}