import java.util.*;

class Solution {
    static int cnt = 0;
    static List<String>[] banList;
    static Set<Set<String>> finalCheck = new HashSet<>();
    static Set<String> check = new HashSet<>();
    
    static boolean compareUser(String ban, String user) {
        if (ban.length() != user.length()) return false;
        
        for (int i=0; i<ban.length(); i++) {
            if (ban.charAt(i) == '*') continue;
            if (ban.charAt(i) != user.charAt(i)) return false;
        }
        
        return true;
    }
    
    static void countBanUser(int i) {
        if (i == banList.length) {
            if (!finalCheck.contains(check)) cnt++;
            finalCheck.add(new HashSet<>(check));
            return;
        }
        
        for (int j=0; j<banList[i].size(); j++) {
            if (check.contains(banList[i].get(j))) continue;
            check.add(banList[i].get(j));
            countBanUser(i+1);
            check.remove(banList[i].get(j));
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        banList = new ArrayList[banned_id.length];
        for (int i=0; i<banList.length; i++) {
            banList[i] = new ArrayList<>();
        }
        
        // 각각의 banned_id 맞는 user_id들을 저장
        for (int i=0; i<banned_id.length; i++) {
            for (int j=0; j<user_id.length; j++) {
                if (compareUser(banned_id[i], user_id[j])) {
                    banList[i].add(user_id[j]);
                }
            }
        }
        
        countBanUser(0);
        return cnt;
    }
}