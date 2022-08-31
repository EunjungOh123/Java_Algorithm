package Practice13_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가장 긴 감소하는 부분 수열
// {10, 30, 10, 20, 20, 10} > {30, 20, 10} : 길이는 3

public class baekjoon11722 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int [] arr = new int[n];
        for(int i = 0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int [] dp = new int[n];
        int max = 0;
        for(int i = 0; i<arr.length; i++) {
            dp[i] = 1;
            for(int j = 0; j<i; j++) { // i번째 이전의 원소들을 탐색 (이전 원소들의 값은 i번째 원소의 값보다 커야 한다)
                if(arr[j] > arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
