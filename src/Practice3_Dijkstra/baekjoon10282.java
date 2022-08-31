package Practice3_Dijkstra;

// 다익스트라
// 컴퓨터의 개수 = 정점의 개수
// 의존성의 개수 = 간선의 개수
// 의존성으로 감염되는데 걸리는 시간 = 가중치
// 단방향 : 컴퓨터 a가 b를 의존한다 > b가 감염되면 a도 감염된다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon10282 {

    static int n; // 컴퓨터 개수
    static int depend;
    static int hacking; // 처음 해킹당한 컴퓨터 번호
    static ArrayList<Computer> [] lists; // 인접 리스트
    static boolean [] visited;
    static int [] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<test; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            depend = Integer.parseInt(st.nextToken());
            hacking  = Integer.parseInt(st.nextToken());
            lists = new ArrayList[n+1];
            visited = new boolean[n+1];
            result = new int[n+1];
            for(int j = 1; j<n+1; j++) {
                lists[j] = new ArrayList<>();
            }
            for(int j = 0; j<depend; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                lists[b].add(new Computer(a, value));
            }
            다익스트라(hacking);
            int count = 0;
            for(int j = 1; j< result.length; j++) {
                if(result[j] != Integer.MAX_VALUE) {
                    count++;
                }
            }
            int max = 0;
            for(int j = 1; j< result.length; j++) {
                if(max < result[j] && result[j] != Integer.MAX_VALUE) {
                    max = result[j];
                }
            }
            sb.append(count+" "+max+"\n");
        }
        System.out.println(sb);
    }
    public static void 다익스트라 (int x) {
        PriorityQueue<Computer> queue = new PriorityQueue<>();
        queue.offer(new Computer(x,0));
        Arrays.fill(result, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        result[x] = 0;
        while(!queue.isEmpty()) {
            Computer computer = queue.poll();
            if(!visited[computer.num]) {
                visited[computer.num] = true;
                for(Computer next : lists[computer.num]) {
                    if(result[next.num]> computer.depend+next.depend) {
                        result[next.num] = computer.depend+next.depend;
                        queue.offer(new Computer(next.num,result[next.num]));
                    }
                }
            }
        }
    }
}

class Computer implements Comparable<Computer>{
    int num;
    int depend;
    Computer (int num, int depend) {
        this.num = num;
        this.depend = depend;
    }

    @Override
    public int compareTo(Computer o) {
        return this.depend-o.depend;
    }
}