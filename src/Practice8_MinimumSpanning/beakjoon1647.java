package Practice8_MinimumSpanning;

// 집의 개수 = 정점의 개수 = n
// 길의 개수 = 간선의 개수 = m
// 분리된 두 마을 사이에 있는 길은 필요가 없으므로 없앤다
// 나머지 길의 유지비 합을 최소로 하면서 마을을 두 개로 분리
// 최소 신장 트리에서 간선의 길이가 최대인 간선 하나만 없애면 된다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class beakjoon1647 {

    static int n;
    static int m;
    static ArrayList<Town> [] lists;
    static boolean [] visited;
    static int result;
    static int max = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];
        lists = new ArrayList[n+1];
        for(int i =1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            lists[a].add(new Town(b, cost));
            lists[b].add(new Town(a, cost));
        }
        prim(1);
        System.out.println(result-max);
    }
    public static void prim (int x) {
        PriorityQueue<Town> queue = new PriorityQueue<>();
        queue.offer(new Town(x, 0));
        while(!queue.isEmpty()) {
            Town town = queue.poll();
            if(!visited[town.num]) {
                visited[town.num] = true;
                result += town.cost;
                if(town.cost > max) {
                    max = town.cost;
                }
                for(Town next : lists[town.num]) {
                    queue.offer(new Town(next.num, next.cost));
                }
            }
        }
    }
}

class Town implements Comparable<Town>{
    int num;
    int cost;
    Town(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Town o) {
        return this.cost - o.cost; // 비용 오름차순 정렬
    }
}