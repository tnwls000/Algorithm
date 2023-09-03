import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] first = {1,2,3,4,5};
        int[] second = {2,1,2,3,2,4,2,5};
        int[] third = {3,3,1,1,2,2,4,4,5,5};
        
        int f = 0;
        int s = 0;
        int t = 0;
        
        for (int i=0; i<answers.length; i++) {
            if (answers[i] == first[i%first.length]) f++;
            if (answers[i] == second[i%second.length]) s++;
            if (answers[i] == third[i%third.length]) t++;
        }
        
        int maxScore = Math.max(Math.max(f,s), t);
        List<Integer> li = new ArrayList<>();
        if (maxScore == f) li.add(1);
        if (maxScore == s) li.add(2);
        if (maxScore == t) li.add(3);
        
        return li.stream().mapToInt(Integer::intValue).toArray();
    }
}