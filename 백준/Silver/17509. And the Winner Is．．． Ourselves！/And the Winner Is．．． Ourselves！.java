import java.util.Scanner;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] D = new int[11];
        int[] V = new int[11];
        int penalty = 0;
        int time = 0;
        for (int i=0; i<11; i++) {
            D[i] = sc.nextInt();
            V[i] = sc.nextInt();
        }
        Arrays.sort(D);
        for (int i=0; i<11; i++) {
            for (int j=0; j<i+1; j++) {
                penalty += D[j];
            }
            time += D[i];
            if (time > 300) {
                break;
            }
            penalty += V[i]*20;
        }
        System.out.println(penalty);
    }
}