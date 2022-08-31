package Practice3_Dijkstra;
// 다익스트라
// 각 지역에서 모든 지역까지 갈 수 있는 최소 경로
// 최소 경로의 거리가 m(수색 범위) 이하인 경우 아이템을 얻을 수 있다

// 예은이가 2번에 착륙한 경우 최소 경로
// 2번 > 1번 = 1, 2번 > 2번 = 0, 2번 > 3번 = 3, 2번 > 4번 = 8, 2번 > 5번 = 4
// 수색 범위가 4일 때 1번, 2번, 3번, 5번 지역에 갈 수 있다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon14938 {

    static int n; // 지역의 개수
    static int m; // 수색 범위
    static int r; // 길의 개수
    static ArrayList<Game> [] lists;
    static int [] items; // 아이템 수 배열
    static boolean [] visited; // 방문 여부 체크
    static int [] result; // 최단 경로 배열


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        lists = new ArrayList[n+1];
        items = new int[n+1];
        visited = new boolean[n+1];
        result = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i<n+1; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i<r; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            lists[a].add(new Game(b,value));
            lists[b].add(new Game(a, value));
        }
        int max = 0;
        for(int i = 1; i<n+1; i++) {
            int sum = 0;
            다익스트라(i);
            for(int j = 1; j<n+1; j++) {
                if(result[j]<=m) {
                    sum += items[j];
                }
            }
            if(max<sum) {
                max = sum;
            }
        }
        System.out.println(max);
    }
    public static void 다익스트라 (int x) {
        PriorityQueue<Game> queue = new PriorityQueue<>();
        Arrays.fill(result, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        queue.offer(new Game(x, 0));
        result[x] = 0;
        while(!queue.isEmpty()) {
            Game game = queue.poll();
            if(!visited[game.num]) {
                visited[game.num] = true;
                for(Game next : lists[game.num]) {
                    if(result[next.num] > game.distance + next.distance) {
                        result[next.num] = game.distance + next.distance;
                        queue.offer(new Game(next.num, result[next.num]));
                    }
                }
            }
        }
    }
}

class Game implements Comparable<Game>{
    int num;
    int distance;

    Game (int num, int distance) {
        this.num = num;
        this.distance = distance;
    }

    @Override
    public int compareTo(Game o) {
        return this.distance-o.distance; // 거리 오름차순 정렬
    }
}