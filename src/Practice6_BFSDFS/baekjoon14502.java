package Practice6_BFSDFS;

// 바이러스는 상하좌우 빈 칸으로 퍼짐
// 0 = 빈 칸, 1 = 벽, 2 = 바이러스
// 새로 세울 수 있는 벽의 개수 = 3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon14502 {

    static int n;
    static int m;
    static int [][] graph; // 연구소 상태를 나타내는 이차원 배열
    static int [][] graphCopy;
    static int[] dx = {-1, 1, 0, 0}; // 행
    static int[] dy = {0, 0, -1, 1}; // 열
    static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n+1][m+1];
        for(int i = 1; i<n+1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j<m+1; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i][j] = num;
            }
        }
        makeWall(0);
        System.out.println(result);
    }

    // 연구소 모든 칸을 확인하면서 빈 칸이 있다면 벽을 세운다
    public static void makeWall (int count) {
        if(count == 3) { // 벽은 3개만 만들 수 있으므로
            bfs(); // 바이러스 확산 시작
            return;
        }
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<m+1; j++) {
                if(graph[i][j] == 0) {
                    graph[i][j] = 1;
                    makeWall(count+1);
                    graph[i][j] = 0; // 다시 원상복구
                }
            }
        }
    }

    public static void bfs () {
        graphCopy = new int[n+1][m+1]; // 기존 연구소 상태 배열을 바꾸면 안됨
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<m+1; j++) {
                graphCopy[i][j]  = graph[i][j]; // 연구소 상태를 복사한 이차원 배열
            }
        }
        Queue<Virus> queue = new LinkedList<>();
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<m+1; j++) {
                if(graph[i][j] == 2) {
                    queue.offer(new Virus(i, j)); // 바이러스가 있는 칸을 큐에 넣는다
                }
            }
        }
        while(!queue.isEmpty()) {
            Virus virus = queue.poll();
            for(int i = 0; i<dx.length; i++) { // 상하좌우 탐색
                int xx = virus.x + dx[i];
                int yy = virus.y + dy[i];
                if(check(xx, yy)) {
                    queue.offer(new Virus(xx, yy));
                    graphCopy[xx][yy] = 2;
                }
            }
        }
        virusCheck(graphCopy);
    }

    public static boolean check (int x, int y) {
        if (x < 1 || x > n || y < 1 || y > m) { // 연구소 내부를 벗어나지 않고
            return false;
        }
        if(graphCopy[x][y] != 0) { // 바이러스가 퍼지지 않은 칸
            return false;
        }
        return true;
    }

    // 바이러스가 퍼지지 않은 안전한 칸 확인 (칸의 값이 0인 경우)
    public static void virusCheck (int [][] graph) {
        int count = 0;
        for(int i = 1; i<n+1; i++) {
            for(int j = 1; j<m+1; j++) {
                if(graph[i][j] == 0) {
                    count++;
                }
            }
        }
        if(count > result) { // 최댓값 구하기
            result = count;
        }
    }
}

class Virus {
    int x;
    int y;
    Virus (int x, int y) {
        this.x = x;
        this.y = y;
    }
}