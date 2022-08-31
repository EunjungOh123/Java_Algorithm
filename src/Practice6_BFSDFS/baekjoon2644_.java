package Practice6_BFSDFS;

// dfs 사용한 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon2644_ {

    static int n;
    static int m;
    static ArrayList<Integer>[] lists;
    static boolean [] visited;
    static int start;
    static int end;
    static int ans = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lists = new ArrayList[n+1];
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
            lists[a].add(b);
            lists[b].add(a);
        }
        dfs(start, 0);
        System.out.println(ans);
    }
    public static void dfs (int start, int count){
        visited[start] = true;
        if(start == end) {
            ans = count;
            return;
        }
        for(int n : lists[start]) {
            if(!visited[n]) {
                dfs(n, count+1);
            }
        }
    }
}
