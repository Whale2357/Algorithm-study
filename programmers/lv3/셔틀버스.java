import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (String time: timetable){
            int h = Integer.parseInt(time.substring(0,2));
            int min = Integer.parseInt(time.substring(3));
            pq.add(h*60 + min);
        }
        
        int start = 9 * 60;
        int answer = 0;
        
        for(int i = 0; i < n; i++){
            int crew = 0;
            int last_crew = 0;
            
            while(!pq.isEmpty() && crew < m && pq.peek() <= start){
                last_crew = pq.poll();
                crew++;
            }
            
            if(i == n-1){
               answer = (crew < m) ? start : last_crew - 1; 
            }
            
            start += t;
        }
        
        return String.format("%02d:%02d", answer / 60, answer % 60);
    }
}