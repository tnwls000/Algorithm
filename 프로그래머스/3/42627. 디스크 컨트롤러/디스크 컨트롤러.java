import java.util.*;

class Solution {
    static class Task implements Comparable<Task> {
        int idx, accessT, taskT;
        Task(int idx, int accessT, int taskT) {
            this.idx = idx;
            this.accessT = accessT;
            this.taskT = taskT;
        }
        
        @Override
        public int compareTo(Task t) {
            if (this.taskT == t.taskT) {
                if (this.accessT == t.accessT) {
                    return this.idx - t.idx;
                }
                return this.accessT - t.accessT;
            }
            return this.taskT - t.taskT;
        }
    }
    
    public int solution(int[][] jobs) {
        // 번호, 요청 시각, 소요 시간 담은 자료 구조 필요
        int[][] disks = new int[jobs.length][3];
        for (int i=0; i<jobs.length; i++) {
            disks[i][0] = i;
            disks[i][1] = jobs[i][0];
            disks[i][2] = jobs[i][1];
        }
        Arrays.sort(disks, (int[] o1, int[] o2) -> {
            return o1[1] - o2[1];
        });
        
        int turnaroundT = 0;
        int time = 0;
        int idx = 0;
        PriorityQueue<Task> pq = new PriorityQueue<>();
        while (!pq.isEmpty() || idx < disks.length) {
            while (idx < disks.length && disks[idx][1] <= time) {
                pq.add(new Task(disks[idx][0],disks[idx][1],disks[idx][2]));
                idx++;
            }
            
            if (pq.isEmpty()) {
                time++;
                continue;
            }
            
            if (time < pq.peek().accessT) {
                time = pq.peek().accessT;
                continue;
            }
            
            Task curr = pq.poll();
            time += curr.taskT;
            turnaroundT += time - curr.accessT;
        }
        
        return turnaroundT / disks.length;
    }
}