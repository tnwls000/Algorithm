class Solution {
    public long solution(long price, long money, long count) {
        long answer = 0L;
        long ticket = 0L;
        ticket = price * ( count * ( count + 1))/2;
        
        if (ticket - money > 0) {
            answer = ticket - money;
        }
        return answer;
    }
}