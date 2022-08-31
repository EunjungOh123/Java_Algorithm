package Practice2_DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1 = 익은 토마토, 0 = 익지 않은 토마토, -1 = 토마토가 들어있지 않음
// 보관 후 하루 지나면 익은 토마토의 상하좌우에 있는 익지 않은 토마토가 익는다 (bfs 사용)
// 익은 토마토의 개수가 여러개인 경우도 있으므로 동시 출발 > 큐 사용

public class baekjoon7576 {

    // 이동할 4가지 방향 정의 (상하 / 좌우가 겹치지 않게)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int n;
    static int m;
    static int [][] grid;
    static Queue<Tomato> queue = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        grid = new int[n+1][m+1];
        for(int i = 1; i<n+1; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 1; j<m+1; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<m+1; j++) {
                if(grid[i][j] == 1) { // 익은 토마토가 있는 지점들을 모두 큐에 넣어준다
                    queue.offer(new Tomato(i,j));
                }
            }
        }
        bfs();
        int result = 0;
        Loop: for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<m+1; j++) {
                if(grid[i][j] == 0) { // 토마토가 모두 익지 못한 경우
                    result = -1;
                    break Loop;
                }
                if(grid[i][j] > result) { // grid 값 중 가장 큰 값에서 1을 뺀 값이 모두 익을 때까지의 최소 날짜
                    result = grid[i][j];
                }
            }
        }
        if(result == -1) {
            System.out.println(-1);
        } else {
            System.out.println(result - 1);
        }
    }

    public static void bfs () {
        while(!queue.isEmpty()) {
            Tomato tomato = queue.poll();
            int x= tomato.x;
            int y= tomato.y;

            for(int i = 0; i<dx.length; i++) {
                int xx = x+ dx[i];
                int yy = y+ dy[i];
                if(check(xx, yy) && grid[xx][yy] == 0) { // 상자를 벗어나지 않고 토마토가 익지 않았다면
                    grid[xx][yy] = grid[x][y] + 1;
                    queue.offer(new Tomato(xx, yy));
                }
            }
        }
    }

    public static boolean check(int x, int y) {
        // 상자 밖을 벗어나지 않아야 한다
        if (x < 1 || x > n || y < 1 || y > m) {
            return false;
        }
        return true;
    }
}

class Tomato {
    int x;
    int y;
    Tomato (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
