import java.util.*;

class Solution {
    public int solution(int[] a) {
        int[] minArr = new int[a.length];
        Arrays.fill(minArr, 1000000001);
        int cnt = 0;
        
        int min = a[0];
        for (int i=0; i<a.length; i++) {
            min = Math.min(min, a[i]);
            minArr[i] = min;
        }
        min = a[a.length-1];
        for (int i=a.length-1; i>=0; i--) {
            min = Math.min(min, a[i]);
            minArr[i] = Math.max(minArr[i], min);
        }
        
        for (int i=0; i<a.length; i++) {
            if (minArr[i] >= a[i]) cnt++;
        }
        
        return cnt;
    }
}