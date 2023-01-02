import java.util.Scanner;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] str = new int[45];
        for (int j=1; j<45; j++) {
            str[j] = j * (j+1) / 2;
        }
        
        
        for (int i=0; i<N; i++) {
            int K = sc.nextInt();
            boolean check = false;
            
            for (int a=1; a<45; a++) {
                if (check) break;
                for (int b=1; b<45; b++) {
                    if (check) break;
                    for (int c=1; c<45; c++) {
                        if (str[a] + str[b] + str[c] == K) {
                            check = true;
                            break;
                        }
                    }
                }
            }
            
            if (check) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
            
        }
        sc.close();
    }
}
