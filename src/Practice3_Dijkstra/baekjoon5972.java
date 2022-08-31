package Practice3_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon5972 {

    static int n;
    static int m;
    static boolean [] visited;
    static int [] result;
    static ArrayList<Farm> [] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];
        result = new int[n+1];
        lists = new ArrayList[n+1];
        for(int i = 1; i<=n; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            lists[a].add(new Farm(b, cost));
            lists[b].add(new Farm(a, cost));
        }
        Arrays.fill(result, Integer.MAX_VALUE);
        System.out.println(다익스트라(1));

    }
    public static int 다익스트라 (int x) {
        PriorityQueue<Farm> queue = new PriorityQueue<>();
        queue.offer(new Farm(x, 0));
        result[x] = 0; // 자기 자신
        while(!queue.isEmpty()) {
            Farm farm = queue.poll();
            if(!visited[farm.num]) {
                visited[farm.num] = true;
                for(Farm next : lists[farm.num]) {
                    if(result[next.num] > result[farm.num] + next.cost) {
                        result[next.num] = result[farm.num] + next.cost;
                        queue.offer(new Farm(next.num, result[next.num]));
                    }
                }
            }
        }
        return result[n];
    }
}

class Farm implements Comparable<Farm>{
    int num;
    int cost;

    public Farm(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Farm f) {
        return this.cost-f.cost;
    }
}