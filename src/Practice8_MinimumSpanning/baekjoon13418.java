package Practice8_MinimumSpanning;

// 오르막길 = 0, 내리막길 = 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon13418 {
    static int n;
    static int m;
    static boolean[] visited;
    static ArrayList<School>[] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lists = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int fatigue = Integer.parseInt(st.nextToken());
            lists[a].add(new School(b, fatigue));
            lists[b].add(new School(a, fatigue));
        }
        int best = best(0);
        int worst = worst(0);
        int ans = (int) (Math.pow(worst, 2)-Math.pow(best, 2));
        System.out.println(ans);
    }

    public static int best(int x) {
        int count = 0;
        PriorityQueue<School> queue = new PriorityQueue<>(new Comparator<School>() {
            @Override
            public int compare(School o1, School o2) {
                return o2.fatigue-o1.fatigue; // 내리막길부터
            }
        });
        visited = new boolean[n + 1];
        queue.offer(new School(x, -1));
        while (!queue.isEmpty()) {
            School school = queue.poll();
            if (!visited[school.num]) {
                visited[school.num] = true;
                if(school.fatigue == 0) {
                    count++;
                }
                for (School next : lists[school.num]) {
                    if(!visited[next.num]) {
                        queue.offer(new School(next.num, next.fatigue));
                    }
                }
            }
        }
        return count;
    }
    public static int worst(int x) {
        int count = 0;
        PriorityQueue<School>queue = new PriorityQueue<>(new Comparator<School>() {
            @Override
            public int compare(School o1, School o2) {
                return o1.fatigue-o2.fatigue; // 오르막길부터
            }
        });
        visited = new boolean[n + 1];
        queue.offer(new School(x, -1));
        while (!queue.isEmpty()) {
            School school = queue.poll();
            if (!visited[school.num]) {
                visited[school.num] = true;
                if(school.fatigue == 0) {
                    count++;
                }
                for (School next : lists[school.num]) {
                    if(!visited[next.num]) {
                        queue.offer(new School(next.num, next.fatigue));
                    }
                }
            }
        }
        return count;
    }
}

class School  {
    int num;
    int fatigue;

    public School(int num, int fatigue) {
        this.num = num;
        this.fatigue = fatigue;
    }
}