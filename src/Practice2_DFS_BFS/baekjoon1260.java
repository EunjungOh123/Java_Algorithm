package Practice2_DFS_BFS;

// dfs(깊이우선탐색), bfs(너비우선탐색)
// 정점 번호는 1부터 N까지

// 인접행렬: 행렬로 정점들 사이의 관계를 표현하는 것, 노드(정점) 사이에 간선으로 연결되어 있으면 1, 아니면 0
// 인접 리스트로도 구현 가능

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon1260 {
    static boolean [] visited; // 정점을 방문했는지 체크하는 배열
    static int [][] graph; // 정점 간의 연결 정보를 담는 이차원 배열 (인접 행렬)
    static int n; // 정점의 개수
    static int m; // 간선의 개수
    static StringBuilder sb1 = new StringBuilder();
    static StringBuilder sb2 = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];
        graph = new int[n+1][n+1];

        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start][end] = 1;
            graph[end][start] = 1; // 간선이 양방향이므로
        }
        dfs(v);
        System.out.println(sb1);
        visited = new boolean[n+1]; // 방문 여부 초기화
        bfs(v);
        System.out.println(sb2);
    }
    public static void dfs (int x) { // 재귀적으로 동작
        visited[x] = true;
        sb1.append(x+" ");

        for(int i = 1; i<n+1; i++) {
            if(graph[x][i] == 1 && visited[i] == false) {
                dfs(i);
            }
        }
    }
    public static void bfs (int x) { // 재귀적으로 동작하지 않음
        Queue <Integer> queue = new LinkedList<>();
        queue.offer(x);
        visited[x] = true;
        sb2.append(x+" ");
        while(!queue.isEmpty()) {
            int tmp = queue.poll();
            for(int i = 1; i<n+1; i++) {
                if(graph[tmp][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    sb2.append(i+" ");
                }
            }
        }
    }
}
