import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = 2;
        int P = 3;
        int V = 4;
        int count = 1;
        while (L != 0 && P != 0 && V != 0) {
            // 캠핑 가능일
            L = sc.nextInt();
            // 연속일
            P = sc.nextInt();
            // 휴가일
            V = sc.nextInt(); 
            if (L == 0 && P == 0 && V == 0) {
                break;
            }
            int camp = L * (V/P);
            if (V%P > L) {
                camp += L;
            } else camp += V%P;
            System.out.printf("Case %d: %d%n", count, camp);
            count++;
        }
        sc.close();
    }
}