package Practice3_Floyd;

// 플로이드 워셜 알고리즘을 사용한 풀이
// 인접 행렬 사용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2660 {
    static int[][] members;
    static int n;
    static int INF = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        members = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j) { // 자기 자신
                    members[i][j] = 0;
                }
                members[i][j] = INF;
            }
        }
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) break;
            members[a][b] = 1;
            members[b][a] = 1;
        }
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (i == j) continue;
                    members[i][j] = Math.min(members[i][j], members[i][k] + members[k][j]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        int [] result = new int[n];
        for (int i = 1; i < n + 1; i++) {
            int max = 0;
            for (int j = 1; j < n + 1; j++) {
                if(members[i][j] != INF && members[i][j] > max) {
                    max = members[i][j];
                }
            }
            result[i-1] = max; // 각 사람마다 가장 높은 점수를 result 배열에 담는다
            if(max < ans) {
                ans = max; // 그 중 최소값을 갖는 사람이 회장이 될 수 있다
            }
        }
        int count = 0;
        for(int i = 0; i<n; i++) {
            if(result[i] == ans) {
                sb.append(i+1+" ");
                count++;
            }
        }
        System.out.println(ans+" "+count);
        System.out.println(sb);
    }
}
