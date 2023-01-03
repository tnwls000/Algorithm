import java.util.*;


class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
		int[][] time = new int[N][2];
		for (int i=0; i<N; i++){
			time[i][0] = sc.nextInt();
			time[i][1] = sc.nextInt();
		}
		
		Arrays.sort(time, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}	
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(time[0][1]);
		
		for (int i=1; i<N; i++){
			if (pq.peek() <= time[i][0]) pq.poll();
			pq.add(time[i][1]);
		}
		System.out.println(pq.size());
	}
}