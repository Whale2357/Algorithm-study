import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int work : works) {
            pq.offer(work);
        }

        for (int i = 0; i < n; i++) {
            if (pq.isEmpty()) break;
            
            int maxWork = pq.poll();
            if (maxWork == 0) {
                return 0; 
            }
            pq.offer(maxWork - 1); 
        }
        
        while (!pq.isEmpty()) {
            long remain = pq.poll();
            answer += remain * remain;
        }
        
        return answer;
    }
}