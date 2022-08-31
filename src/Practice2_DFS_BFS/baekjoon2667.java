package Practice2_DFS_BFS;

// 단지번호 붙이기
// 단지 개수와 각 단지에 속하는 집의 수 오름차순 정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon2667 {

    static int n;
    static int[][] graph;
    static boolean[][] visit; // 방문 여부

    // 이동할 4가지 방향 정의 (상하 / 좌우가 겹치지 않게)
    static int[] dx = {-1, 1, 0, 0}; // 행
    static int[] dy = {0, 0, -1, 1}; // 열

    static int number = 0; // 총 단지 수


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n + 1][n + 1];
        visit = new boolean[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            String[] str = br.readLine().split("");
            for (int j = 1; j < n + 1; j++) {
                graph[i][j] = Integer.parseInt(str[j - 1]);
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (graph[i][j] == 1 && !visit[i][j]) { // 집이 있으면서 방문하지 않은 곳 = dfs 시작점
                    number++;
                    dfs(i, j);
                }
            }
        }
        // 단지 수
        System.out.println(number);
        // 단지 내 집의 수
        int[] count = new int[number];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (graph[i][j] != 0) { // 집이 있는 위치
                    count[graph[i][j]-1]++; // 인덱스 0에 1번 단지 집의 개수, 인덱스 1에 2번 단지 집의 개수 ...
                }
            }
        }
        Arrays.sort(count);
        for (int i : count) {
            System.out.println(i);
        }
    }

    public static void dfs(int x, int y) {
        visit[x][y] = true;
        graph[x][y] = number;
        // 상하좌우 이동하며 탐색
        for (int i = 0; i < dx.length; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (check(xx, yy)) {
                dfs(xx, yy); // 연결 되어있는 단지 탐색
            }
        }
    }

    public static boolean check(int x, int y) {
        // 노드가 범위 밖인 경우 [x(행), y(열)]
        if (x < 1 || x > n || y < 1 || y > n) {
            return false;
        }
        // 이미 방문한 노드인 경우 or 방문할 수 없는 곳인 경우
        if (visit[x][y] || graph[x][y] == 0) {
            return false;
        }
        return true;
    }
}
