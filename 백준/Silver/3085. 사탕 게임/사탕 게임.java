import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int max = 0;
    static int N;
    static char[][] str;
    
    public static void arrCheck() {
        for (int i=0; i<N; i++) {
            int count = 1;
            for (int j=0; j<N-1; j++) {
                if (str[i][j] == str[i][j+1]) {
                    count++;
                } else count = 1;
                max = Math.max(max, count);
            }
        }
        
        for (int i=0; i<N; i++) {
            int count = 1;
            for (int j=0; j<N-1; j++) {
                if (str[j][i] == str[j+1][i]) {
                    count++;
                } else count = 1;
                max = Math.max(max, count);
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        str = new char[N][N];
        for (int i=0; i<N; i++) {
            String tmp = sc.next();
            for (int j=0; j<N; j++) {
            str[i][j] = tmp.charAt(j);
            }
        }
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<N-1; j++) {
                char temp = str[i][j];
                str[i][j] = str[i][j+1];
                str[i][j+1] = temp;
                arrCheck();
                temp = str[i][j];
                str[i][j] = str[i][j+1];
                str[i][j+1] = temp;
            }
        }
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<N-1; j++) {
                char temp = str[j][i];
                str[j][i] = str[j+1][i];
                str[j+1][i] = temp;
                arrCheck();
                temp = str[j][i];
                str[j][i] = str[j+1][i];
                str[j+1][i] = temp;
            }
        }
        System.out.println(max);
        sc.close();
    }
}