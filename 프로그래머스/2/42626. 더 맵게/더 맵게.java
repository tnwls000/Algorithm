import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        int cnt = 0;
        while (pq.size() >= 2) {
            if (pq.peek() >= K) break;
            
            int num1 = pq.poll();
            int num2 = pq.poll();
            pq.add(num1 + (num2 * 2));
            cnt++;
        }
        
        if (pq.peek() < K) return -1;
        else return cnt;
    }
}