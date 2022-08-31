package Practice6_BFSDFS;

// 수빈이와 동생 좌표는 0부터 100,000까지
// 1초 후에 X-1 또는 X+1, 순간이동을 하는 경우 0초 후에 2*X의 위치 이동
// 수빈이 현재 위치 = n
// 동생 현재 위치 = k


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class baekjoon13549 {

    static int n;
    static int k;
    static boolean [] visited = new boolean [100001]; // 방문 여부 체크
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if(n == k) {
            System.out.println(0);
        } else {
            bfs(n);
            System.out.println(result);
        }
    }

    public static void bfs (int x) {
        PriorityQueue<Hidden> queue = new PriorityQueue<>();
        queue.offer(new Hidden(x,0));
        while(!queue.isEmpty()) {
            Hidden hidden = queue.poll();
            visited[hidden.num] = true;
            if(hidden.num == k) {
                result = hidden.time;
                break;
            }
            if(hidden.num * 2<= 100000 && !visited[hidden.num*2]) { // 순간 이동
                queue.offer(new Hidden(hidden.num*2, hidden.time));
            }
            if(hidden.num + 1<=100000 && !visited[hidden.num+1]) { // 앞으로 한 칸
                queue.offer(new Hidden(hidden.num+1, hidden.time+1));
            }
            if(0 <= hidden.num-1 && hidden.num <= 100000 && !visited[hidden.num-1]) { // 뒤로 한 칸
                queue.offer(new Hidden(hidden.num-1, hidden.time+1));
            }
        }
    }
}
class Hidden implements Comparable<Hidden>{
    int num;
    int time;
    Hidden (int num, int time) {
        this.num = num;
        this.time = time;
    }

    @Override
    public int compareTo(Hidden o) {
        return this.time-o.time;
    }
}