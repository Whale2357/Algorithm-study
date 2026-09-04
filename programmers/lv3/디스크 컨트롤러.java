import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int n = jobs.length;
        
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        
        int end = 0;
        int count = 0;
        int idx = 0;
        
        while(count < n){
            while(idx < n && jobs[idx][0] <= end){
                pq.offer(jobs[idx]);
                idx++;
            }
            
            if(pq.isEmpty()){
                end = jobs[idx][0];
            }
            else{
                int[] cur = pq.poll();
                end += cur[1];
                answer += (end - cur[0]);
                count++;
            }
        }
        return answer/n;
    }
}