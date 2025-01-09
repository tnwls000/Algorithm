import java.util.*;
class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        
        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (k > 0 && !stack.isEmpty() && stack.peek() < c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        
        StringBuilder answer = new StringBuilder();
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }
        
        if (k > 0) 
            return answer.reverse().toString().substring(0, answer.toString().length() - k);
        return answer.reverse().toString();
        
    }
}