import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Integer[] newA = Arrays.stream(A).boxed().toArray(Integer[]::new);
        Integer[] newB = Arrays.stream(B).boxed().toArray(Integer[]::new);
        Arrays.sort(newA, Collections.reverseOrder());
        Arrays.sort(newB, Collections.reverseOrder());
        
        int idx = 0;
        int cnt = 0;
        for (int i=0; i<newA.length; i++) {
            if (newA[i] < newB[idx]) {
                cnt++;
                idx++;
            }
        }
        return cnt;
    }
}