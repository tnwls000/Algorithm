import java.util.Scanner;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //나무의 수
		long m = sc.nextLong(); //집으로 가져가는 나무의 길이
		long[] h = new long[n]; //나무의 높이
		
		for (int i=0; i<n; i++) {
			h[i] = sc.nextLong();
		}
		long low = 0; //점점 증가할 변수
		long x = 1000000000; //절단기 높이를 나무의 최대 높이로 설정
		
		while (low + 1 < x) {
			long sum = 0;
			long mid = (low + x) / 2; //중간길이 설정
			for (int i=0; i<n; i++) {
				if (h[i] > mid) sum += h[i] - mid;
			}
			
			//계산한 총합이 가져가려는 길이보다 많다면(조건 부합)
			if (sum >= m) low = mid;
			else x = mid;
		}
		System.out.println(low);
		
		//절단기에 설정할 수 있는 높이의 최댓값이 변수
		//나무의 높이가 변수보다 낮거나 같다면 -> 0
		//나무의 높이가 변수보다 높다면 -> h-m
		//계산한 총합이 가져가려는 길이 m보다 많다면 true 아니라면 false
    }
}