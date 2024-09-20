class Solution {
    static int count;
    public int solution(int[] numbers, int target) {
        
        recur(numbers, 0, 0, target);
        return count;
    }
    
    private static void recur(int[] numbers, int now, int sum, int target) {
        if (now == numbers.length) {
            if (sum == target) count++;
            return;
        }
        
        recur(numbers, now+1, sum-numbers[now], target);
        recur(numbers, now+1, sum+numbers[now], target);
    }
}