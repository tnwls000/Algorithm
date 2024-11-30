import java.util.*;
import java.io.*;

public class Main {
    static int n, morning, night, min;
    static int[][] works;
    static int[] combiFirstArr;
    static int[] combiSecondArr;
    static Set<String> combiSet;

    static void combiFirst(int idx, int cnt) {
        if (cnt == n/2) {
            boolean[] checkDuplication = new boolean[n];
            for (int i=0; i<combiFirstArr.length; i++) {
                checkDuplication[combiFirstArr[i]] = true;
            }

            // 저녁 조합도 구하기
            int[] nightArr = new int[n/2];
            int nightIdx = 0;
            for (int i=0; i<n; i++) {
                if (!checkDuplication[i]) nightArr[nightIdx++] = i;
            }

            morning = 0;
            night = 0;

            combiSecondArr = new int[2];
            combiSecond(0, 0, combiFirstArr, true);
            combiSecondArr = new int[2];
            combiSecond(0, 0, nightArr, false);

            min = Math.min(min, Math.abs(morning - night));
            return;
        }

        for (int i=idx; i<n; i++) {
            combiFirstArr[cnt] = i;
            combiFirst(i+1, cnt+1);
        }
    }

    static void combiSecond(int idx, int cnt, int[] combiArr, boolean isMorning) {
        if (cnt == 2) {
            if (combiSecondArr[0] == combiSecondArr[1]) return;

            int cal = works[combiSecondArr[0]][combiSecondArr[1]];
            if (isMorning) morning += cal;
            else night += cal;
            return;
        }

        for (int i=0; i<combiArr.length; i++) {
            combiSecondArr[cnt] = combiArr[i];
            combiSecond(i+1, cnt+1, combiArr, isMorning);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        works = new int[n][n];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                works[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combiFirstArr = new int[n/2];
        combiSet = new HashSet<>();
        min = Integer.MAX_VALUE;

        combiFirst(0, 0);

        System.out.println(min);
    }
}