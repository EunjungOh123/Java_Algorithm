package Practice4_Partition;

// n=9 일 경우 한 블록의 사이즈는 3 (9블럭)
// n=3 일 경우 한 블록의 사이즈는 1 (9블럭)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon1780 {

    static int [][] graph; // N×N 크기의 행렬로 표현되는 종이 (-1,0,1)
    static int [] result = new int[3];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for(int i = 0; i<n; i++) {
            String [] arr = br.readLine().split(" ");
            for(int j = 0; j<n; j++) {
                graph[i][j] = Integer.parseInt(arr[j]);
            }
        }
        paperDivide(n,0,0);
        for(int i : result) {
            System.out.println(i);
        }
    }

    public static void paperDivide (int n, int x, int y) {
        if(check(n,x,y)) {
            result[graph[x][y]+1]++; // -1이 0번 인덱스, 0이 1번 인덱스, 1이 2번 인덱스에 위치
        } else {
            int size = n / 3; // 쪼개기 > 한 변이 9에서 3으로
            for(int i = x; i<x+n; i+=size) {
                for(int j = y; j<y+n; j+=size) {
                    paperDivide(size, i, j);
                }
            }
        }
    }

    // 단위 종이 내 모든 칸이 동일한 숫자를 가지고 있는지 확인
   public static boolean check (int n, int x, int y) {
        int start = graph[x][y]; // 시작점
        for(int i = x; i<x+n; i++) {
            for (int j = y; j < y + n; j++) {
                if (start != graph[i][j]) {
                    return false;
                }
            }
        }
       return true;
    }
}
