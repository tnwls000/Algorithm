import java.util.*;

class Solution {
    static Map<String, Integer> map;
    static int[] answer;
    public void calSale(int cost, int idx, String[] referral) {
        if (referral[idx].equals("-") || cost == 0) return;
        
        int nextIdx = map.get(referral[idx]); 
        int newCost = cost/10;
        answer[nextIdx] += (newCost == 0) ? cost : cost - newCost;
        calSale(cost/10, nextIdx, referral);
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        map = new HashMap<>();
        for (int i=0; i<enroll.length; i++) {
            map.put(enroll[i], i);
        }
        
        answer = new int[enroll.length];
        for (int i=0; i<seller.length; i++) {
            int idx = map.get(seller[i]);
            answer[idx] += amount[i]*90;
            calSale(amount[i]*10, idx, referral);
        }
        
        return answer;
    }
}