import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int cnt = 0;
        int idx = 0;
        for (int i=1; i<=n; i++) {
            if (idx < stations.length && i >= stations[idx] - w) {
                i = stations[idx] + w;
                idx++;
            } else {
                cnt++;
                i += w*2;
            }
        }
        
        return cnt;
    }
}