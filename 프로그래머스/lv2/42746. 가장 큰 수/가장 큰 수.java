import java.util.Arrays;
import java.lang.StringBuilder;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        String[] arr = new String[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(arr, (o2, o1) -> (o1 + o2).compareTo(o2 + o1));
        if (arr[0].equals("0")) {
            return "0";
        }
        for (int i=0; i<arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
        
    }
}