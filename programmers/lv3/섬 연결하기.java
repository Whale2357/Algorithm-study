import java.util.*;

class Solution {
    private int[] parent;
    
    private int find(int i){
        if(parent[i] == i){
            return i;
        }
        return parent[i] = find(parent[i]);
    }
    
    private void union(int i, int j){
        int rootI = find(i);
        int rootJ = find(j);
        if(rootI != rootJ){
            parent[rootI] = rootJ;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        Arrays.sort(costs, (a,b) -> Integer.compare(a[2],b[2]));
        parent = new int[n];
        
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        
        int totalCost = 0;
        int edgesUsed = 0;
        
        for (int[] edge : costs) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];

            // 사이클이 형성되지 않는 경우에만 선택
            if (find(src) != find(dest)) {
                union(src, dest);
                totalCost += cost;
                edgesUsed++;

                if (edgesUsed == n - 1) break;
            }
        }
        
        answer = totalCost;
        
        return answer;
    }
}