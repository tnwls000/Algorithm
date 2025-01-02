import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);
            String order = st.nextToken();
            int value = Integer.parseInt(st.nextToken());
            if ("I".equals(order)) {
                min.add(value);
                max.add(value);
            }
            else {
                if (min.isEmpty() && max.isEmpty()) continue;
                
                if (value == -1) max.remove(min.poll());
                else min.remove(max.poll());
            }
        }
        
        if (min.isEmpty()) return new int[] {0,0};
        else return new int[] {max.poll(), min.poll()};
    }
}