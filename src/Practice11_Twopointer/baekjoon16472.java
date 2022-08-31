package Practice11_Twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class baekjoon16472 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        translator(str);
    }

    public static void translator(String str) {
        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int max = 0; // 길이의 최댓값
        int length = 0;
        while (true) {
            if (map.size() > n) { // 알파벳의 종류가 n보다 많아지는 경우
                map.put(chars[start], map.get(chars[start]) - 1);
                if (map.get(chars[start]) == 0) {
                    map.remove(chars[start]);
                }
                start++;
            } else if (end == chars.length) { // 끝까지 탐색한 경우 종료
                break;
            } else if (map.size() <= n) {
                map.put(chars[end], map.getOrDefault(chars[end], 0) + 1);
                end++;
            }
            if (map.size() <= n) {
                length = end - start;
                if (length > max) {
                    max = length;
                }
            }
        }
       System.out.println(max);
    }
}
