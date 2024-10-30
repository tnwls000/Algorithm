import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 지난 일수
        int X = Integer.parseInt(st.nextToken()); // 특정 기간

        int[] days = new int[N]; // 일별 방문자 수 배열
        int[] prefixSum = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            days[i] = Integer.parseInt(st.nextToken());
            prefixSum[i+1] = prefixSum[i] + days[i];
        }

        int max = -1;
        int cnt = 0;
        int now = X;
        while (now <= N) {
            int sum = prefixSum[now] - prefixSum[now-X];
            if (sum > max) {
                cnt = 1;
                max = sum;
            } else if (sum == max) cnt++;

            now++;
        }

        if (max == 0) System.out.println("SAD");
        else System.out.println(max + "\n" + cnt);
    }
}