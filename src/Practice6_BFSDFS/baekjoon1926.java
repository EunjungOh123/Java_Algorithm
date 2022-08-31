package Practice6_BFSDFS;

// bfs & 인접 행렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1926 {

    static int n; // 도화지 세로 크기
    static int m; // 도화지 가로 크기
    static int [][] picture; // 그림 색칠 여부 이차원 배열로 표현
    static boolean [][] visited; // 방문 여부 체크
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        picture = new int[n+1][m+1];
        visited = new boolean[n+1][m+1];
        for(int i = 1; i<n+1; i++) {
            String [] str = br.readLine().split(" ");
            for(int j = 1; j<m+1; j++) {
                picture[i][j] = Integer.parseInt(str[j-1]);
            }
        }
        int count = 0;
        int max = 0;
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<m+1; j++) {
                if(picture[i][j] == 1 && !visited[i][j]) {
                    count++;
                    int ans = BFS(i, j);
                    if(ans>max) {
                        max = ans;
                    }
                }
            }
        }
        if(count == 0) {
            max = 0;
        }
        System.out.println(count);
        System.out.println(max);
    }
    public static int BFS (int x, int y) {
        Queue <Picture> queue = new LinkedList<>();
        queue.offer(new Picture(x, y));
        visited[x][y] = true;
        int area = 1;
        while(!queue.isEmpty()) {
            Picture p = queue.poll();
            for(int i = 0; i<dx.length; i++) {
                int xx = p.x +dx[i];
                int yy = p.y +dy[i];
                if(check(xx, yy) && picture[xx][yy] == 1) { // 이동할 수 있는 곳인 경우
                    visited[xx][yy] = true;
                    area++;
                    queue.offer(new Picture(xx, yy));
                }
            }
        }
        return area;
    }

    public static boolean check (int x, int y) { // 그림의 범위를 벗어나지 않고 방문한 적이 없어야 함
        if(x<1 || y<1 || x>n || y>m || visited[x][y]) {
            return false;
        }
        return true;
    }
}
class Picture {
    int x;
    int y;
    Picture (int x, int y) {
        this.x = x;
        this.y = y;
    }
}