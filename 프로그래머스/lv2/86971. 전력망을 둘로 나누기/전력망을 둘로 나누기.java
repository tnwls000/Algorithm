import java.util.Queue;
import java.util.LinkedList;

class Solution {
    static int arr[][];
    public int solution(int n, int[][] wires) {
        arr = new int[n+1][n+1];
        for (int i=0; i<wires.length; i++) {
            arr[wires[i][0]][wires[i][1]] = 1;
            arr[wires[i][1]][wires[i][0]] = 1;
        }
        
        int answer = n;
        int a, b;
        for (int i=0; i<wires.length; i++) {
            a = wires[i][0];
            b = wires[i][1];
            
            arr[a][b] = 0;
            arr[a][b] = 0;
            
            answer = Math.min(answer, dfs(n, a));
            
            arr[a][b] = 1;
            arr[a][b] = 1;
        }
        
        
        return answer;
    }
    
    private static int dfs(int n, int start) {
        int[] visited = new int[n+1];
        int count = 1;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        
        while (!q.isEmpty()) {
            int point = q.poll();
            visited[point] = 1;
            
            for (int i=1; i<=n; i++) {
                if (visited[i] == 1) continue;
                if (arr[point][i] == 1) {
                    q.offer(i);
                    count++;
                }
            }
        }
        return (int) Math.abs(n-2*count);
    }
}