package Practice6_BFSDFS;

// 주의 : 아무 지역도 물에 잠기지 않을 수 있다
// ex) 지역의 모든 영역의 높이가 1일 때 비가 오지 않으면 아무 지역도 물에 잠기지 않는다 > 안전한 영역은 1개

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon2468 {

    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] grid; // 지역 내 영역별 높이 정보를 담은 이차원 배열
    static boolean[][] visited; // 방문 여부 체크
    static int flood; // 비의 양 (영역의 높이가 flood 보다 커야 안전 영역이다)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        int max = 0;
        for (int i = 1; i < n + 1; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 1; j < n + 1; j++) {
                grid[i][j] = Integer.parseInt(str[j - 1]);
                if(grid[i][j] > max) {
                    max = grid[i][j]; // 지역 내 영역의 높이 최댓값
                }
            }
        }

        int ans = 0;
        for (int h = 0; h <= max; h++) { // 비가 오지 않는 경우부터 영역의 높이 최댓값만큼 비가 오는 경우까지 각각의 안전한 영역의 개수를 구함
            visited = new boolean[n + 1][n + 1];
            flood = h;
            int count = 0;
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (grid[i][j] > flood && !visited[i][j]) {
                        count++;
                        BFS(i, j, flood);
                    }
                }
            }
            if (count > ans) {
                ans = count; // 안전한 영역의 최대 개수
            }
        }
        System.out.println(ans);
    }

    public static void BFS(int x, int y, int flood) {
        Queue<Safe> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.offer(new Safe(x, y));
        while (!queue.isEmpty()) {
            Safe safe = queue.poll();
            for (int i = 0; i < dx.length; i++) {
                int xx = safe.x + dx[i];
                int yy = safe.y + dy[i];
                if (check(xx, yy) && grid[xx][yy] > flood) { // 이동할 수 있는 곳이고 해당 영역의 높이가 flood 보다 큰 경우
                    visited[xx][yy] = true;
                    queue.offer(new Safe(xx, yy));
                }
            }
        }
    }

    public static boolean check(int x, int y) {
        if (x > n || y > n || x < 1 || y < 1 || visited[x][y]) { // 지역을 벗어나지 않고 방문한 적이 없어야 함
            return false;
        }
        return true;
    }
}

class Safe {
    int x;
    int y;

    Safe(int x, int y) {
        this.x = x;
        this.y = y;
    }
}