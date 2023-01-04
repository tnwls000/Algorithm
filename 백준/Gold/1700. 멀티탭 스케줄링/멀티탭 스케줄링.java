import java.util.*;


class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 멀티탭 구멍의 개수
		int K = sc.nextInt(); // 전기 용품의 총 사용횟수
		List<Integer> items = new ArrayList<>();
		for (int i=0; i<K; i++) {
			items.add(sc.nextInt());
		}
		//1. 비어있는 멀티탭이 있으면 거기 꽂는다(이미 사용중이라면 넘어간다)
		//2. 비어있는 멀티탭이 없으면
		//2-1. 다시 사용하지 않을 전기 용품을 뺀다
		//2-2. 모두 다시 사용하는 전기 용품이라면 가장 나중에 사용하는 전기 용품을 뺀다
		//3. 뺀 그 자리에 꽂아야 할 전기 용품을 꽂는다
		
		List<Integer> multitap = new ArrayList<>();
		int count = 0;
		
		while (!items.isEmpty()) {
			int item = items.remove(0);
			//이미 사용 중이라면
			if (multitap.contains(item)) {
				continue;
			}
			//비어 있는 멀티탭이 있으면 거기 꽂는다
			if (multitap.size() < N) {
				multitap.add(item);
				continue;
			}
			//비어 있는 멀티탭이 없다면
			boolean flag = false;
			count++;
			int index = -1;
			int maxIndex = -1;
			for (int i=0; i<multitap.size(); i++) {
				//다시 사용하지 않는 용품이 있다면
				if (!items.contains(multitap.get(i))) {
					flag = true;
					multitap.remove(i);
					multitap.add(item);
					break;
				//모두 다시 사용한다면	
				} else {
					if (items.indexOf(multitap.get(i)) > index) {
						index = items.indexOf(multitap.get(i));
						maxIndex = i;
					}
				}
			}
			if (!flag) {
				multitap.remove(maxIndex);
				multitap.add(item);
			}
			
		}	
		System.out.println(count);
	}
}	