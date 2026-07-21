import java.util.*;

class Solution {
    private HashSet<String> resultSets = new HashSet<>();
    private boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[user_id.length];
        
        dfs(0, "", user_id, banned_id);
        
        return resultSets.size();
    }

    private void dfs(int depth, String currentPattern, String[] user_id, String[] banned_id) {
        if (depth == banned_id.length) {
            char[] chars = currentPattern.toCharArray();
            Arrays.sort(chars);
            resultSets.add(new String(chars));
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (visited[i] || !isMatch(user_id[i], banned_id[depth])) {
                continue;
            }

            visited[i] = true;
            dfs(depth + 1, currentPattern + i, user_id, banned_id);
            visited[i] = false; 
        }
    }

    private boolean isMatch(String user, String banned) {
        if (user.length() != banned.length()) {
            return false;
        }
        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) != '*' && user.charAt(i) != banned.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}