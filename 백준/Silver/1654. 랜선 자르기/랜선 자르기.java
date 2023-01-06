import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt(); //이미 갖고 있는 랜선의 개수
		int n = sc.nextInt(); //필요한 랜선의 개수
		long[] arr = new long[k]; //갖고 있는 각 랜선의 길이
		for (int i=0; i<k; i++) {
			arr[i] = sc.nextLong();
		}
		Arrays.sort(arr);
		//정답: n개를 만들 수 있는 랜선의 최대 길이
		//n개보다 많이 만드는 것도 n개를 만드는 것이라 인정
		//mid = 랜선 최대 길이
		long left = 1; //랜선 길이 최솟값
		long right = 2147483648L; //랜선 길이 최댓값
		
		while (right - left > 1) {
			long mid = (left + right) / 2;
			long count = 0;
			//mid만큼 랜선을 차례대로 얼마나 만들 수 있는지 계산
			//n보다 많이 만들 수 있으면 left = mid
			//n개만큼 못만들면 right = mid
			for (int i=0; i<k; i++) {
				count += arr[i]/mid;
			}
			
			if (count >= n) left = mid;
			else right = mid;
		}
		System.out.println(left);
    }
}