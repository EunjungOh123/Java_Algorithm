package Practice12_Greedy;

// AABC와 DDDD가 있다고 하자
// AABC = 1000A + 100A + 10B + 1C
// DDDD = 1000D + 100D + 10D + 1D
// 알파벳 배열 A 인덱스에 1000, B 인덱스에 10, C 인덱스에 1, D 인덱스에 1000+100+10+1의 값이 들어간다
// alphabet[0] = 1100, alphabet[1] = 10, alphabet[3] = 1, alphabet[4] = 1111
// 해당 배열을 내림차순으로 정렬,  1111*9 + 1000*8 + 10*7 + 1*6 = 18875

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class baekjoon1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Integer [] alphabet = new Integer [26]; // 알파벳 배열 ([A: 0]~[Z: 26]까지)
        Arrays.fill(alphabet, 0);  // int 배열의 초기값은 0이지만 객체 배열의 초기값은 null > 따로 0으로 초기화 필요
        for(int i = 0; i<num; i++) {
            String str = br.readLine();
            int multiple = 1;
            for(int j = str.length()-1; j>=0; j--) {
                alphabet[str.charAt(j)-'A'] += multiple;
                multiple *= 10;
            }
        }
        Arrays.sort(alphabet, Collections.reverseOrder()); // 내림차순 정렬
        int n = 9;
        int answer = 0;
        for(int i = 0; i<alphabet.length; i++) {
            if(alphabet[i] == 0) break; // 이미 정렬한 상태이므로 더 이상 반복문 실행할 필요 없음
            answer += alphabet[i] * n;
            n--;
        }
        System.out.println(answer);
    }
}