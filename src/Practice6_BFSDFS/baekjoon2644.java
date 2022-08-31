package Practice6_BFSDFS;

// DFS/BFS 문제이지만 다익스트라로 풀었다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon2644 {

    static int n;
    static int m;
    static ArrayList<Family> [] lists;
    static int [] result;
    static boolean [] visited;
    static int start;
    static int end;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lists = new ArrayList[n+1];
        result = new int[n+1];
        visited = new boolean[n+1];
        for(int i = 1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lists[a].add(new Family(b, 1));
            lists[b].add(new Family(a, 1));
        }
        int ans = degreeOfKinship(start);
        if(ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
    public static int degreeOfKinship (int x) {
        Arrays.fill(result, Integer.MAX_VALUE);
        PriorityQueue<Family> queue = new PriorityQueue<>();
        queue.offer(new Family(x, 0));
        result[x] = 0;
        while(!queue.isEmpty()) {
            Family f = queue.poll();
            if(!visited[f.num]) {
                visited[f.num] = true;
                for(Family next : lists[f.num]) {
                    if(result[next.num] > result[f.num]+next.v) {
                        result[next.num] = result[f.num]+next.v;
                        queue.offer(new Family(next.num, result[next.num]));
                    }
                }
            }
        }
        return result[end];
    }


}

class Family implements Comparable<Family>{
    int num;
    int v;
    Family(int num, int v) {
        this.num = num;
        this.v = v;
    }

    @Override
    public int compareTo(Family o) {
        return this.v-o.v;
    }
}