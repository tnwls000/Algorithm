import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Queue<Integer> q = new LinkedList<>();
        
        for (int i=0; i<arr.length; i++) {
            if (i > 0 && arr[i] == arr[i-1]) continue;
            q.add(arr[i]);    
        }
        
        int[] result = new int[q.size()];
        int idx = 0;
        while (!q.isEmpty()) {
            result[idx++] = q.poll();
        }

        return result;
    }
}