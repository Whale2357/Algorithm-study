class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int position = 1;
        int idx = 0;
        int range = 2 * w + 1;
        
        while(position <= n){
            if(idx < stations.length && position >= stations[idx] - w){
                position = stations[idx] + w + 1;
                idx++;
            }
            else{
                position += range;
                answer++;
            }
        }
        
        return answer;
    }
}