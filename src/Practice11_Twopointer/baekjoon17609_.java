package Practice11_Twopointer;

// 회문 여부를 체크하는 메서드와 유사 회문 여부를 체크하는 메서드 두 가지를 별도로 만든다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon17609_ {
    static char [] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            arr = br.readLine().toCharArray();
            int start = 0;
            int end = arr.length-1;
            if(checkP(start, end)) { // 회문인 경우
                sb.append(0+"\n");
                continue;
            }
            if(checkS(start, end)) {
                sb.append(1+"\n");
            } else {
                sb.append(2+"\n");
            }
        }
        System.out.println(sb);
    }

    public static boolean checkP(int start, int end) { // 회문 여부 체크
        while (start <= end) {
            if (arr[start] != (arr[end])) { // 두 포인터가 가리키는 문자가 달라지는 순간 false 반환
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean checkS(int start, int end) { // 유사 회문 여부 체크
        while (start <= end) {
            if (arr[start]!=(arr[end])) { // 두 포인터가 가리키는 문자가 다른 경우
                boolean b1 = checkP(start + 1, end); // 왼쪽 문자 무시하고 나머지가 회문인지 체크
                boolean b2 = checkP(start, end - 1); // 오른쪽 문자 무시하고 나머지가 회문인지 체크
                if (b1 == false && b2 == false) {
                    return false; // 둘 다 회문이 될 수 없으면 유사 회문도 될 수 없음
                } else {
                    return true;
                }
            }
            start++;
            end--;
        }
        return true; // 회문인 경우 true 반환
    }
}
