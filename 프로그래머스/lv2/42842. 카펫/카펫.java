class Solution {
    public long[] solution(long brown, long yellow) {
        long[] answer = new long[2];
        long area = brown + yellow;
        for (int i=1; i<=area; i++) {
            if (area%i == 0) {
                long one = i;
                long two = area/i;
                if ((one - 2) * (two - 2) == yellow) {
                    answer[0] = Math.max(one, two);
                    answer[1] = Math.min(one, two);
                }
            }
        }
        
        return answer;
    }
}