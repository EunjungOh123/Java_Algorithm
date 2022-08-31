package Practice6_BFSDFS;

// 정점의 개수 = n
// 간선의 개수 = m
// 무방향 그래프

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon11724 {
    static int n;
    static int m;
    static ArrayList<Integer> [] lists; // 인접 리스트
    static boolean [] visited; // 방문 여부 체크

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lists = new ArrayList[n+1];
        visited = new boolean[n+1];
        for(int i = 1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lists[a].add(b);
            lists[b].add(a);
        }
        int count = 0;
        for(int i = 1; i<n+1; i++) {
           if(!visited[i]) { // 방문하지 않은 곳만 bfs 탐색
               bfs(i); // bfs 호출 시 연결되어 있는 정점들은 모두 방문 처리된다 > 연결되어 있지 않는 경우만 다시 bfs 탐색
               count++;
           }
        }
        System.out.println(count);
    }
    static void bfs (int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        visited[x] = true;
        while(!queue.isEmpty()) {
            int tmp = queue.poll();
            for(int next : lists[tmp]) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}
