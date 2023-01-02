import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] str = new int[45];
        for (int j=1; j<45; j++) {
            str[j] = j * (j+1) / 2;
        }
        
        for (int i=0; i<N; i++) {
            int K = sc.nextInt();
            int check = eureka(K, str);
            System.out.println(check);
        }
        sc.close();
    }
    
    public static int eureka(int K, int[] str) {
        for (int a=1; a<45; a++) {
            for (int b=1; b<45; b++) {
                for (int c=1; c<45; c++) {
                    if (str[a] + str[b] + str[c] == K) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}