package Practice12_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] line = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            line[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(line);
        int sum = 0;
        int [] ans = new int[n];
        for(int i = 0; i<n; i++) {
            sum += line[i];
            ans[i] = sum;
        }
        int answer = 0;
        for(int i : ans) {
            answer += i;
        }
        System.out.println(answer);
    }
}
