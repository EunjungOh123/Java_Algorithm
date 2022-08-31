package Practice8_MinimumSpanning;

// 모든 두 집 쌍에 대해 불이 켜진 길만으로 서로를 왕래 (양방향)
// 집의 개수 = 정점의 개수 = m
// 길의 개수 = 간선의 개수 = n
// 길의 불을 꺼서 절약할 수 있는 최대 액수 = 불이 켜진 길들 최소 거리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon6497 {

    static int m;
    static int n;
    static ArrayList<City>[] lists;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) {
                break;
            }
            visited = new boolean[m];
            lists = new ArrayList[m];
            for (int i = 0; i < m; i++) {
                lists[i] = new ArrayList<>();
            }
            int sum = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                lists[a].add(new City(b, cost));
                lists[b].add(new City(a, cost));
                sum += cost;
            }
            System.out.println(sum -  prim(0));
        }
    }

    public static int prim(int x) {
        int total = 0;
        PriorityQueue<City> queue = new PriorityQueue<>();
        queue.offer(new City(x, 0));
        while (!queue.isEmpty()) {
            City city = queue.poll();
            if (!visited[city.num]) {
                visited[city.num] = true;
                total += city.distance;
                for (City next : lists[city.num]) {
                    queue.offer(new City(next.num, next.distance));
                }
            }
        }
        return total;
    }
}

class City implements Comparable<City> {
    int num;
    int distance;

    City(int num, int distance) {
        this.num = num;
        this.distance = distance;
    }

    @Override
    public int compareTo(City o) {
        return this.distance - o.distance; // 거리 오름차순 정렬
    }
}