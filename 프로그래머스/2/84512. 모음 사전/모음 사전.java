class Solution {
    static boolean flag;
    static int cnt, result;
    static String[] arr = {"A","E","I","O","U"};
    static StringBuilder sb = new StringBuilder();
    
    static void recur(int idx, String word) {
        if (flag) 
            return;
        if (word.equals(String.valueOf(sb))) {
            result = cnt;
            flag = true;
            return;
        }
        if (idx == 5) return;
        
        for (int i=0; i<5; i++) {
            sb.append(arr[i]);
            cnt++;
            
            recur(idx+1, word);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    
    public int solution(String word) {
        recur(0, word);
        return result;
    }
}