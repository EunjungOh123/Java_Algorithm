package Practice2_DFS_BFS;

// 노드 간 거리는 1

import java.util.*;

public class programmers_far_node {

    static int[] result;
    static ArrayList<Integer> [] lists;
    static boolean[] visited;
    static int max = 0;

    public static void main(String[] args)  {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n, edge));
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        result = new int[n + 1];
        lists = new ArrayList[n+1];
        visited = new boolean[n + 1];
        for(int i = 1; i<n+1; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            lists[a].add(b);
            lists[b].add(a);
        }

        BFS(1);
        int count = 0;
        for(int i = 1; i< result.length; i++) {
            if(result[i] == max) count++;
        }
        answer = count;
        return answer;
    }

    public static void BFS(int x) {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(result, Integer.MAX_VALUE);
        queue.offer(x);
        result[x] = 0; // 자기 자신까지의 거리는 0
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            if (!visited[tmp]) {
                visited[tmp] = true;
                for(int next : lists[tmp]) {
                    if(!visited[next] && result[next] > result[tmp] + 1) {
                        result[next] = result[tmp] + 1;
                        queue.offer(next);
                    }
                }
            }
        }
        for(int i = 1; i< result.length; i++) {
            if(result[i] > max) max = result[i];
        }
    }
}
