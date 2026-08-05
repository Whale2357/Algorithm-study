import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int minMax = Integer.MAX_VALUE;

        for (int i = 0; i < stones.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && stones[deque.peekLast()] <= stones[i]) {
                deque.pollLast();
            }

            deque.addLast(i);

            if (i >= k - 1) {
                minMax = Math.min(minMax, stones[deque.peekFirst()]);
            }
        }

        return minMax;
    }
}