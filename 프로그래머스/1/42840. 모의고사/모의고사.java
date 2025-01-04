import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] first = {1,2,3,4,5};
        int[] second = {2,1,2,3,2,4,2,5};
        int[] third = {3,3,1,1,2,2,4,4,5,5};
        
        int firstCnt = 0;
        int secondCnt = 0;
        int thirdCnt = 0;
        
        for (int i=0; i<answers.length; i++) {
            if (first[i%first.length] == answers[i]) firstCnt++;
            if (second[i%second.length] == answers[i]) secondCnt++;
            if (third[i%third.length] == answers[i]) thirdCnt++;
        }
        
        int max = Math.max(firstCnt, Math.max(secondCnt, thirdCnt));
        
        List<Integer> list = new ArrayList<>();
        if (max == firstCnt) list.add(1);
        if (max == secondCnt) list.add(2);
        if (max == thirdCnt) list.add(3);
        
        int[] result = list.stream().mapToInt(i->i).toArray();
        return result;
    }
}