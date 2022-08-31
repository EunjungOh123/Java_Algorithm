package Practice3_Floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon10159 {

    static int n;
    static int m;
    static int[][] grid;
    static int INF = 200001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        grid = new int[n+1][n+1];
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j< n+1; j++) {
                if(i == j) { // 자기 자신으로 가는 최단 경로는 0
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = INF;
                }
            }
        }
        StringTokenizer st;
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine()," ");
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

        // 비교 결과를 알 수 없기 위해서는 정점 간에 경로가 없어야 한다 (경로가 있는 경우 비교할 수 있다)
        // 각 물건마다 다른 물건과의 경로가 양방향으로 없는 경우의 개수를 구한다
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<n+1; i++) {
            int count = 0;
            for(int j = 1; j<n+1; j++) {
                if(grid[i][j] == INF && grid[j][i] == INF) count++;
            }
            sb.append(count+"\n");
        }
        System.out.println(sb);
    }
}
