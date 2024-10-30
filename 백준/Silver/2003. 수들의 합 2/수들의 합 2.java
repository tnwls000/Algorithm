import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N+1];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            if (arr[0] == M) System.out.println(1);
            else System.out.println(0);
            return;
        }

        int left = 0;
        int right = 0;
        int now = 0;
        int cnt = 0;
        while (right <= N) {
            if (now >= M) now -= arr[left++];
            else now += arr[right++];
            if (now == M) cnt++;
        }

        System.out.println(cnt);
    }
}