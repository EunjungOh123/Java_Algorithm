package Practice2_DFS_BFS;

// bfs 사용
// 칸을 셀 때에는 시작 위치와 도착 위치도 포함

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon2178 {

    static int [][] graph; // 미로를 이차원 배열로 나타낸 것
    static int n;
    static int m;

    // 이동할 4가지 방향 정의
    static int [] dx = {-1,1,0,0}; // 좌(-1), 우(1)
    static int [] dy = {0,0,-1,1}; // 하(-1), 상(1)

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
        bfs(1,1);
    }
    public static void bfs (int x, int y) {
        Queue<Node>queue = new LinkedList<>();
        queue.offer(new Node(x,y)); // 큐에 시작점 추가
        graph[x][y] = 1; // 시작점

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            // 현재 노드 node.x, node.y에서 상하좌우 4가지 방향 노드에 대한 작업
            for(int i = 0; i< dx.length; i++) {
                int xx = node.x + dx[i];
                int yy = node.y + dy[i];
                if(check(xx,yy) && graph[xx][yy] == 1) { // 미로를 벗어나지 않음, 방문하지 않은 칸 중에 이동 가능한 칸인 경우
                    // 큐에 인접 노드 추가
                    queue.offer(new Node(xx,yy));
                    // 추가한 노드까지의 거리 = 현재 노드까지의 거리 + 1
                    graph[xx][yy] = graph[node.x][node.y] + 1;
                }
            }
        }
        System.out.println(graph[n][m]);
    }
    public static boolean check (int x, int y) {
        // 노드가 범위 밖인 경우 > x(행), y(열)
        if(x<1 || x>n || y<1 || y>m) {
            return false;
        }
        return true;
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}