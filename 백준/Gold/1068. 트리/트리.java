import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] parentArr = new int[n];
        List<Integer>[] list = new ArrayList[n+1];
        for (int i=0; i<=n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i=0; i<n; i++) {
            int parent = sc.nextInt();
            if (parent != -1) {
                parentArr[i] = parent;
                list[parent].add(i);
            }
            else {
                parentArr[i] = n;
                list[n].add(i);
            }
        }

        int deleteNode = sc.nextInt();
        int deleteParent = parentArr[deleteNode];
        for (int i=0; i<n; i++) {
            if (list[deleteParent].get(i) == deleteNode) {
                list[deleteParent].remove(i);
                break;
            }
        }

        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int node : list[curr]) {
                if (!list[node].isEmpty()) q.add(node);
                else cnt++;
            }
        }
        System.out.println(cnt);
    }
}