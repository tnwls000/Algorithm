import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        List<Integer> arr = new ArrayList<>();
        for (int i=0; i<operations.length; i++) {
            String value = operations[i];
            if (value.contains("I")) {
                arr.add(Integer.valueOf(value.substring(2)));
            }
            else if (value.contains("D 1")) {
                if (arr.isEmpty()) continue;
                else arr.remove(Collections.max(arr));
            }
            else if (value.contains("D -1")) {
                if (arr.isEmpty()) continue;
                else arr.remove(Collections.min(arr));
            }
        }
        if (arr.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        }
        else {
            answer[0] = Collections.max(arr);
            answer[1] = Collections.min(arr);
        }
        
        return answer;
    }
}