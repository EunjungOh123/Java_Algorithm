package Practice13_DP;

// 이차원 배열을 사용한 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<test; i++) {

            int n = Integer.parseInt(br.readLine());

            int [] coin = new int[n+1];

            st = new StringTokenizer(br.readLine(), " ");

            for(int j = 1; j<=n; j++) {
                coin[j] = Integer.parseInt(st.nextToken());
            }

            int amount = Integer.parseInt(br.readLine()); // 만들어야 할 금액

            int [][] dp = new int[n+1][amount+1];
            for(int j = 1; j<=n; j++) {
                dp[j][0] = 1; // 0번째 열은 모두 1로 초기화
                // 어떤 동전이든 0원을 만들 수 있는 가지수는 무조건 1가지 존재 > ex : 1원 동전 0개로 0원을 만들 수 있음
            }

            for(int j = 1; j<=n; j++) {
                for(int h = 1; h<=amount; h++) {
                    if(h>=coin[j]) {
                        dp[j][h] = dp[j-1][h] + dp[j][h-coin[j]];
                    } else {
                        dp[j][h] = dp[j-1][h]; // h원을 만들기 위해 동전 coin[j]를 사용할 수 없는 경우 > 기존에서 추가되는 경우의 수 없음
                    }
                }
            }

            sb.append(dp[n][amount]+"\n");

        }
        System.out.println(sb);
    }
}
