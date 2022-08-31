package Practice2_DFS_BFS;

// 인접 리스트와 dfs/bfs 이용한 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon11725_ {

    static boolean [] visited; // 정점을 방문했는지 체크하는 배열
    static ArrayList<Integer> [] lists; // 정점 간의 연결 정보를 나타내는 리스트
    static int n; // 정점의 개수
    static int [] parents; // 부모 노드들을 담는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        lists = new ArrayList[n+1];
        parents = new int[n+1];

        // 1번 노드부터 리스트를 만들어서 ArrayList 배열에 담아주기 (1번 노드 리스트, 2번 노드 리스트, .... , n번 노드 리스트)
        for(int i = 1; i<=n; i++) {
            lists[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 양방향
            lists[start].add(end);
            lists[end].add(start);
        }
        StringBuilder sb = new StringBuilder();

//        DFS(1);
        BFS(1);
        for(int i = 2; i<n+1; i++) {
            sb.append(parents[i]+"\n");
        }
        System.out.println(sb);
    }

//    public static void DFS (int x) {
//        visited[x] = true;
//        for(int vertex : lists[x]) {
//            if(!visited[vertex]) {
//                parents[vertex] = x;
//                DFS(vertex);
//            }
//        }
//    }

    public static void BFS (int x) {
        Queue<Integer>queue = new LinkedList<>();
        visited[x] = true;
        queue.offer(x);
        while(!queue.isEmpty()) {
            int tmp = queue.poll();
            for(int next : lists[tmp]) {
                if(!visited[next]) {
                    visited[next] = true;
                    parents[next] = tmp;
                    queue.offer(next);
                }
            }
        }
    }
}
