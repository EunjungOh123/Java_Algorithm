package Practice3_Floyd;

// 키를 비교할 수 있는 학생의 수를 구하기 위해서는 그 학생보다 키가 작거나 키가 큰 경우가 존재해야 함
// 학생이 자신의 키가 몇 번째인지 알기 위해서는 그 학생이 모든 학생과 연결되어 있어야 한다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon2458 {
    static int n;
    static int m;
    static int[][] grid;
    static int INF = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(grid[i], INF); // 원래 자기 자신으로 가는 경로는 0이지만 편의를 위해 INF 로 초기화
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            grid[a][b] = 1;
        }
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (i == j) continue;
                    grid[i][j] = Math.min(grid[i][j], grid[i][k] + grid[k][j]);
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            int count = 0;
            for (int j = 1; j < n + 1; j++) {
                if (grid[i][j] != INF || grid[j][i] != INF) { // i보다 큰 사람이 존재 또는 i보다 작은 사람이 존재하는 경우
                    count++;
                }
            }
            if (count == n - 1) { // 자기 자신을 제외하고 모든 정점과 연결되어 있는 경우 (키 순서 파악 가능)
                ans++;
            }
        }
        System.out.println(ans);
    }
}