import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
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
        int now = arr[left];
        int cnt = 0;
        while (left <= right && right < N) {
            if (now == M) {
                cnt++;
                right++;
                if (right < N) now += arr[right];
            } else if (now < M) {
                right++;
                if (right < N) now += arr[right];
            }
            else {
                if (left == right) {
                    right++;
                    if (right < N) now += arr[right];
                }
                else {
                    now -= arr[left];
                    left++;
                }
            }
        }

        System.out.println(cnt);
    }
}