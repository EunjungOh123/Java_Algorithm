package Practice12_Greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 모든 섬이 통행 가능하도록 다리를 건설하는데 필요한 최소 비용
// 양방향
// 그리디 알고리즘으로 분류된 문제지만 최소 신장 트리를 이용하는 문제이다

public class programmers_island {

    static List<Island> [] lists;
    static boolean [] visited;
    static int result;

    public static void main(String[] args) {
        int [][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        int n = 4;
        System.out.println(solution(n, costs));
    }
    public static int solution(int n, int[][] costs) {
        int answer = 0;
        lists = new List[n];
        visited = new boolean[n];
        for(int i = 0; i<n; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i<costs.length; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int value = costs[i][2];
            lists[a].add(new Island(b, value));
            lists[b].add(new Island(a, value));
        }
        prim(0);
        answer = result;
        return answer;
    }
    public static void prim (int x) {
        PriorityQueue<Island> queue = new PriorityQueue<>();
        queue.offer(new Island(x, 0));
        while(!queue.isEmpty()) {
            Island island = queue.poll();
            if(!visited[island.num]) {
                visited[island.num] = true;
                result += island.cost;

                for(Island next : lists[island.num]) {
                    if(!visited[next.num]) {
                        queue.offer(new Island(next.num, next.cost));
                    }
                }
            }
        }
    }
}

class Island implements Comparable<Island>{
    int num;
    int cost;

    public Island(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Island o) {
        return this.cost-o.cost; // 비용 오름차순 정렬
    }
}