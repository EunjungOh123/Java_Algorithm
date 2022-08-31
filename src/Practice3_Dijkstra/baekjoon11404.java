package Practice3_Dijkstra;

// 최소 비용 경로

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon11404 {

    static int n;
    static int m;
    static ArrayList<CityBus> [] lists;
    static int [] result;
    static boolean [] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        lists = new ArrayList[n+1];
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            lists[a].add(new CityBus(b, cost));
        }

        for(int i = 1; i<n+1; i++) {
            다익스트라(i);
            for(int j = 1; j<n+1; j++) {
               if(result[j] == Integer.MAX_VALUE) {
                   sb.append(0+" ");
               } else {
                   sb.append(result[j]+" ");
               }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void 다익스트라 (int x) {
        result = new int[n+1];
        visited = new boolean[n+1];
        Arrays.fill(result, Integer.MAX_VALUE);
        PriorityQueue<CityBus> queue = new PriorityQueue<>();
        queue.offer(new CityBus(x, 0));
        result[x] = 0;
        while(!queue.isEmpty()) {
            CityBus bus = queue.poll();
            if(!visited[bus.num]) {
                visited[bus.num] = true;
                for(CityBus next : lists[bus.num]) {
                    if(result[next.num] > result[bus.num] + next.cost) {
                        result[next.num] = result[bus.num] + next.cost;
                        queue.offer(new CityBus(next.num, result[next.num]));
                    }
                }
            }
        }
    }
}

class CityBus implements Comparable<CityBus>{
    int num;
    int cost;
    CityBus(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(CityBus o) {
        return this.cost-o.cost; // 비용 오름차순으로 정렬
    }
}