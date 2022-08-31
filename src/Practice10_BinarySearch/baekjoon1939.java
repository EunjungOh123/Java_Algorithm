package Practice10_BinarySearch;

// 이진 탐색과 bfs 활용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon1939 {

    static int n; // 섬의 개수 (정점)
    static int m; // 다리의 개수 (간선)
    static boolean[] visited; // 방문 여부 체크
    static ArrayList<Route>[] lists; // 인접리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lists = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            lists[i] = new ArrayList<>();
        }
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            lists[a].add(new Route(b, value));
            lists[b].add(new Route(a, value));
            max = Math.max(value, max);
            min = Math.min(value, min);
        }
        int left = min; // 중량의 최솟값
        int right = max; // 중량의 최댓값
        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        while(left<=right) {
            int mid = (left+right) / 2;
            visited = new boolean[n+1];
            if(BFS(start, end, mid)) {
                left = mid + 1; // 무게를 더 늘릴 수 있음
            } else {
                right = mid - 1; // 무게를 더 줄어야 함
            }
        }
        System.out.println(right);
    }

    public static boolean BFS(int start, int end, int mid) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            if(tmp == end) {
                return true;
            }
            for(Route next : lists[tmp]) {
                if(!visited[next.num] && mid <= next.weight) {
                    visited[next.num] = true;
                    queue.offer(next.num);
                }
            }
//            for(int i = 0; i<lists[tmp].size(); i++) {
//                if(lists[tmp].get(i).weight >= mid && !visited[lists[tmp].get(i).num]) {
//                    visited[lists[tmp].get(i).num] = true;
//                    queue.offer(lists[tmp].get(i).num);
//                }
//            }
        }
        return false;
    }
}

class Route {
    int num;
    int weight;

    Route(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }
}