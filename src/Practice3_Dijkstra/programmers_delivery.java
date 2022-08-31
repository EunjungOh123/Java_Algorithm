package Practice3_Dijkstra;

// 1번 마을에서 음식 배달 > K시간 이하로 배달이 가능한 곳만 주문 가능
// 1번 마을에서 모든 마을까지의 최단 경로를 구한 후 K시간 이하인 곳만 count

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class programmers_delivery {

    static int [] result;
    static boolean [] visited;
    static ArrayList<Delivery> [] lists;

    public static void main(String[] args) {
        int n = 5;
        int [][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int k = 3;
        System.out.println(solution(n, road, k));
    }
    public static int solution(int N, int[][] road, int K) {
        int answer = 0;
        lists = new ArrayList[N+1];
        result = new int[N+1];
        visited = new boolean[N+1];
        Arrays.fill(result, Integer.MAX_VALUE);
        for(int i = 1; i<=N; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i< road.length; i++) {
            for(int j = 0; j<3; j++) {
               int a = road[i][0];
               int b = road[i][1];
               int time = road[i][2];
               lists[a].add(new Delivery(b, time));
               lists[b].add(new Delivery(a, time));
            }
        }
        delivery(1);
        for(int i = 1; i<N+1; i++) {
            if(result[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
    public static void delivery (int x) {
        PriorityQueue<Delivery> queue = new PriorityQueue<>();
        queue.offer(new Delivery(x, 0));
        result[x] = 0;
        while(!queue.isEmpty()) {
            Delivery delivery = queue.poll();
            if(!visited[delivery.num]) {
                visited[delivery.num] = true;
                for(Delivery next : lists[delivery.num]) {
                    if(result[next.num] > result[delivery.num] + next.time) {
                        result[next.num] = result[delivery.num] + next.time;
                        queue.offer(new Delivery(next.num, result[next.num]));
                    }
                }
            }
        }
    }
}

class Delivery implements Comparable<Delivery>{
    int num;
    int time;

    public Delivery(int num, int time) {
        this.num = num;
        this.time = time;
    }

    @Override
    public int compareTo(Delivery d) {
        return this.time-d.time;
    }
}