package Practice3_Dijkstra;

// 학생 N명 = 노드 개수
// M개의 단방향 도로들 = 간선 개수
// 소요 시간 = 가중치
// 왕복이므로 최단 거리 + 최단 거리
// 자기 마을에서 x까지 갔다가 다시 자기 마을로 돌아오는 거리의 합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon1238 {

    static int n;
    static int m;
    static int x; // x를 반드시 거쳐야 함
    static boolean[] visited; // 노드 방문 여부 체크
    static ArrayList<Distance>[] lists; // 정점 간의 연결 정보를 나타내는 리스트
    static int[] times;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        times = new int[n + 1];
        lists = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            lists[start].add(new Distance(end, time)); // 출발지, 도착지, 소요 시간
        }

        int [] arr = new int[n+1];

        for(int i = 1; i<=n; i++) { // 각각의 학생이 자기 마을에서 x까지 갔다가 다시 자기 마을로 돌아오는 거리를 배열에 넣어준다
            dijkstra(i);
            int a = times[x];
            dijkstra(x);
            int b = times[i];
            arr[i] = a+b;
        }

        Arrays.sort(arr);
        System.out.println(arr[arr.length-1]);
    }

    public static void dijkstra (int x) {
        visited = new boolean[n + 1]; // 방문 여부 체크
        Arrays.fill(times, Integer.MAX_VALUE); // 큰 값으로 초기화

        PriorityQueue<Distance> priorityQueue = new PriorityQueue();
        priorityQueue.offer(new Distance(x, 0)); // 시작 노드
        times[x] = 0;

        while(!priorityQueue.isEmpty()) {
            Distance distance = priorityQueue.poll();
            if(!visited[distance.num]) {
                visited[distance.num] = true;
                for(Distance next : lists[distance.num]) {
                    if(times[next.num] > distance.time + next.time) {
                        times[next.num] = distance.time + next.time;
                        priorityQueue.offer(new Distance(next.num, times[next.num]));
                    }
                }
            }
        }

    }
}
class Distance implements Comparable<Distance> {
    int num;
    int time;

    Distance(int num, int time) {
        this.num = num;
        this.time = time;
    }

    @Override
    public int compareTo(Distance d) {
        return this.time-d.time;
    }
}