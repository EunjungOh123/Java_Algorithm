package Practice3_Dijkstra;

// 인접 행렬과 최단 경로

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class baekjoon4485 {

    static int n; // 동굴의 크기
    static int[][] graph; // 동굴 내 도둑 루피 정보를 담은 이차원 배열
    static boolean[][] visited; // 방문 여부 확인
    static int[] dx = {-1, 1, 0, 0}; // 좌우 이동
    static int[] dy = {0, 0, -1, 1}; // 상하 이동
    static int[][] result; // 최단 경로를 담을 이차원 배열
    static int i = 0;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            graph = new int[n][n];
            visited = new boolean[n][n];
            result = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] str = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(str[j]);
                }
            }
            sb.append("Problem "+(++i)+": "+다익스트라(0, 0)+"\n");
        }
        System.out.println(sb);
    }

    public static int 다익스트라(int x, int y) {
        PriorityQueue<Treasure> queue = new PriorityQueue<>();
        for(int i = 0; i<n; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE); // 이차원 배열의 초기값 설정
        }
        queue.offer(new Treasure(x, y, graph[x][y]));
        result[x][y] = graph[x][y];
        while (!queue.isEmpty()) {
            Treasure tr = queue.poll();
            if (!visited[tr.x][tr.y]) {
                visited[tr.x][tr.y] = true;
                for (int i = 0; i < dx.length; i++) {
                    int xx = tr.x + dx[i];
                    int yy = tr.y + dy[i];
                    if (check(xx, yy) && result[xx][yy] > result[tr.x][tr.y] + graph[xx][yy]) {
                        result[xx][yy] = result[tr.x][tr.y] + graph[xx][yy];
                        queue.offer(new Treasure(xx, yy, result[xx][yy]));
                    }
                }
            }
        }
        return result[n-1][n-1];
    }

    public static boolean check(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= n || visited[x][y]) { // 동굴을 벗어나거나 방문한 경우 false 반환
            return false;
        }
        return true;
    }
}

class Treasure implements Comparable<Treasure> {
    int x;
    int y;
    int rupee;

    Treasure(int x, int y, int rupee) {
        this.x = x;
        this.y = y;
        this.rupee = rupee;
    }

    @Override
    public int compareTo(Treasure t) {
        return this.rupee - t.rupee; // 도둑 루피 오름차순 정렬
    }
}