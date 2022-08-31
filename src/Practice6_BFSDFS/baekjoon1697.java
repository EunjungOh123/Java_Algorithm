package Practice6_BFSDFS;

// 수빈이와 동생 좌표는 0부터 100,000까지
// 1초 후에 X-1 또는 X+1, 순간이동을 하는 경우 1초 후에 2*X의 위치 이동
// 수빈이 현재 위치 = n
// 동생 현재 위치 = k

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1697 {

    static int n;
    static int k;
    static boolean [] visited = new boolean [100001]; // 방문 여부 체크
    static int [] numbers = new int[100001];
    static int [] move = {-1,1,2}; // 수빈이 이동 좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if(n == k) {
            System.out.println(0);
        } else {
            bfs(n);
        }
    }
    public static void bfs (int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        visited[x] = true;
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            int xx;
            for(int i = 0; i< move.length; i++) {
                if(i == 2) {
                    xx = tmp*move[i]; // 순간이동하는 경우
                } else {
                    xx  = tmp+move[i]; // 현재 위치에서 +1 or -1
                }
                if(check(xx)) {
                    queue.offer(xx);
                    visited[xx] = true;
                    numbers [xx] = numbers[tmp]+1;
                    if(xx == k) {
                        System.out.println(numbers[xx]); // 동생 위치에 도달하면 출력
                        return;
                    }
                }
            }
        }
    }
    public static boolean check (int x) {
        if(x<0 || x>100000 || visited[x]) { // 이동 범위를 벗어나거나 x를 방문한 경우 false
            return false;
        }
        return true;
    }
}