package Practice4_Partition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon10830 {
    static int N;
    static long B;
    static int [][] grid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        grid = new int[N][N];
        String [] str;
        for(int i = 0; i<N; i++) {
            str = br.readLine().split(" ");
            for(int j = 0; j<N; j++) {
                // 초기 행렬에도 1000으로 나눈 나머지 값으로 초기화
                // > B = 1인 경우 행렬의 숫자가 1000이 넘을 경우 나머지가 아닌 기존 값이 그대로 출력되므로
                grid[i][j] = Integer.parseInt(str[j]) % 1000;
            }
        }
        int [][] output = partition(grid, B);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                sb.append(output[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static int[][] partition (int [][] procession, long num) {
        if(num == 1L) {
            return procession;
        }
        int [][] tmp = partition(grid, num/2);

        tmp = calculator(tmp, tmp);

        if(num % 2 == 1L) {
            tmp = calculator(tmp, grid);
        }

        return tmp;
    }
    public static int[][] calculator (int [][] grid1, int [][] grid2) {
        int [][] tmp = new int[N][N];
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                for(int k = 0; k<N; k++) {
                    tmp[i][j] += grid1[i][k] * grid2[k][j];
                    tmp[i][j] %= 1000;
                }
            }
        }
        return tmp;
    }
}
