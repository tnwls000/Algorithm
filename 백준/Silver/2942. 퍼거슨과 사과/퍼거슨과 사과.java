import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Integer> nums;

    static int getMaxDivisor(int a, int b) {
        while (a%b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return b;
    }

    static void getYaksu(int num) {
        for (int i=1; i<=num; i++) {
            if (num%i == 0) nums.add(i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int g = sc.nextInt();

        int maxDivisor = (r > g) ? getMaxDivisor(r, g) : getMaxDivisor(g, r);
        nums = new ArrayList<>();
        getYaksu(maxDivisor);

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num).append(" ").append(r/num).append(" ").append(g/num).append("\n");
        }
        System.out.println(sb);
    }
}