package Practice2_DFS_BFS;

// 이중 for문 사용할 경우 시간 초과 발생 > 삼차원 배열 사용해야 한다고 함....

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon2206 {

    static int n;
    static int m;
    static int [][] graph;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static int [][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n+1][m+1];
        for(int i = 1; i<n+1; i++) {
            String [] str= br.readLine().split("");
            for(int j = 1; j<m+1; j++) {
                graph[i][j] = Integer.parseInt(str[j-1]);
            }
        }
        int min = Integer.MAX_VALUE;
        int route = 0;
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<m+1; j++) {
                if(graph[i][j] == 1) {
                    graph[i][j] = 0;
                    route = BFS(1,1);
                    if(min>route && route != 0) {
                        min = route;
                    }
                    graph[i][j] = 1;
                }
            }
        }
        if(min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    protected static int BFS (int x, int y) {
        visited = new int[n+1][m+1];
        Queue<Wall> queue = new LinkedList<>();
        queue.offer(new Wall(x,y));
        visited[x][y] = 1;
        while(!queue.isEmpty()) {
            Wall wall = queue.poll();
            for(int i = 0; i< dx.length; i++) {
                int xx = wall.x + dx[i];
                int yy = wall.y + dy[i];
                if(check(xx, yy) && visited[xx][yy] == 0) { // 방문하지 않은 곳이여야 한다
                    visited[xx][yy] = visited[wall.x][wall.y] + 1;
                    queue.offer(new Wall(xx, yy));
                }
            }
        }
        return visited[n][m];
    }
    public static boolean check (int x, int y) {
        if(x < 1 || x > n || y < 1 || y > m || graph[x][y] == 1) { // 맵을 벗어나거나 방문할 수 없는 경우
            return false;
        }
        return true;
    }
}
class Wall {
    int x;
    int y;
    Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }
}