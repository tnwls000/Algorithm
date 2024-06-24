import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int gemCnt = (int) Arrays.stream(gems)
            .distinct()
            .count();
        
        if (gemCnt == 1) return new int[] {1,1};
        int left = 0;
        int right = 0;
        int min = 100001;
        
        Map<String, Integer> map = new HashMap<>();
        map.put(gems[0], 1);
        
        while (right < gems.length) {
            
            
            
            if (map.size() == gemCnt) {
                if (right - left < min) {
                    answer[0] = left+1;
                    answer[1] = right+1;
                    min = right - left;
                } 
                
                if (map.get(gems[left]) == 1) {
                    map.remove(gems[left]);
                } else map.put(gems[left], map.get(gems[left]) - 1);
                left++;
                
            } else {
                right++;
                if (right < gems.length) {
                    if (map.containsKey(gems[right])) {
                        map.put(gems[right], map.get(gems[right]) + 1);
                    } else {
                        map.put(gems[right], 1);
                    }
                }
            }
        }
        
        return answer;
    }
}