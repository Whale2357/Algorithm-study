import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIdx = A.length - 1; 
        int bIdx = B.length - 1; 
        
        while (aIdx >= 0 && bIdx >= 0) {
            if (B[bIdx] > A[aIdx]) {
                answer++;
                bIdx--;
                aIdx--;
            } 
            else {
                aIdx--;
            }
        }
        
        return answer;
    }
}