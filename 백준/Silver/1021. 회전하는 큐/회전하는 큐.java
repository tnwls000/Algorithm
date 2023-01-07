import java.util.Scanner;
import java.util.LinkedList;


class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //큐의 크기
		int c = sc.nextInt(); //뽑아내려고 하는 수의 개수
		//큐 리스트
		LinkedList<Integer> li = new LinkedList<>();
		for (int i=0; i<n; i++) {
			li.add(i+1);
		}
		//뽑아내려고 하는 수
		LinkedList<Integer> numList = new LinkedList<>();
		for (int i=0; i<c; i++) {
			numList.add(Integer.valueOf(sc.nextInt()));
		}
		
		int count = 0;
		
		while (true) {
			int size = li.size();
			int first = li.get(0); //첫 번째 인덱스
			int last = li.get(size - 1); //마지막 인덱스
			int target = li.indexOf(numList.get(0)); //찾으려고 하는 원소 위치
			
			//뽑으려는 수가 큐의 첫번째에 있다면 바로 빼주기(1)
			if (numList.get(0) == first) {
				if (numList.size() == 1) {
					break;
				}
				li.removeFirst();
				numList.removeFirst();
				continue;
			}
			//마지막 원소보다 첫 번째 원소에 가깝다면 왼쪽으로 이동(2)
			if (target - 0 <= (size - 1) - target) {
				li.removeFirst();
				li.add(first);
				count++;
			} else {
			//첫 번째 원소보다 마지막 원소에 가깝다면 오른쪽으로 이동(3)
				li.removeLast();
				li.add(0, last);
				count++;
			}
		}
		System.out.println(count);
		
    }
}