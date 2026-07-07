class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] board = new int[n + 1][m + 1];
        boolean[][] flood = new boolean[n+1][m+1];
        
        for(int[] puddle: puddles){
            flood[puddle[1]][puddle[0]] = true;
        }
        
        board[1][1] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if((i == 1 && j == 1) || flood[i][j]){
                    continue;
                }
                board[i][j] = (board[i-1][j] + board[i][j-1]) % 1000000007;
            }
        }
        
        answer = board[n][m];
        return answer;
    }
}