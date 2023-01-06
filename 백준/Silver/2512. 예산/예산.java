import java.util.Scanner;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //지방의 수
		long[] h = new long[n]; //각 지방의 예산요청
		for (int i=0; i<n; i++) {
			h[i] = sc.nextLong();
		}
		long m = sc.nextLong(); //총 예산
		
		//각 지방의 예산요청의 합이 총 예산보다 작거나 같을 경우 각 지방의 예산요청의 합을 반환
		//아닌 경우 상한액 설정
		// -> 지방예산이 상한액보다 크면 상한액만 제공
		// -> 지방예산이 상한액보다 작으면 지방예산 그대로 제공
		//중요) 가능한 최대의 총예산을 사용하기.
		
		Arrays.sort(h);
		long low = 0; //점점 증가할 변수
		long x = 100000; //최댓값
		
		long s = 0;
		for (int i=0; i<n; i++) {
			s += h[i];
		}
		if (s <= m) {
			low = h[n-1];
			x = h[n-1] + 1;
		}
		
		while (x - low > 1) {
			long sum = 0;
			long mid = (low + x) / 2; //중간길이 설정(상한액)
			
			for (int i=0; i<n; i++) {
				sum += Math.min(h[i], mid);
			}
			
			if (sum > m) x = mid;
			else low = mid;
		}
		System.out.println(low);
    }
}

