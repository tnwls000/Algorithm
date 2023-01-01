import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] list = new int[9];
        boolean check = false;
        int sum = 0;
        for (int i=0; i<9; i++){
            list[i] = sc.nextInt();
            sum += list[i];
        }
        for (int a=0; a<8; a++){
            if (check) break;
            for (int b=a+1; b<9; b++){
                if (sum - list[a] - list[b] == 100) {
                    list[a] = 0;
                    list[b] = 0;
                    Arrays.sort(list);
                    check = true;
                    break;
                }
            }
        }
        
        for (int j=2; j<9; j++) {
            System.out.println(list[j]);
        }
        sc.close();
    }
}