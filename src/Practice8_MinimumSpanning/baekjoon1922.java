package Practice8_MinimumSpanning;

// 모든 컴퓨터를 연결하는데 필요한 최소 비용
// n = 컴퓨터의 수 = 정점의 개수
// m = 연결할 수 있는 선의 수 = 간선의 개수
// 프림 알고리즘 : 시작 정점을 기준으로 그 정점과 인접한 정점을 잇는 가중치가 가장 작은 간선과 연결된 정점을 선택하며 신장 트리를 확장
// 크루스칼 알고리즘 : 모든 간선들의 가중치를 오름차순으로 정렬 후 가중치가 가장 작은 간선을 선택, 사이클이 발생할 경우 선택 X

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon1922 {

    static int n;
    static int m;
    static ArrayList<Computer> [] lists; // 인접리스트
    static boolean [] visited; // 방문 여부 체크
    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        lists = new ArrayList[n+1];
        for(int i = 1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            lists[a].add(new Computer(b,value)); // 양방향
            lists[b].add(new Computer(a,value));
        }
        prim(1); // 시작점이 어디여도 상관없다
        System.out.println(result);
    }

    public static void prim (int x) {
        PriorityQueue<Computer> queue = new PriorityQueue<>();
        queue.offer(new Computer(x, 0));
        while(!queue.isEmpty()) {
            Computer computer = queue.poll();
            if(!visited[computer.v1]) {
                visited[computer.v1] = true;
                result += computer.cost;

                for(Computer next : lists[computer.v1]) {
                    if(!visited[next.v1]) {
                        queue.offer(new Computer(next.v1, next.cost));
                    }
                }
            }
        }
    }
}

class Computer implements Comparable<Computer>{
    int v1;
    int cost;

    Computer (int v1, int cost) {
        this.v1 = v1;
        this.cost = cost;
    }

    @Override
    public int compareTo(Computer o) {
        return this.cost-o.cost;
    }
}