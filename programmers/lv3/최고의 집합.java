import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        
        if(n > s){
            int[] answer = {-1};
            return answer;
        }
        
        int[] answer = new int[n];
        
        int base = s / n; 
        int remainder = s % n; 
        
        for (int i = 0; i < n; i++) {
            if (i < n - remainder) {
                answer[i] = base; 
            } else {
                answer[i] = base + 1;   
            }
        }
        
        return answer;
    }
}