package Practice2_DFS_BFS;

// 컴퓨터의 수 = 노드 개수
// 연결되어 있는 컴퓨터 쌍의 수 = 간선의 개수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2606 {
    static boolean [] visited; // 컴퓨터에 방문했는지 체크하는 배열
    static int [][] graph; // 인접 행렬
    static int computer; // 컴퓨터(노드)의 개수
    static int line; // 네트워크 상에 연결되어 있는 컴퓨터 쌍의 수 (간선이 연결하는 두 노드)
    static int count = 0; // 바이러스에 감염될 컴퓨터의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        computer = Integer.parseInt(br.readLine());
        line = Integer.parseInt(br.readLine());
        visited = new boolean[computer+1];
        graph = new int[computer+1][computer+1];
        for(int i = 0; i<line; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start][end]= 1;
            graph[end][start]= 1;
        }
        dfs(1); // 1번 컴퓨터부터
        System.out.println(count);
    }
    public static void dfs (int x) {
        visited[x] = true;
        for(int i = 1; i<computer+1; i++) {
            if(graph[x][i] == 1 && !visited[i]) {
                count++; // 방문하지 않은 곳을 탐색할 때마다 개수를 하나씩 늘려준다
                dfs(i);
            }
        }
    }
}
