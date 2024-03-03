import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        int[] memo = new int[n+2];
        for (int i=0; i<reserve.length; i++) {
            memo[reserve[i]] = 1;
        }
        
        int cnt = n - lost.length;
        for (int i=0; i<lost.length; i++) {
            int num = lost[i];
            if (memo[num] == 1) {
                cnt++;
                memo[num] = -1;
            }
        }
        
        
        
        for (int i=0; i<lost.length; i++) {
            int num = lost[i];
            if (memo[num] == -1) continue;
            if (memo[num-1] == 1) {
                memo[num-1] = 0;
                cnt++;
            }
            else if (memo[num+1] == 1) {
                memo[num+1] = 0;
                cnt++;
            }
        }
        return cnt;       
    }
}