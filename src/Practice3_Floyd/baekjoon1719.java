package Practice3_Floyd;

// ex) 1 > 6까지 가는 최단 경로는 1 > 2 > 5 > 6
// result[1][5] = 2 (1 > 5까지 가는 최단 경로의 가장 먼저 거치는 집하장)
// result[1][6] = result[1][5] = 2가 된다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1719 {
    static int n;
    static int m;
    static int [][] map; // 최단 경로를 담는 배열
    static int [][] result; // 가장 먼저 거치는 집하장을 담는 배열

    static int INF = 1000_0001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = new int[n+1][n+1];
        map = new int[n+1][n+1];
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<n+1; j++) {
                if(i == j) { // 자기 자신까지 가는 경로는 0이다
                    map[i][j] = 0;
                } else {
                    map[i][j] = INF;
                }
            }
        }
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            map[a][b] = time;
            map[b][a] = time;
            result[a][b] = b;
            result[b][a] = a;
        }
        for(int k = 1; k< n+1; k++) { // 중간에 거쳐가야할 집하장을 k
            for(int i = 1; i<n+1; i++) {
                for(int j = 1; j<n+1; j++) {
                    if(map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                        result[i][j] = result[i][k]; // 가장 먼저 거치는 집하장이 바뀐다
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<n+1; j++) {
                if(i == j) {
                    sb.append("-"+" ");
                } else {
                    sb.append(result[i][j]+" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
