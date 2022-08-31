package Practice8_MinimumSpanning;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon14950 {

    static int n; // 도시의 개수
    static int m; // 도로의 개수
    static int t; // 한번 정복할 때마다 증가하는 도로의 비용
    static ArrayList<Conquer> [] lists; // 인접 리스트
    static boolean [] visited; // 방문 여부 확인
    static int total = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        lists = new ArrayList[n+1];
        visited = new boolean[n+1];
        for(int i = 1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            lists[a].add(new Conquer(b, cost)); // 도로는 양방향
            lists[b].add(new Conquer(a, cost));
        }
        prim(1);
        System.out.println(total);
    }
    public static void prim (int x) {
        PriorityQueue<Conquer> queue = new PriorityQueue<>();
        queue.offer(new Conquer(x, 0));
        while(!queue.isEmpty()) {
            Conquer conquer = queue.poll();
            if(!visited[conquer.num]) {
                visited[conquer.num] = true;
                if(count <= 1) { // 도로의 비용이 늘어나지 않고 기존의 비용만 더해줌
                    total += conquer.cost;
                } else { // 정복이 된 도시가 하나 이상 존재한 이후부터 도로의 비용이 늘어남
                    total += conquer.cost + (count - 1) * t;
                }
                count++;
                for(Conquer next : lists[conquer.num]) {
                    queue.offer(new Conquer(next.num, next.cost));
                }
            }
        }
    }
}

class Conquer implements Comparable<Conquer>{
    int num;
    int cost;

    public Conquer(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Conquer c) {
        return this.cost-c.cost; // 비용 오름차순 정렬
    }
}