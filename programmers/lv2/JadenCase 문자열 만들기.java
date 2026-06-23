class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isNextUpper = true; // 첫 글자는 무조건 대문자 대상

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append(c);
                isNextUpper = true; // 공백을 만났으므로 다음 글자는 대문자 후보
            } else {
                if (isNextUpper) {
                    sb.append(Character.toUpperCase(c));
                    isNextUpper = false;
                } else {
                    //나머지 글자를 소문자로 만들기 위해 toLowerCase() 사용
                    sb.append(Character.toLowerCase(c)); 
                }
            }
        }
        return sb.toString();
    }
}