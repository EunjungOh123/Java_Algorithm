package Practice15_UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon4386 {

    static int n;
    static ArrayList<Node> nodeList = new ArrayList<>();
    static PriorityQueue<Edge> queue = new PriorityQueue<>();
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visited = new boolean[n];
        parent = new int[n];

        StringTokenizer st;

        for(int i = 0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());
            nodeList.add(new Node(i, a, b));
        }

        for(int i = 0; i<n; i++) {
            for(int j = i+1; j<n; j++) {
                double dist = Math.sqrt(Math.pow(nodeList.get(i).x-nodeList.get(j).x, 2) + Math.pow(nodeList.get(i).y-nodeList.get(j).y, 2));
                queue.offer(new Edge(i, j, dist));
            }
        }
        double answer = kruskal();
        System.out.printf("%.2f", answer);
    }
    public static double kruskal () {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        double distance = 0;
        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            int a = find(edge.start);
            int b = find(edge.end);
            if(a != b) {
                union(a, b); // 별 연결시키기
                distance += edge.distance;
            }
        }
        return distance;
    }
    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) { // 두 부모 노드를 합치는 메서드
        parent[x] = y;
    }
}

class Node {
    int num;
    double x;
    double y;

    public Node(int num, double x, double y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge> {
    int start;
    int end;
    double distance;

    public Edge(int start, int end, double distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge e) {
        return Double.compare(this.distance, e.distance); // 거리 오름차순 정렬
    }
}