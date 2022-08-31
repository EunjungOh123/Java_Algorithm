package Practice3_Dijkstra;

// 도시의 개수 = 정점의 개수
// 버스의 개수 = 간선의 개수
// 경로는 도착지 -> 출발지 역추적하여 구한다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon11779 {

    static boolean [] visited; // 방문 여부 체크
    static ArrayList<Bus> [] lists; // 인접 리스트
    static int [] result; // 최소 경로를 담은 배열
    static int n;
    static int m;
    static int start; // 출발 도시
    static int end; // 도착 도시
    static int count; // 최소 비용을 갖는 경로에 포함되어 있는 도시의 개수
    static int [] track;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        result = new int[n+1];
        lists = new ArrayList[n+1];
        track = new int[n+1];
        for(int i =1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken()); // 시작점
            int b = Integer.parseInt(st.nextToken()); // 도착점
            int value = Integer.parseInt(st.nextToken()); // 비용
            lists[a].add(new Bus(b, value));
        }
        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        System.out.println(다익스트라(start, end));
        Stack<Integer> stack = search();
        System.out.println(count);
        while(!stack.isEmpty()) {
            System.out.print(stack.pop()+" ");
        }
    }
    public static int 다익스트라 (int start, int end) {
        PriorityQueue<Bus> queue = new PriorityQueue<>();
        Arrays.fill(result, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        queue.offer(new Bus(start, 0));
        while(!queue.isEmpty()) {
            Bus bus = queue.poll();
            if(!visited[bus.city]) {
                visited[bus.city] = true;
                for(Bus next : lists[bus.city]) {
                    if(result[next.city] > bus.money + next.money) {
                        result[next.city] = bus.money + next.money;
                        queue.offer(new Bus(next.city, result[next.city]));
                        track[next.city] = bus.city;
                    }
                }
            }
        }
        return result[end];
    }

    // 경로 역추적
    // 출발 도시가 1, 도착 도시가 5인 경우
    // track[1] = 0, track[2] = 1, track[3] = 1, track[4] = 1, track[5] = 4
    // 거꾸로 타고 올라감 : 5번 도시 > 4번 도시 > 1번 도시

    public static Stack<Integer> search() {
        Stack<Integer> stack = new Stack<>();
        int current = end;
        while(current != start) {
            stack.push(current);
            count++;
            current = track[current];
        }
        stack.push(current);
        count++;
        return stack;
    }
}

class Bus implements Comparable<Bus>{
    int city;
    int money;
    Bus (int city, int money) {
        this.city = city;
        this.money = money;
    }

    @Override
    public int compareTo(Bus o) {
        return this.money-o.money;
    }
}
