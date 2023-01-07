import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //사람 수
		int k = sc.nextInt(); //제거될 순번
		
		//1번부터 N번까지의 사람 큐에 넣기
		Queue<Integer> q = new LinkedList<>();
		for (int i=1; i<=n; i++) {
			q.offer(i);
		}
		
		String result = "<";
		while (q.size() > 1) {
			for (int i=0; i<k-1; i++) {
				q.offer(q.poll());
			}
			result += q.poll() + ", ";
		}
		result += q.poll() + ">";
		System.out.println(result);
    }
}