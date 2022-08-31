package Practice11_Twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int [] arr = new int[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int end = 0; // 시작점(start)과 끝점(end)이 첫 번째 원소의 인덱스
        int count = 0;
        int sum = 0;
        while(end <= n) {
            if(sum<m) { // 합이 m보다 작은 경우 부분 배열의 길이를 늘려 합을 더해주고 end 한 칸 오른쪽으로 이동
                sum += arr[end];
                end++;
            } else { // 합이 m보다 크거나 같은 경우 부분 배열의 길이를 줄여 합을 빼주고 start 한 칸 오른쪽 이동
                sum -= arr[start];
                start++;
            }
            if(sum == m) {
                count++;
            }
        }
        System.out.println(count);
    }
}
