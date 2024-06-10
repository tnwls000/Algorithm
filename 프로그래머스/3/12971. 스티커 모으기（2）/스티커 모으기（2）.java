class Solution {
    public int solution(int sticker[]) {
        if (sticker.length == 1) return sticker[0];
        else if (sticker.length == 2) return Math.max(sticker[0], sticker[1]);
        
        int[] dp = new int[sticker.length];
       
        // 0번째 고르지 않았을 때
        dp[0] = 0;
        dp[1] = sticker[1];
        for (int i=2; i<sticker.length; i++) {
            dp[i] = Math.max(dp[i-2]+sticker[i], dp[i-1]);
        }
        
        int max = dp[sticker.length-1];
        
        // 0번째 골랐을 때
        dp[0] = sticker[0];
        dp[1] = sticker[0];
        for (int i=2; i<sticker.length-1; i++) {
            dp[i] = Math.max(dp[i-2]+sticker[i], dp[i-1]);
        }
        
        return Math.max(max, dp[sticker.length-2]);
    }
}