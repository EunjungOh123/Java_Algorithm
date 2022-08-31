package Practice4_Partition;

// 4사분면으로 나누어 생각하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1074 {

    static int N;
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int length = (int) Math.pow(2, N);
        int answer = partition(length, 0, 0);
        System.out.println(answer);

    }
    public static int partition (int size, int r, int c) {
        if(size == 1) {
            return 0;
        }

        // 1사분면
        if(R < r+size/2 && C >= c+size/2) {
            return partition(size/2, r, c+size/2) + (size/2)*(size/2);
        }
        // 2사분면
        else if(R < r+size/2 && C < c+size/2) {
            return partition(size/2, r, c) ;
        }
        // 3사분면
        else if(R >= r+size/2 && C < c+size/2) {
            return partition(size/2, r+size/2, c) + (size/2)*(size/2)*2;
        }
        // 4사분면 if(R >= r+size/2 && C >= c+size/2)
        else {
            return partition(size/2, r+size/2, c+size/2) + (size/2)*(size/2)*3;
        }
    }
}
