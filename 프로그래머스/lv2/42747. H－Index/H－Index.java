import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        
        for (int i=0; i<citations.length; i++) {
            int c = citations[i];
            int index = citations.length - i;
            if (index <= c) {
                answer = index;
                break;
            }
        }
        return answer;
    }
}