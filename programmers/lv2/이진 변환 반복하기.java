class Solution {
    int convertcount = 0;
    int zerocount = 0;
    public int[] solution(String s) {
        int[] answer = {};

        binaryConvert(s);

        answer = new int[2]; 
        answer[0] = convertcount;
        answer[1] = zerocount;

        return answer;
    }

    public void binaryConvert(String s){
        if(s.equals("1")){
            return;
        }

        int originalLength = s.length();
        String removedZeroStr = s.replace("0", "");
        int newLength = removedZeroStr.length();

        zerocount += (originalLength - newLength);

        convertcount++;

        String nextStr = Integer.toBinaryString(newLength);

        binaryConvert(nextStr);
    }
}