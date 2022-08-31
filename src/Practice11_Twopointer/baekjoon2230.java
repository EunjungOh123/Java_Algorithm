package Practice11_Twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int [] arr = new int[n];
        for(int i = 0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int start = 0;
        int end = 1;
        int gap = 0;
        int min = Integer.MAX_VALUE;
        while(end<n) {
            gap = Math.abs(arr[start]-arr[end]);
            if(gap == m) {
                min = gap;
                break;
            } else if(gap < m) { // 차를 늘려줘야 한다
                end++;
            } else { // gap>m : 차를 줄여줘야 한다
                start++;
                min = Math.min(min, gap);
            }

        }
        System.out.println(min);
    }
}
