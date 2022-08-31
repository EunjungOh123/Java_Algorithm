package Practice11_Twopointer;

// 정수의 오버플로우에 주의

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long [] liquid = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++) {
            liquid[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(liquid);
        long [] output = new long[3];
        long ans = Long.MAX_VALUE;
        for(int i = 0; i<n; i++) {
            int start = 0;
            int end = liquid.length-1;
            long sum = 0;
            long tmp = Long.MAX_VALUE;
            long [] temp = new long[3];
            while(start<end) {
                if(start == i) {
                    start++;
                    continue;
                } else if (end == i) {
                    end--;
                    continue;
                }
                sum = liquid[i]+liquid[start]+liquid[end];
                if(tmp > Math.abs(sum)) {
                    temp[0] = liquid[start];
                    temp[1] = liquid[end];
                    temp[2] = liquid[i];
                    tmp = Math.abs(sum);
                    if(sum == 0) break;
                }
                if(sum<0) {
                    start++;
                } else {
                    end--;
                }
            }
            if(ans > tmp) {
                ans = tmp;
                output[0] = temp[0];
                output[1] = temp[1];
                output[2] = temp[2];
            }
        }
        Arrays.sort(output);
        for(long i : output) {
            System.out.print(i+" ");
        }
    }
}
