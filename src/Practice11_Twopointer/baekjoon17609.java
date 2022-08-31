package Practice11_Twopointer;

// 2개의 포인터가 각각 배열의 시작점과 끝점에 위치
// 양 끝에서 탐색하며 같은 경우 왼쪽 포인터는 오른쪽으로 한 칸 이동, 오른쪽 포인터는 왼쪽으로 한 칸 이동
// 다른 문자가 나올 경우, 왼쪽을 제거하고 유사 회문 여부 체크 or 오른쪽을 제거하고 유사 회문 여부 체크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n; i++) {
            String str = br.readLine();
            sb.append(twoPointer(str)+"\n");
        }
        System.out.println(sb);
    }
    public static int twoPointer (String str) {
        char [] arr = str.toCharArray();
        int count = 0;
        int start = 0;
        int end = arr.length-1;
        while(start<=end) {
            if(arr[start] == arr[end]) { // 양쪽 문자가 일치할 경우
                start++;
                end--;
            } else { // 다른 경우
                count = 1;
                int left = start+1; // 왼쪽 문자 무시 후 회문 여부 체크
                int right = end;

                while(left<=right) {
                    if(arr[left] == arr[right]) {
                        left++;
                        right--;
                    } else {
                        count++;
                        break;
                    }
                }
                left = start;
                right = end-1; // 오른쪽 문자 무시 후 회문 여부 체크
                while(left<=right) {
                    if(arr[left] == arr[right]) {
                        left++;
                        right--;
                    } else {
                        count++;
                        break;
                    }
                }
                if(count>=2) { // 왼쪽과 오른쪽 모두 다른 경우 > 3, 둘 중 하나만 다른 경우 > 2
                    return count-1;
                } else {
                    return count;
                }
            }
        }
        return count; // 회문이라면 0 반환
    }
}
