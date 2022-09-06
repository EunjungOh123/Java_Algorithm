package Practice13_DP;

// 시간 제한 조건 때문에 이차원 배열이 아닌 일차원 배열로 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int [] coin = new int[n+1];
        for(int i = 1; i<=n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        int [] dp = new int[k+1];
        dp[0] = 1;
        for(int i = 1; i<=n; i++) {
            for(int j = coin[i]; j<=k; j++) {
                dp[j] = dp[j] + dp[j-coin[i]];
            }
        }
        System.out.println(dp[k]);
    }
}
