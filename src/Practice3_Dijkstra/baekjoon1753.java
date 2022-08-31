package Practice3_Dijkstra;

// 최단 경로 찾기
// 정점의 개수 = v, 간선의 개수 = e
// 시작점 = k, 가중치 w

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon1753 {

    static int v; // 정점의 개수
    static int e; // 간선의 개수
    static int start; // 시작점

    static ArrayList<Node> lists []; // 정점 간의 연결 정보를 나타내는 리스트
    static boolean[] visited; // 노드 방문 여부 체크
    static int [] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        visited = new boolean[v+1];
        result = new int[v+1];
        lists = new ArrayList[v+1];
        for(int i = 1; i<v+1; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i<e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            lists[start].add(new Node(end, value));
        }
        Arrays.fill(result, Integer.MAX_VALUE);
        다익스트라(start);
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<v+1; i++) {
            if(result[i] == Integer.MAX_VALUE) {
                sb.append("INF"+"\n");
            } else {
                sb.append(result[i]+"\n");
            }
        }
        System.out.println(sb);
    }

    public static void 다익스트라 (int x) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        result[x] = 0;
        queue.offer(new Node(x, 0)); // 시작 노드
        while(!queue.isEmpty()) {
            Node node = queue.poll(); // 가중치가 제일 작은 노드를 꺼낸다
            if(!visited[node.num]) {
                visited[node.num] = true; // 꺼낸 노드는 방문 처리
                for(Node next : lists[node.num]) {
                    if(result[next.num] > node.value + next.value) {
                        result[next.num] = node.value + next.value;
                        queue.offer(new Node(next.num, result[next.num]));
                    }
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int num;
    int value;

    Node(int num, int value) {
        this.num = num;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value; // 가중치 오름차순 정렬
    }
}