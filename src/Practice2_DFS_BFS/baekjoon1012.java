package Practice2_DFS_BFS;

// dfs
// 배추 있는 칸 = 1, 배추 없는 칸 = 0

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon1012 {

    static boolean[][] field; // 배추밭 방문 여부
    static int[][] graph; // 배추가 있는지 없는지 이차원 배열로 표현
    // 이동할 방향 정의 (dx: 상하 / dy: 좌우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n; // 행
    static int m; // 열
    static int k;
    static int count = 0; // 배추 흰지렁이 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < test; h++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken()); // 가로 (열)
            n = Integer.parseInt(st.nextToken()); // 세로 (헹)
            k = Integer.parseInt(st.nextToken());
            field = new boolean[n][m];
            graph = new int[n][m];
            for (int i = 0; i < k; i++) {
                String [] str = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    int a = Integer.parseInt(str[0]); // 가로
                    int b = Integer.parseInt(str[1]); // 세로
                    graph[b][a] = 1;
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (graph[i][j] == 1 && !field[i][j]) { // 배추가 있으면서 방문하지 않은 곳 = dfs 시작점
                        count++;
                        dfs(i, j);
                    }
                }
            }
            sb.append(count+"\n");
            count = 0; // 다음 테스트 케이스 때 다시 사용하기 위해 0으로 초기화
        }
        System.out.println(sb);
    }

    public static void dfs(int x, int y) {
        field[x][y] = true;
        graph[x][y] = count;
        for (int i = 0; i < dx.length; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (check(xx, yy)) {
                dfs(xx, yy); // 연결 되어있는 곳 탐색
            }
        }
    }

    public static boolean check(int x, int y) {
        // 노드가 범위 밖인 경우 [x(행), y(열)]
        if (x < 0 || x > n - 1 || y < 0 || y > m - 1) {
            return false;
        }
        // 이미 방문한 경우 or 방문할 수 없는 곳인 경우 (배추 없는 곳)
        if (field[x][y] || graph[x][y] == 0) {
            return false;
        }
        return true;
    }
}

