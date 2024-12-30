import java.util.*;

class Solution {
    static int max = 1;
    static boolean[] visited;
    static List<Integer>[] adjList;
    
    public int solution(int[] info, int[][] edges) {
        visited = new boolean[info.length];
        adjList = new ArrayList[info.length]; 
        for (int i=0; i<info.length; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int i=0; i<edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            adjList[a].add(b);
        }
        
        visited[0] = true;
        dfs(1, 0, 0, info);
        return max;
    }
    
    static void dfs(int sheep, int wolf, int x, int[] info) {
        max = Math.max(max, sheep);
        for (Integer y : adjList[x]) {
            if (visited[y]) continue;
            if (info[y] == 0) {
                visited[y] = true;
                List<Integer> origin = new ArrayList<>(adjList[y]);
                adjList[y].addAll(adjList[x]);
                dfs(sheep + 1, wolf, y, info);
                visited[y] = false;
                adjList[y] = new ArrayList<>(origin);
            }
            if (info[y] == 1 && sheep > wolf+1) {
                visited[y] = true;
                List<Integer> origin = new ArrayList<>(adjList[y]);
                adjList[y].addAll(adjList[x]);
                dfs(sheep, wolf + 1, y, info);
                visited[y] = false;
                adjList[y] = new ArrayList<>(origin);
            }
        }
    }
}