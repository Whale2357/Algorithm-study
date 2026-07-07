import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });

        int prev_end_time = -30001; 
        
        for (int i = 0; i < routes.length; i++) {
            if (routes[i][0] > prev_end_time) {
                prev_end_time = routes[i][1]; 
                answer++;
            }
        }
        
        return answer;
    }
}