package Practice4_Partition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1629 {
    static int A;
    static int B;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        System.out.println(multiple(A, B));
    }

    // ex) 3^4 = (3*3) * (3*3)
    public static long multiple(long x, long n) {
        if (n == 1) {
            return x % C;
        }

        long tmp = multiple(x, n / 2);

        // 모듈러 합동 공식: (a * b) % C = ((a % C)*(b % C)) % C
        // tmp * tmp * A % C = ((tmp * tmp % C) * A) % C
        if (n % 2 == 1) {
            return ((tmp * tmp % C) * A) % C;
        }
        return tmp * tmp % C;
    }
}
