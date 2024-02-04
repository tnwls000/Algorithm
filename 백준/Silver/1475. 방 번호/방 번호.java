import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 특이사항 : 6은 9를 뒤집어서, 9는 6을 뒤집어서 사용 가능
		// 다른 숫자 -> 겹치는거 하나라도 있으면 세트+1
		// 6,9 -> 겹치는거 두개까지 허용. 1~2개면 1세트, 3~4개면 2세트. 
		
		String n = br.readLine(); // 입력 : 다솜이 방 번호 N (1<= N <= 1,000,000) - 허용 시간복잡도 O(NlgN)
		int[] nums = new int[n.length()];
		for (int i=0; i<n.length(); i++) { // 숫자 배열에 각각 넣어주기
			nums[i] = n.charAt(i) - '0';
		}
		
		int[] count = new int[10]; // 카운팅 배열. 한 세트에 0~9까지 하나씩 있음
		for (int i=0; i<nums.length; i++) { // 숫자 배열 돌리면서 빈도수 세기
			count[nums[i]]++;
		}
		
		int notNineSix = 0;
		int nineSix = 0;
		for (int i=0; i<count.length; i++) {
			if (i == 6 || i == 9) { // 6이랑 9일 경우는 빈도수 합치기
				nineSix += count[i]; 
			} else { // 나머지는 최대 빈도수값 찾기
				notNineSix = Math.max(notNineSix, count[i]);
			}
		}
		
		// 6이랑 9일 경우
		if (nineSix % 2 == 0) { // 짝수는 개수/2, 홀수는 (개수/2)+1)가 필요 세트 개수
			nineSix /= 2;
		} else {
			nineSix = nineSix / 2 + 1; 
		}
		
		int result = Math.max(notNineSix, nineSix); // 최종 필요 세트 개수
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
	}
}