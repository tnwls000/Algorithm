import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //풍선 수
		//-n <= 종이 <= n, 종이 != 0
		
		//1번부터 N번까지의 풍선 종이 번호
		Deque<Integer> li = new ArrayDeque<>();
		Deque<Integer> index = new ArrayDeque<>();
		for (int i=1; i<=n; i++) {
			int input = sc.nextInt();
			li.offerLast(Integer.valueOf(input));
			index.offerLast(i);
		}
		
		//종이에 양수 적혀 있으면 오른쪽으로 이동(앞으로 당기기)
		//종이에 음수 적혀 있으면 왼쪽으로 이동(뒤로 밀기)

		//1번 풍선 터뜨리기
		int value = li.pollFirst();
		System.out.print(index.pollFirst());
		
		while (li.size() > 1) {
			if (value > 0) {
				for (int i=0; i<value-1; i++) {
					li.offerLast(li.pollFirst());
					index.offerLast(index.pollFirst());
				}
				value = li.pollFirst();
				System.out.print(" " + index.pollFirst());
			} else {
				value = (int) Math.abs(value);
				for (int i=0; i<value; i++) {
					li.offerFirst(li.pollLast());
					index.offerFirst(index.pollLast());
				}
				value = li.pollFirst();
				System.out.print(" " + index.pollFirst());
			}
		}
		System.out.print(" " + index.pollFirst());
    }
}