import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> gemTypes = new HashSet<>(Arrays.asList(gems));
        int totalTypes = gemTypes.size();

        Map<String, Integer> gemMap = new HashMap<>();

        int[] answer = new int[2];
        int minLength = Integer.MAX_VALUE;
        int left = 0;

        for (int right = 0; right < gems.length; right++) {
            String rightGem = gems[right];
            gemMap.put(rightGem, gemMap.getOrDefault(rightGem, 0) + 1);

            while (gemMap.size() == totalTypes) {
                int currentLength = right - left + 1;

                if (currentLength < minLength) {
                    minLength = currentLength;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }

                String leftGem = gems[left];
                gemMap.put(leftGem, gemMap.get(leftGem) - 1);

                if (gemMap.get(leftGem) == 0) {
                    gemMap.remove(leftGem);
                }

                left++;
            }
        }

        return answer;
    }
}