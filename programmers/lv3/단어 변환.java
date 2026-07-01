import java.util.*;

class Solution {
    static class Node {
        String word;
        int count;

        Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    public int solution(String begin, String target, String[] words) {

        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();

        boolean[] visited = new boolean[words.length];

        queue.add(new Node(begin, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.word.equals(target)) {
                return current.count;
            }

            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && isOneCharDifferent(current.word, words[i])) {
                    visited[i] = true;
                    queue.add(new Node(words[i], current.count + 1));
                }
            }
        }

        return 0;
    }
    
    public static boolean isOneCharDifferent(String str1, String str2) {

        int diffCount = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diffCount++;
            }
            
            if (diffCount > 1) {
                return false;
            }
        }
        
        return diffCount == 1;
    }
}