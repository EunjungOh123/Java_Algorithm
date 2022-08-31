package Practice8_MinimumSpanning;

// 절약될 수 있는 금액 = 기존에 드는 비용 - 최소 비용
// 양방향 도로
// 정수의 오버플로우에 주의할 것 (비용 계산에 있어 int 범위를 벗어나는 경우 존재)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon21924 {

    static int n; // 건물의 개수
    static ArrayList<Road> [] lists; // 인접 리스트
    static int m; // 도로의 개수
    static boolean [] visited; // 방문 여부 확인
    static long minCost;
    static long total;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
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
            int cost = Integer.parseInt(st.nextToken());
            lists[a].add(new Road(b, cost));
            lists[b].add(new Road(a, cost));
            total += cost;
        }
        prim(1);
        if(count == n) {
            System.out.println(total-minCost);
        } else { // 모든 건물이 연결되어 있지 않는 경우
            System.out.println(-1);
        }
    }
    public static void prim (int x) {
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(x, 0));
        while(!queue.isEmpty()) {
            Road road = queue.poll();
            if(!visited[road.building]) {
                count++; // 건물 방문 횟수 세기
                visited[road.building] = true;
                minCost += road.cost;
                for(Road next : lists[road.building]) {
                    if(!visited[next.building]) {
                        queue.offer(new Road(next.building, next.cost));
                    }
                }
            }
        }
    }
}

class Road implements Comparable<Road>{
    int building;
    int cost;

    public Road(int building, int cost) {
        this.building = building;
        this.cost = cost;
    }

    @Override
    public int compareTo(Road r) {
        return this.cost-r.cost;
    }
}