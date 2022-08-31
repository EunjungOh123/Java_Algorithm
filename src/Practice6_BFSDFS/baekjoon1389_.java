package Practice6_BFSDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접리스트 사용한 풀이
// 두 사람이 최소 몇 단계만에 이어질 수 있는지
// 1번 유저의 케빈 베이컨의 수
// (1번 유저 -> 2번 유저) + (1번 유저 -> 3번 유저) + (1번 유저 -> 4번 유저) + (1번 유저 -> 5번 유저)

public class baekjoon1389_ {

    static boolean [] visited;
    static ArrayList<Kevin> [] lists;
    static int n;
    static int m;
    static int result;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lists = new ArrayList[n+1];
        for(int i = 1; i<=n; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 1; i<=m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lists[a].add(new Kevin(b, 0));
            lists[b].add(new Kevin(a, 0));
        }
        for(int i = 1; i<=n; i++) {
            visited = new boolean[n+1];
            bfs(i);
        }
        System.out.println(result);
    }
    public static void bfs (int x) {
        Queue <Kevin> queue = new LinkedList<>();
        visited[x] = true;
        int count= 0; // 각 유저들을 돌면서 얻은 결과 값 (1번 유저의 케빈 베이컨의 수, 2번 유저의 케빈 베이컨의 수...)
        queue.offer(new Kevin(x, 0));
        while(!queue.isEmpty()) {
            Kevin kevin = queue.poll();
            count += kevin.value;
            for(Kevin k : lists[kevin.num]) {
                if(!visited[k.num]) {
                    visited[k.num] = true;
                    queue.offer(new Kevin(k.num, kevin.value+1));
                }
            }
        }
        if(min > count) { // 최솟값을 구하기 위해
            min = count;
            result = x;
        }
    }
}

class Kevin {
    int num;
    int value;

    Kevin (int num, int value) {
        this.num = num;
        this.value = value;
    }
}