class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0L;
        long ticket = 0L;
        ticket = (long) price * ((long) count * ((long) count + 1))/2;
        
        if (ticket - money > 0) {
            answer = ticket - money;
        }
        return answer;
    }
}