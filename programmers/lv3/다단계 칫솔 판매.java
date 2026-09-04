import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> profit = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
            profit.put(enroll[i], 0);
        }
        
        for (int i = 0; i < seller.length; i++) {
            String curr = seller[i];
            int money = amount[i] * 100; 

            while (!curr.equals("-") && money > 0) {
                int give = money / 10;
                int keep = money - give;

                profit.put(curr, profit.get(curr) + keep);

                // 부모 노드로 이동
                curr = parent.get(curr);
                money = give;
            }
        }
        
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = profit.get(enroll[i]);
        }
        
        return answer;
    }
}