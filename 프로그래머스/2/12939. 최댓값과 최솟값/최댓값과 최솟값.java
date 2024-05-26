import java.util.*;

class Solution {
    public String solution(String s) {
        int[] arr = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int a : arr) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        
        return min + " " + max;
    }
}