import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
     
    static boolean isSudoku; // 플래그 변수
    static int[][] sudoku; // 스도쿠 퍼즐 배열
    static boolean[] nums; // 중복 숫자 확인 배열
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        for (int t=1; t<=T; t++) {
             
            isSudoku = true; 
            sudoku = new int[10][10];
            // 스도쿠 퍼즐 입력 받기
            for (int r=1; r<=9; r++) {
                nums = new boolean[10];
                st = new StringTokenizer(br.readLine());
                 
                for (int c=1; c<=9; c++) {
                    int num = Integer.parseInt(st.nextToken());
                     
                    // (행) 숫자 검증
                    if (nums[num] && isSudoku) { 
                        isSudoku = false;
                    }
                    nums[num] = true;
                    sudoku[r][c] = num;
                }
            }
             
            // 3*3 숫자 검증
            checkThree();
             
            // (열) 숫자 검증 - 열 우선 순회
            checkCol();
             
            // 결과 출력
            sb.append("Case " + t + ": ");
            if (isSudoku) sb.append("CORRECT\n");
            else sb.append("INCORRECT\n");
            if (t < T) br.readLine(); // 테스트 케이스 사이 빈 줄 존재
        }
        System.out.println(sb);
    }
     
    static void checkThree() {
        if (isSudoku) { // 이미 스도쿠 아닌 거 확정이면 무시
            threeThree: for (int r=1; r<=7; r+=3) {
                for (int k=1; k<=7; k+=3) { // 열 시작인덱스
                     
                    nums = new boolean[10];
                    for (int dr=r; dr<=r+2; dr++) {
                        for (int c=0; c<3; c++) {
                            if (nums[sudoku[dr][c+k]]) {
                                isSudoku = false;
                                break threeThree;
                            }
                            nums[sudoku[dr][c+k]] = true;
                        }
                    }
                     
                }
            }
        }
    }
     
    static void checkCol() {
        if (isSudoku) { // 이미 스도쿠 아닌 거 확정이면 무시
            col: for (int c=1; c<=9; c++) {
                nums = new boolean[10];
                for (int r=1; r<=9; r++) {
                    if (nums[sudoku[r][c]]) {
                        isSudoku = false;
                        break col;
                    }
                    nums[sudoku[r][c]] = true;
                }
            }
        }
    }
}