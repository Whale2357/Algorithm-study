import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        
        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        int[] distance = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(1);
        visited[1] = true;
        distance[1] = 0;

        int maxDistance = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1;
                    maxDistance = Math.max(maxDistance, distance[neighbor]);
                    queue.add(neighbor);
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (distance[i] == maxDistance) {
                answer++;
            }
        }
        
        return answer;
    }
}