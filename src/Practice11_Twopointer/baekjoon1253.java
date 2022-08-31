package Practice11_Twopointer;

// 배열 내 숫자는 음수와 0도 가능하다
// 어떤 수가 다른 수 두 개의 합 = 좋은 수
// 두 포인터는 배열 정렬 후 양쪽 끝에 놓기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr); // 오름차순 정렬

        int count = 0;
        for(int i = 0; i<n; i++) {
            int start = 0;
            int end = arr.length-1;
            int sum = 0;
            while(start<end) {
                sum = arr[start] + arr[end];
                if(sum == arr[i]) { // 합이 배열 내 값과 동일한 경우
                    if(i == start) {
                        start++; // 두 수의 합에 자기 자신 포함하면 안됨
                    } else if (i == end) {
                        end--; // 두 수의 합에 자기 자신 포함하면 안됨
                    } else { // 다른 두 수의 합인 경우
                        count++; // 개수 늘려줌
                        break;
                    }
                }
                if(sum < arr[i]) { // 합을 늘려야 한다
                    start++;
                } else if (sum > arr[i]){ // 합을 줄여야 한다
                    end--;
                }
            }
        }
        System.out.println(count);
    }
}
