import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int size = sticker.length;
        
        if(size == 1){
            return sticker[0];
        }
        else if(size == 2){
            return Math.max(sticker[0], sticker[1]);
        }
        else if(size == 3){
            return Math.max(Math.max(sticker[0], sticker[1]), sticker[2]);
        }
        
        int[] dp0 = new int[size];
        dp0[0] = sticker[0];
        dp0[1] = sticker[1];
        dp0[2] = sticker[0] + sticker[2];
        
        int[] dp1 = new int[size];
        dp1[1] = sticker[1];
        dp1[2] = sticker[2];
        
        
        for(int i = 3; i < size ; i++){
            dp0[i] = sticker[i] + Math.max(dp0[i - 3], dp0[i - 2]);
            dp1[i] = sticker[i] + Math.max(dp1[i - 3], dp1[i - 2]);
        }
        
        int max1 = Math.max(dp0[size - 2], dp0[size - 3]);
        int max2 = Math.max(dp1[size - 2], dp1[size - 1]);
        answer = Math.max(max1, max2);
        return answer;
    }
}