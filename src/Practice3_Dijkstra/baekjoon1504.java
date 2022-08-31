package Practice3_Dijkstra;

// 정점의 개수 = n
// 간선의 개수 = e
// 1번 정점에서 n번 정점으로 최단 거리로 이동 (시작점 1)
// 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동 가능
// 주어진 두 정점(v1, v2)을 반드시 거치면서 최단 경로로 이동
// (1번 > v1) + (v1 > v2) + (v2 > n번)
// (1번 > v2) + (v2 > v1) + (v1 > n번)
// 두 가지 경우를 모두 고려한 후 그 중 작은 값이 정답
// 경로가 없을 때에는 -1을 출력
// 거리 최댓값 = 1000, 간선 개수 최댓값 = 200000 > 경로의 최대 길이 = 200000000

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 정수의 오버플로우에 조심하자!

public class baekjoon1504 {

    static int n;
    static int e;
    static ArrayList <Route> [] lists;
    static int [] result;
    static boolean[] visited;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        lists = new ArrayList[n+1];

        for(int i = 1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }

        for(int i = 0; i<e; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            lists[a].add(new Route(b, value)); // 양방향
            lists[b].add(new Route(a, value));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long answer1 = 0;  // 1->v1->v2->n
        answer1 += 다익스트라(1, v1);
        answer1 += 다익스트라(v1, v2);
        answer1 += 다익스트라(v2, n);
        long answer2 = 0; // 1->v2->v1->n
        answer2 += 다익스트라(1, v2);
        answer2 += 다익스트라(v2, v1);
        answer2 += 다익스트라(v1, n);
        if (Math.min(answer1, answer2) >= INF) {
            System.out.println(-1);
            return;
        }
        System.out.println(Math.min(answer1, answer2));
    }

    public static int 다익스트라 (int x, int y) {
        result = new int[n+1];
        visited = new boolean[n+1];
        Arrays.fill(result, INF);
        PriorityQueue <Route> queue = new PriorityQueue<>();
        queue.offer(new Route(x, 0));
        result[x] = 0;
        while (!queue.isEmpty()) {
            Route route = queue.poll();
            if(!visited[route.num]) {
                visited[route.num] = true;
                for(Route next : lists[route.num]) {
                    if(result[next.num] > result[route.num] + next.distance) {
                        result[next.num] = result[route.num] + next.distance;
                        queue.offer(new Route(next.num, result[next.num]));
                    }
                }
            }
        }
        return result[y];
    }
}

class Route implements Comparable <Route>{
    int num;
    int distance;
    Route (int num, int distance) {
        this.num = num;
        this.distance = distance;
    }

    @Override
    public int compareTo(Route o) {
        return this.distance-o.distance;
    }
}