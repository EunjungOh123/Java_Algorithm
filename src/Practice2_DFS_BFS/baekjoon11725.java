package Practice2_DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 인접 행렬과 dfs 이용한 풀이
// 노드의 개수 N의 범위가 2 ≤ N ≤ 100,000이므로 인접 행렬로 풀 경우 메모리 초과가 발생한다


public class baekjoon11725 {

    static boolean [] visited; // 정점을 방문했는지 체크하는 배열
    static int [][] graph; // 정점 간의 연결 관계를 담는 이차원 배열 (인접 행렬)
    static int n; // 정점의 개수
    static int [] parents; // 부모 노드들을 담는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        graph = new int[n+1][n+1];
        parents = new int[n+1];
        for(int i = 0; i<n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start][end] = 1;
            graph[end][start] = 1;
        }
        dfs(1);
        StringBuilder sb= new StringBuilder();
        for(int i =2; i<n+1; i++) { //  2번 노드부터 순서대로 출력
            sb.append(parents[i]+"\n");
        }
        System.out.println(sb);
    }
    public static void dfs (int x) { // 재귀적으로 동작
        visited[x] = true;

        for(int i = 1; i<n+1; i++) {
            if(graph[x][i] == 1 && visited[i] == false) {
                parents[i] = x; // 위의 조건을 충족하는 노드가 있다는 것은 x가 부모 노드가 될 수 있다는 의미
                dfs(i);
            }
        }
    }
}
