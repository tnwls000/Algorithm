import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] result = new int[commands.length];
        for (int i=0; i<commands.length; i++) {
            int[] target = commands[i];
            int start = target[0];
            int end = target[1];
            int size = end - start + 1;
            int[] newArr = new int[size];
            for (int j=0; j<size; j++) {
                newArr[j] = array[start-1];
                start++;
            }
            Arrays.sort(newArr);
            result[i] = newArr[target[2]-1];
        }
        return result;
    }
}