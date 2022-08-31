package Practice3_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon13424 {
    static int n;
    static int m;
    static int [][] map;
    static int INF = 4950001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int i = 0; i<test; i++) {
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new int[n+1][n+1];
            for(int j = 1; j<n+1; j++) {
                for(int h = 1; h<n+1; h++) {
                    if(j == h) { // 자기 자신까지 가는 경로는 0
                        map[j][h] = 0;
                    } else {
                        map[h][j] = INF;
                    }
                }
            }
            for(int j = 0; j<m; j++) {
                st = new StringTokenizer(br.readLine()," ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());
                map[a][b] = distance; // 양방향 통행이 가능
                map[b][a] = distance;
            }
            for(int k = 1; k<n+1; k++) {
                for(int j = 1; j<n+1; j++) {
                    for(int h = 1; h<n+1; h++) {
                        if(map[j][h] > map[j][k] + map[k][h]) {
                            map[j][h] = map[j][k] + map[k][h];
                        }
                    }
                }
            }
            int num = Integer.parseInt(br.readLine()); // 친구들의 수
            int [] friends = new int[num]; // 친구들이 위치한 방 번호를 담은 배열
            int min = INF;  // 친구들의 이동 거리 총합의 최솟값
            int ans = 0;
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j<num; j++) {
                friends[j] = Integer.parseInt(st.nextToken());
            }

            for(int j = 1; j<n+1; j++) {
                int sum = 0; // 친구들의 이동 거리 총합
                for(int h = 0; h<num; h++) {
                    sum += map[friends[h]][j];
                }
                if(sum < min) {
                    min = sum;
                    ans = j;
                }
            }
            sb.append(ans+"\n");
        }
        System.out.println(sb);
    }
}
