import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int cnt = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        while (!pq.isEmpty()) {
            if (pq.size() == 1 & pq.peek() < K) return -1;
            
            if (pq.peek() >= K) return cnt;
            
            int first = pq.poll();
            int second = pq.poll();
            
            cnt++;
            pq.add(first + (second * 2));
        }
        
        return cnt;
    }
}