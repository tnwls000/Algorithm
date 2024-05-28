import java.util.*;

class Solution {
    public int solution(String s) {
        Stack<String> stack = new Stack();
        
        for (String alpha : s.split("")) {
            if (!stack.isEmpty() && stack.peek().equals(alpha)) {
                stack.pop();
            } else stack.push(alpha);
        }
        
        return stack.isEmpty() ? 1 : 0;
    }
}