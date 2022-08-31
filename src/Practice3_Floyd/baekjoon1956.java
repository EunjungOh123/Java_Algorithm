package Practice3_Floyd;

// 도로의 길이의 합이 가장 작은 사이클
// 다익스트라 알고리즘보다 플로이드–워셜 알고리즘을 사용하는 것이 좋다

// 다익스트라 : 그래프 내 하나의 정점에서 모든 정점으로의 최단 경로를 구하는 알고리즘

// 플로이드 워셜 : 그래프 내 모든 정점에서 모든 정점으로의 최단 경로를 구하는 알고리즘 (삼중 for 문을 사용)
// 모든 정점 간의 최단거리를 구해야 하므로 2차원 인접 행렬을 사용 + 단계마다 거쳐가는 정점을 기준으로 최단 거리를 구한다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1956 {

    static int village;
    static int road;
    static int [][] result;
    static int INF = 4000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        village = Integer.parseInt(st.nextToken());
        road = Integer.parseInt(st.nextToken());

        result = new int[village+1][village+1];

        for(int i = 1; i<village+1; i++) {
            for(int j = 1; j< village+1; j++) {
                if(i == j) { // 자기 자신
                    result[i][j] = 0;
                }
                result[i][j] = INF;
            }
        }
        for(int i = 0; i<road; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            result[a][b] = length;
        }
        for(int k = 1; k<village+1; k++) { // 거쳐갈 정점
            for(int i = 1; i<village+1; i++) {
                for(int j = 1; j<village+1; j++) {
                    if(i == j) continue;
                    result[i][j] = Math.min(result[i][j], result[i][k]+result[k][j]);
                }
            }
        }
        int ans = INF;
        for(int i = 1; i<village+1; i++) {
            for(int j = 1; j< village+1; j++) {
                if(i == j) continue;
                if(result[i][j] != INF && result[j][i] != INF) {
                    ans = Math.min(result[i][j]+result[j][i], ans);
                }
            }
        }
        ans = (ans == INF) ? -1 : ans;
        System.out.println(ans);
    }
}
