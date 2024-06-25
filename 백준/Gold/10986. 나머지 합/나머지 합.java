import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];
        arr[0] = Long.parseLong(st.nextToken());
        long[] mods = new long[m];
        long cnt = 0;

        // 누적합
        for (int i=1; i<n; i++) {
            arr[i] = (arr[i-1] + Long.parseLong(st.nextToken()));
        }

        for (int i=0; i<n; i++) {
            int mod = (int) (arr[i] % m);
            if (mod == 0) cnt++;
            mods[mod]++;
        }

        for (int i=0; i<m; i++) {
            if (mods[i] > 1) cnt += mods[i] * (mods[i] - 1) / 2;
        }

        System.out.println(cnt);
    }
}