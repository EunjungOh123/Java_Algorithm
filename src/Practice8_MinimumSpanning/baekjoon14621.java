package Practice8_MinimumSpanning;

// 모든 대학교 간 이동이 가능해야 함
// 최단 경로
// 크루스칼 알고리즘 사용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon14621 {

    static int n; // 학교 수 (1번 대학교, 2번 대학교...)
    static int m; // 연결하는 도로 수
    static Map<Integer, String> map = new HashMap<>();
    static ArrayList<Application> [] lists; // 인접리스트
    static boolean [] visited; // 방문 여부 체크
    static int result;
    static int count = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lists = new ArrayList[n+1];
        visited = new boolean[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++) {
            map.put(i+1, st.nextToken()); // 대학교 번호와 대학교가 남초인지 여초인지 담아줌
        }
        for(int i = 1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            lists[a].add(new Application(b, map.get(b), distance)); // 양방향 이동 가능
            lists[b].add(new Application(a, map.get(a), distance));
        }
        prim(1);
        if(count == n) { // 모든 대학교가 연결되어 있다면
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }
    public static void prim (int x) {
        PriorityQueue<Application> queue = new PriorityQueue<>();
        queue.offer(new Application(x, map.get(x),0));
        while(!queue.isEmpty()) {
            Application application = queue.poll();
            if(!visited[application.num]) {
                visited[application.num] = true;
                result += application.distance;
                count++; // 방문 처리된 대학교가 몇 개인지
                for(Application next : lists[application.num]) {
                    if(!application.gender.equals(map.get(next.num))) { // 성별이 다른 경우에만 연결될 수 있음
                        queue.offer(new Application(next.num, map.get(next.num), next.distance));
                    }
                }
            }
        }
    }
}

class Application implements Comparable<Application>{

    int num;
    String gender;
    int distance;

    public Application(int num, String gender, int distance) {
        this.num = num;
        this.gender = gender;
        this.distance = distance;
    }

    @Override
    public int compareTo(Application a) {
        return this.distance-a.distance; // 경로 오름차순 정렬
    }
}