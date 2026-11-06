import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        if(n < 3){
            return n;
        }
        
        int[] left_min = new int[n];
        int[] right_min = new int[n];
        
        left_min[0] = a[0];
        for(int i = 1; i < n; i++){
            left_min[i] = Math.min(left_min[i-1], a[i]);
        }
        
        right_min[n - 1] = a[n - 1];
        for(int i = n-2; i >= 0; i--){
            right_min[i] = Math.min(right_min[i+1], a[i]);
        }
        
        int answer = 2;
        for(int i = 1; i < n - 1; i++){
            if(a[i] < left_min[i - 1] || a[i] < right_min[i + 1]){
                answer++;
            }
        }
        
        return answer;
    }
}