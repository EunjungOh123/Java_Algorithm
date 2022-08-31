package Practice9_Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon1662 {
    static int i = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String ans = recursive(str);
        System.out.println(ans.length());
    }


    public static String recursive (String s) {
        StringBuilder sb = new StringBuilder();
        String tmp;
        int num = 0;

        while(i<s.length()) {
            if(Character.isDigit(s.charAt(i)) && s.charAt(i+1)=='(') { // isDigit()은 지정된 문자가 숫자인지 체크
                num = Character.getNumericValue(s.charAt(i));
                i++;
            } else if(s.charAt(i) == '(') {
                i++;
                tmp = recursive(s);
                for(int i = 0; i<num; i++) {
                    sb.append(tmp);
                }
            } else if (s.charAt(i) == ')') {
                i++;
                break;
            } else {
                sb.append(s.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}
