import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        int n = sequence.length;
        int[] t1 = new int[n];
        int[] t2 = new int[n];

        for (int i = 0; i < n; i++) {
            int m1 = (i % 2 == 0) ? 1 : -1;
            t1[i] = sequence[i] * m1;

            int m2 = (i % 2 == 0) ? -1 : 1;
            t2[i] = sequence[i] * m2;
        }

        long[] dp1 = new long[n];
        dp1[0] = t1[0];
        long[] dp2 = new long[n];
        dp2[0] = t2[0];
        
        answer = Math.max(dp1[0], dp2[0]);
        for(int i = 1; i < n; i++){
            dp1[i] = Math.max(dp1[i-1] + t1[i], t1[i]);
            dp2[i] = Math.max(dp2[i-1] + t2[i], t2[i]);
            answer = Math.max(answer, Math.max(dp1[i], dp2[i]));
        }
        
        return answer;
    }
}