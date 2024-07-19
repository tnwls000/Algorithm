import java.util.*;

public class Main {
    static int n;
    static int[] building;
    static int[][] result;
    static Stack<int[]> s;

    static void check(int i) {
        int curr = building[i];

        while (!s.isEmpty() && s.peek()[0] <= curr) {
            s.pop();
        }
        if (!s.isEmpty()) {
            result[i][0] += s.size();
            if (result[i][1] != 0) {
                if (Math.abs(i - result[i][1]) >= Math.abs(i - s.peek()[1])) result[i][1] = s.peek()[1];
            } else result[i][1] = s.peek()[1];
        }
        s.push(new int[]{curr,i});
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        building = new int[n+1];
        for (int i=1; i<=n; i++) {
            building[i] = sc.nextInt();
        }

        // n == 1
        if (n==1) {
            System.out.println(0);
            return;
        }

        // [0] : cnt, [1] : 가장 가까운 건물 번호
        result = new int[n+1][2];

        // 오 -> 왼
        s = new Stack<>();
        s.push(new int[]{building[n], n});
        for (int i=n-1; i>=1; i--) {
            check(i);
        }

        // 왼 -> 오
        s.clear();
        s.push(new int[]{building[1], 1});
        for (int i=2; i<=n; i++) {
            check(i);
        }

        for (int i=1; i<=n; i++) {
            System.out.print(result[i][0] + " " + ((result[i][0] != 0) ? result[i][1] : ""));
            System.out.println();
        }
    }
}