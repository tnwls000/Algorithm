import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Process> q = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i=0; i<priorities.length; i++) {
            q.add(new Process(priorities[i], i));
            pq.add(priorities[i]);
        }
        
        int cnt = 0;
        while (!q.isEmpty()) {
            Process pc = q.poll();
            if (pc.priority < pq.peek()) q.add(pc);
            else {
                cnt++;
                if (pc.id == location) break;
                pq.poll();
            }
        }
        
        return cnt;
    }
}

class Process {
    int priority;
    int id;
    Process (int priority, int id) {
        this.priority = priority;
        this.id = id;
    }
}