import java.util.*;
import java.io.*;

public class Main {
    static int n, min, max;
    static int[] numArr, operatorArr, operatorCombiArr;
    static Set<String> operatorSet;

    static void operatorCombi(int cnt, boolean[] visited) {
        if (cnt == n-1) {
            // 중복 체크
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<n-1; i++) {
                sb.append(operatorCombiArr[i]);
            }
            String operatorStr = sb.toString();
            if (operatorSet.contains(operatorStr)) return;
            operatorSet.add(operatorStr);

            int calcNum = calc();
            min = Math.min(min, calcNum);
            max = Math.max(max, calcNum);
            return;
        }

        for (int i=0; i<n-1; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            operatorCombiArr[cnt] = operatorArr[i];
            operatorCombi(cnt+1, visited);
            visited[i] = false;
        }
    }

    static int calc() {
        int result = numArr[0];
        for (int i=1; i<n; i++) {
            int operator = operatorCombiArr[i-1];
            switch (operator) {
                case 0:
                    result += numArr[i];
                    break;
                case 1:
                    result -= numArr[i];
                    break;
                case 2:
                    result *= numArr[i];
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        numArr = new int[n];
        operatorArr = new int[n-1];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int operatorIdx = 0;
        for (int i=0; i<3; i++) {
            int operator = Integer.parseInt(st.nextToken());
            if (operator == 0) continue;
            for (int j=0; j<operator; j++) {
                operatorArr[operatorIdx++] = i;
            }
        }

        operatorSet = new HashSet<>();
        operatorCombiArr = new int[n-1];
        boolean[] visited = new boolean[n-1];
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        
        operatorCombi(0, visited);

        System.out.println(min + " " + max);
    }
}