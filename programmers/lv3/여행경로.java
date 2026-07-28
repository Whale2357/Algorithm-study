import java.util.*;

class Solution {
    private boolean[] visited;
    private String[] answer;
    
    public String[] solution(String[][] tickets) {
        
        int n = tickets.length;
        visited = new boolean[n];
        
        Arrays.sort(tickets, (a,b) -> {
            if(a[0].equals(b[0])){
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[1]);
        });
        
        List<String> path = new ArrayList<>();
        path.add("ICN");
        
        dfs("ICN", tickets, path, 0);
        
        return answer;
    }
    
    private boolean dfs(String current, String[][] tickets, List<String> path, int count){
        if(count == tickets.length){
            answer = path.toArray(new String[0]);
            return true;
        }
        
        for(int i = 0; i < tickets.length; i++){
            if(!visited[i] && tickets[i][0].equals(current)){
                visited[i] = true;
                path.add(tickets[i][1]);
                
                if(dfs(tickets[i][1], tickets, path, count + 1)){
                    return true;
                }
                
                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
        
        return false;
    }
}