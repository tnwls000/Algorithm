import java.util.Scanner;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int[] water = new int[N];
        int count = 0;
        for (int i=0; i<N; i++) {
            water[i] = sc.nextInt();
        }
        Arrays.sort(water);
        double range = water[0] - 0.5 + L;
        count++;
        for (int i=1; i<N; i++) {
            if (range < water[i] + 0.5) {
                count++;
                range = water[i] - 0.5 + L;
            }
        }
        System.out.println(count);
    }
}