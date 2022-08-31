package Practice8_MinimumSpanning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon1197 {
    static int v;
    static int e;
    static ArrayList<Spanning> [] lists;
    static boolean [] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        lists = new ArrayList[v+1];
        visited = new boolean[v+1];
        for(int i = 1; i<v+1; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i<e; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            lists[a].add(new Spanning(b, value));
            lists[b].add(new Spanning(a, value));
        }
        prim(1);
        System.out.println(result);
    }

    public static void prim (int x) {
        PriorityQueue<Spanning> queue = new PriorityQueue<>();
        queue.offer(new Spanning(x, 0));
        while(!queue.isEmpty()) {
            Spanning spanning = queue.poll();
            if(!visited[spanning.num]) {
                visited[spanning.num] = true;
                result += spanning.value;
                for(Spanning next : lists[spanning.num]) {
                    if(!visited[next.num]) {
                        queue.offer(new Spanning(next.num, next.value));
                    }
                }
            }
        }
    }
}

class Spanning implements Comparable<Spanning>{
    int num;
    int value;
    Spanning(int num, int value) {
        this.num = num;
        this.value = value;
    }

    @Override
    public int compareTo(Spanning o) {
        return this.value-o.value;
    }
}