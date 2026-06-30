import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String operation : operations) {
            String[] parts = operation.split(" ");
            String command = parts[0];
            int num = Integer.parseInt(parts[1]);
            
            if (command.equals("I")) {
                minPq.add(num);
                maxPq.add(num);
            } 
            else if (command.equals("D")) {
                if (minPq.isEmpty()) {
                    continue;
                }
                
                if (num == 1) {
                    int max = maxPq.poll();
                    minPq.remove(max);
                } else if (num == -1) {
                    int min = minPq.poll();
                    maxPq.remove(min);
                }
            }
        }

        if (minPq.isEmpty()) {
            answer = new int[]{0, 0};
        } 
        else {
            answer = new int[]{maxPq.peek(), minPq.peek()};
        }
        
        return answer;
    }
}