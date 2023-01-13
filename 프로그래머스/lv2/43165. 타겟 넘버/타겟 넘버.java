class Solution {
    static int count;
    public int solution(int[] numbers, int target) {
        
        back(numbers, 0, 0, target);
        return count;
    }
    
    private static void back(int[] numbers, int x, int sum, int target) {
        if (x==numbers.length) {
            if (sum == target) {
                count++;
            }
            return;
        }
        back(numbers, x+1, sum-numbers[x], target);
        back(numbers, x+1, sum+numbers[x], target);
    }
}