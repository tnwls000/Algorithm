import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long result = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 보석 수
		int k = Integer.parseInt(st.nextToken()); // 가방 수
		TreeMap<Integer, PriorityQueue<Integer>> jewels = new TreeMap<>();
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken()); // 무게
			int v = Integer.parseInt(st.nextToken()); // 가격
			
			if (jewels.containsKey(v)) {
				PriorityQueue<Integer> mPQ = jewels.get(v);
				mPQ.add(m);
				jewels.put(v, mPQ);
			} else {
				PriorityQueue<Integer> mPQ = new PriorityQueue<>();
				mPQ.add(m);
				jewels.put(v, mPQ);
			}
		}
		
		TreeMap<Integer, Integer> bags = new TreeMap<>();
		for (int i=0; i<k; i++) {
			int bag = Integer.parseInt(br.readLine());
			if (bags.containsKey(bag)) bags.put(bag, bags.get(bag) + 1);
			else bags.put(bag, 1);
		}
		
		while (!bags.isEmpty() && !jewels.isEmpty()) { // 보석이나 가방이 0개가 될 때까지
			
			Entry<Integer, PriorityQueue<Integer>> entry = jewels.lastEntry();
			PriorityQueue<Integer> mPQ = entry.getValue();
			int targetV = entry.getKey();
			int targetM = mPQ.poll();
			
			if (mPQ.isEmpty()) jewels.remove(targetV);
			else jewels.put(targetV, mPQ);
			
			Integer key = bags.ceilingKey(targetM);
			if (key == null) continue;
			
			if (bags.get(key) == 1) bags.remove(key);
			else bags.put(key, bags.get(key) - 1);
			
			result += targetV;
		}
		
		System.out.println(result);
	}
}