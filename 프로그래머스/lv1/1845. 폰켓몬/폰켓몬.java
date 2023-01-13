import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(int[] nums) {
        String[] str = new String[nums.length];
        for (int i=0; i<nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        
        Map<String,Integer> map = new HashMap<>();
        for (String s : str) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        
        int get = nums.length / 2 ;
        if (map.size() > get) {
            return get;
        } else {
            return map.size();
        }
        
    }
}