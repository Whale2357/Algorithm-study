class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int height = triangle.length - 1;
        
        int[][] dp = new int[height + 1][height + 1];
        for(int i = 0; i < triangle[height].length; i++){
            dp[height][i] = triangle[height][i];
        }
        
        
        for(int i = height; i > 0; i--){
            for(int j = 0; j < triangle[i - 1].length; j++){
                dp[i - 1][j] = triangle[i - 1][j] + Math.max(dp[i][j], dp[i][j + 1]);
            }
        }
        
        answer = dp[0][0];
        return answer;
    }
}