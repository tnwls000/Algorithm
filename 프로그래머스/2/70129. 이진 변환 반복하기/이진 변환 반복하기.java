import java.util.*;

class Solution {
    static String num = "";
    static int zero = 0;
    static int cnt = 0;
    
    static public void getZeroCnt(String s) {
        zero += Arrays.stream(s.split("")).filter(i -> i.equals("0")).count();
    }
    
    static public void toBinary(String s) {
        long len = Arrays.stream(s.split("")).filter(i -> !i.equals("0")).count();
        num = Long.toBinaryString(len);
    }
    
    public int[] solution(String s) {
        num = s;
        
        while (!num.equals("1")) {
            getZeroCnt(num);
            toBinary(num);
            cnt++;
        }
        
        return new int[] {cnt, zero};
    }
}