import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] arr, idxArr;
    static List<Integer> lis;

    static int binary(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis.get(mid) < target) left = mid + 1;
            else right = mid;
        }
        return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1]; // 입력받는 수열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis = new ArrayList<>(); // 증가하는 수열 저장
        lis.add(Integer.MIN_VALUE);
        idxArr = new int[n+1]; // 해당 숫자의 위치 저장

        for (int i = 1; i <= n; i++) {
            if (lis.get(lis.size()-1) < arr[i]) {
                lis.add(arr[i]);
                idxArr[i] = lis.size()-1;
            }
            else {
                int idx = binary(1, lis.size()-1, arr[i]);
                lis.set(idx, arr[i]);
                idxArr[i] = idx;
            }
        }

        Stack<Integer> stack = new Stack<>();
        int idx = lis.size() - 1;
        for (int i=n; i>0; i--) {
            if (idxArr[i] == idx) {
                idx--;
                stack.push(arr[i]);
            }
        }

        System.out.println(lis.size()-1);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}