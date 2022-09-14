package Practice2_DFS_BFS;

// 적록색약인 경우 초록색과 빨간색을 구분하지 못함 = 하나의 구역으로 본다
// 적록색약인 경우 dfs, 적록색약이 아닌 경우 dfs 각각 나눠서 생각?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon10026 {

    static String [][] grid; // 색칠한 그림을 이차원 배열로 나타낸 것
    static boolean [][] visited; // 방문 여부
    static int n;

    // 이동할 4가지 방향 정의 (상하 / 좌우가 겹치지 않게)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count; // 적록색약인 경우 빨간색과 초록색을 합쳐서 볼 때 구역의 수
    static int [] result = new int[3]; // 적록색약이 아닌 경우 각 색깔별 구역의 수를 담은 배열
    static String [] color = {"R","G","B"};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new String [n+1][n+1];
        visited = new boolean [n+1][n+1];
        for(int i = 1; i<n+1; i++) {
            String [] str = br.readLine().split("");
            for(int j = 1; j<n+1; j++) {
                grid[i][j] = str[j-1];
            }
        }

        // 적록색약이 아닌 경우
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<n+1; j++) {
                if(grid[i][j].equals("R") && !visited[i][j]) { // 빨간색 시작점
                    result[0]++;
                    dfs1(i,j, 0);
                } else if (grid[i][j].equals("G") && !visited[i][j]) { // 초록색 시작점
                    result[1]++;
                    dfs1(i,j, 1);
                } else if (grid[i][j].equals("B") && !visited[i][j]) { // 파란색 시작점
                    result[2]++;
                    dfs1(i,j, 2);
                }
            }
        }

        visited = new boolean [n+1][n+1]; // 적록색약 아닌 경우를 구하기 위해 초기화

        // 적록색약인 경우 > 초록색과 빨간색을 구분하지 않음
        // 파란색 구역의 수는 적록색약 여부와 상관없이 동일하다
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<n+1; j++) {
                if((grid[i][j].equals("R") || grid[i][j].equals("G")) && !visited[i][j]) { // 빨간색 or 초록색 시작점
                    count++;
                    dfs2(i,j);
                }
            }
        }

        int sum = 0;
        for(int i : result) {
            sum += i;
        }
        System.out.println(sum+" "+(count+result[2]));
    }

    // 적록색약이 아닌 경우의 dfs
    public static void dfs1 (int x, int y, int num) {
        visited[x][y] = true;
        for(int i= 0; i<dx.length; i++) {
            // 상하좌우 이동하며 탐색
            int xx = x+dx[i];
            int yy = y+dy[i];
            if (check1(xx, yy, num)) {
                dfs1(xx, yy, num); // 연결 되어있는 단지 탐색
            }
        }
    }

    // 적록색약인 경우의 dfs
    public static void dfs2 (int x, int y) {
        visited[x][y] = true;
        for(int i= 0; i<dx.length; i++) {
            // 상하좌우 이동하며 탐색
            int xx = x+dx[i];
            int yy = y+dy[i];
            if (check2(xx, yy)) {
                dfs2(xx, yy); // 연결 되어있는 단지 탐색
            }
        }
    }

    // 적록색약이 아닌 경우 조건
    public static boolean check1(int x, int y, int num) {
        // 노드가 범위 밖인 경우 [x(행), y(열)]
        if (x < 1 || x > n || y < 1 || y > n) {
            return false;
        }
        // 이미 방문한 노드인 경우 or 방문할 수 없는 곳인 경우
        if (visited[x][y] || !(grid[x][y].equals(color[num]))) { // 본인 색 제외하고는 전부 방문 불가
            return false;
        }
        return true;
    }

    // 적록색약인 경우 조건
    public static boolean check2(int x, int y) {
        // 노드가 범위 밖인 경우 [x(행), y(열)]
        if (x < 1 || x > n || y < 1 || y > n) {
            return false;
        }
        // 이미 방문한 노드인 경우 or 방문할 수 없는 곳인 경우
        if (visited[x][y] || grid[x][y].equals(color[2])) { // 파란색인 경우만 방문 불가
            return false;
        }
        return true;
    }
}
