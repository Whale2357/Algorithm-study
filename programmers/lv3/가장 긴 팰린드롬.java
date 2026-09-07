class Solution
{
    public int solution(String s){
        int n = s.length();
        int answer = 1;
        
        for(int i = 0; i < n; i++){
            answer = Math.max(answer, f(s, i, i));
            answer = Math.max(answer, f(s, i, i + 1));
        }

        return answer;
    }
    
    public int f(String s, int start, int end){
        
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            start--;
            end++;
        }
        
        return end - start - 1;
    }
}