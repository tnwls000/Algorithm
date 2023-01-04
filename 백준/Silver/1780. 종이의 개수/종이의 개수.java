import java.util.*;

class Main {
    static int n, paper[][], zero = 0, minus = 0, one = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        paper = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                paper[i][j] = sc.nextInt();
            }
        }
        countPaper(0, 0, n);
        System.out.println(minus);
        System.out.println(zero);
        System.out.println(one);
    }
    
    private static void countPaper(int r, int c, int size) {
        if (check(r, c, size)) {
            int num = paper[r][c];
            if (num == 0) zero++;
            if (num == 1) one++;
            if (num == -1) minus++;
            return;
        }
        
        int newSize = size/3;
        for (int i=r; i<r+size; i+=newSize) {
            for (int j=c; j<c+size; j+=newSize) {
                countPaper(i, j, newSize);
            }
        }
    }
    
    private static boolean check(int r, int c, int size) {
        for (int i=r; i<r+size; i++) {
            for (int j=c; j<c+size; j++) {
                if (paper[r][c] != paper[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}