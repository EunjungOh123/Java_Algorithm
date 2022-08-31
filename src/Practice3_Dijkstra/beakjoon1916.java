package Practice3_Dijkstra;

// 도시의 개수 = 노드 개수 (n)
// 버스의 개수 = 간선 개수 (m)
// 비용 = 가중치

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class beakjoon1916 {
    static int n;
    static int m;
    static boolean[] visited; // 노드 방문 여부 체크
    static ArrayList<Edge>[] lists; // 정점 간의 연결 정보를 나타내는 리스트
    static int[] costs;
    static int start;
    static int end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        costs = new int[n + 1];
        lists = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            lists[start].add(new Edge(end, cost)); // 출발지, 도착지, 비용
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken()); // 출발점
        end = Integer.parseInt(st.nextToken()); // 최종 도착점
        Arrays.fill(costs, Integer.MAX_VALUE); // 큰 값으로 초기화
        dijkstra(start);
        System.out.println(costs[end]);
    }

    public static void dijkstra(int x) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Edge(x, 0)); // 시작 노드
        costs[x] = 0; // 출발점에 대한 최소 비용은 0
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll(); // 비용이 가장 적은 노드를 꺼낸다
            if (!visited[edge.num]) {
                visited[edge.num] = true; // 꺼낸 노드는 방문 처리

                for (Edge next : lists[edge.num]) {
                    // 방문하지 않았고, 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧을 경우
                    if (!visited[next.num] && costs[next.num] > edge.cost + next.cost) {
                        costs[next.num] = edge.cost + next.cost;
                        priorityQueue.offer(new Edge(next.num, costs[next.num]));
                    }
                }
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int num;
    int cost; // 비용

    Edge(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost; // 최소 비용 탐색을 위한 비용 기준으로 오름차순 정렬
    }
}
