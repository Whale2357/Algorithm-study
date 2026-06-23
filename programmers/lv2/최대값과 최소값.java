class Solution {
    public String solution(String s) {
        String answer = "";
        String[] numbers = s.split("\\s+");
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (String numStr : numbers) {
            int num = Integer.parseInt(numStr);
            
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        
        answer = min + " " + max;
        return answer;
    }
}