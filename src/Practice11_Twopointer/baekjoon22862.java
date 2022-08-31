package Practice11_Twopointer;

// 짝수로 이루어져 있는 연속한 부분 수열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon22862 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int [] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int end = 0;
        int count = arr[start] % 2 == 0 ? 0 : 1;
        int length = arr[start] % 2 == 0 ? 1 : 0;
        while(end < n-1) {
            end++;
            if(arr[end] % 2 != 0) { // 포인터가 가리키는 값이 홀수라면
                count++;

                while(count > k) { // count 가 삭제할 수 있는 최대 횟수인 k보다 커진다면
                    if(arr[start] % 2 != 0) {
                        count--; // start 포인터가 가리키는 값이 홀수인 경우 count 하나 줄여줌
                    }
                    start++; // start 포인터를 오른쪽으로 한 칸 이동
                }
            }
            length = Math.max(length, end-start+1-count);
        }
        System.out.println(length);
    }
}
