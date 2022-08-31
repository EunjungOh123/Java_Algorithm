package Practice4_Partition;

// 하얀색 색종이 = 0, 파란색 색종이 = 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2630 {

    static int [][] graph; // N×N 크기의 행렬로 표현되는 색종이
    static int [] count = new int[2]; // 각각의 색종이 개수 담을 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for(int i = 0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        paperCut(n,0,0);
        for(int i : count) {
            System.out.println(i);
        }
    }

    // 단위 종이 내 모든 칸이 동일한 숫자를 가지고 있는지 확인
    public static boolean check (int n, int x, int y) {
        for(int i = x; i<x+n; i++) {
            for(int j = y; j<y+n; j++) {
                int start = graph[x][y]; // 시작점
                if(start != graph[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void paperCut (int n, int x, int y) {
        if(check(n, x, y)) {
            count[graph[x][y]]++; // 0번 인덱스 : 하얀색 색종이, 1번 인덱스 : 파란색 색종이
        } else {
            int size = n/2;
            for(int i = x; i<x+n; i+=size) {
                for(int j = y; j<y+n; j+=size) {
                    paperCut(size, i, j);
                }
            }
        }
    }
}
